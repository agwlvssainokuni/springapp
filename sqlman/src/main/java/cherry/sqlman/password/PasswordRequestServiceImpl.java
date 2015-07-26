/*
 * Copyright 2015 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.sqlman.password;

import static com.google.common.base.Preconditions.checkState;
import static com.mysema.query.support.Expressions.cases;

import java.util.Locale;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.mail.MailData;
import cherry.foundation.mail.MailFacade;
import cherry.foundation.mail.MailModel;
import cherry.foundation.type.FlagCode;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;
import cherry.sqlman.db.gen.query.BPasswordRequest;
import cherry.sqlman.db.gen.query.QPasswordRequest;
import cherry.sqlman.db.gen.query.QUserAccount;

import com.mysema.query.sql.SQLQueryFactory;

@Service
public class PasswordRequestServiceImpl implements PasswordRequestService {

	public static final String MAILID_PASSWORD_REQUEST = "PASSWORD_REQUEST";

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private MailFacade mailFacade;

	@Value("${sqlman.passwordRequest.intervalInSec}")
	private Integer intervalInSec;

	@Value("${sqlman.passwordRequest.rangeInSec}")
	private Integer rangeInSec;

	@Value("${sqlman.passwordRequest.numOfReq}")
	private Integer numOfReq;

	@Value("${sqlman.passwordRequest.validInSec}")
	private Integer validInSec;

	private final QPasswordRequest pr0 = new QPasswordRequest("pr0");
	private final QPasswordRequest pr1 = new QPasswordRequest("pr1");
	private final QUserAccount ua = new QUserAccount("ua");

	@Transactional
	@Override
	public boolean createRequest(String mailAddr, Locale locale, UriComponentsSource source) {

		boolean mailAddrExists = queryFactory.from(ua).where(ua.mailAddr.eq(mailAddr)).exists();
		if (!mailAddrExists) {
			if (log.isDebugEnabled()) {
				log.debug("{0} does not exist: mailAddr={1}", ua.getTableName(), mailAddr);
			}
		} else {

			LocalDateTime now = bizDateTime.now();

			boolean requestAcceptable = queryFactory
					.from(pr0)
					.where(pr0.mailAddr.eq(mailAddr), pr0.appliedAt.goe(now.minusSeconds(rangeInSec)))
					.uniqueResult(
							cases().when(pr0.id.count().eq(0L)).then(true).when(pr0.id.count().goe(numOfReq))
									.then(false).when(pr0.appliedAt.max().goe(now.minusSeconds(intervalInSec)))
									.then(false).otherwise(true));
			if (!requestAcceptable) {
				if (log.isDebugEnabled()) {
					log.debug("Not acceptable: mailAddr={0}, intervalInSec={1}, rangeInSec={2}, numOfReq={3}",
							mailAddr, intervalInSec, rangeInSec, numOfReq);
				}
				return false;
			}

			UUID token = UUID.randomUUID();

			BPasswordRequest pr = new BPasswordRequest();
			pr.setMailAddr(mailAddr);
			pr.setToken(token.toString());
			pr.setAppliedAt(now);
			pr.setDoneFlg(FlagCode.FALSE.code());
			Integer id = queryFactory.insert(pr0).populate(pr).executeWithKey(pr0.id);
			if (log.isDebugEnabled()) {
				log.debug("{0} is created, id={1}, mailAddr={2}, token={3}", pr0.getTableName(), id, mailAddr, token);
			}

			Model model = new Model();
			model.setMailAddr(mailAddr);
			model.setUri(source.buildUriComponents(token).toUriString());

			MailData msg = mailFacade.createMailData(MAILID_PASSWORD_REQUEST, mailAddr, model);
			mailFacade.send(mailAddr, MAILID_PASSWORD_REQUEST, msg.getFromAddr(), msg.getToAddr(), msg.getCcAddr(),
					msg.getBccAddr(), msg.getReplyToAddr(), msg.getSubject(), msg.getBody());
		}

		return true;
	}

	@Transactional
	@Override
	public boolean updatePassword(String token, String mailAddr, String password, Locale locale) {

		LocalDateTime now = bizDateTime.now();

		Integer requestId = queryFactory
				.from(pr0)
				.forUpdate()
				.where(pr0.mailAddr.eq(mailAddr), pr0.appliedAt.goe(now.minusSeconds(validInSec)))
				.where(pr0.token.eq(token), pr0.doneFlg.ne(FlagCode.TRUE.code()))
				.where(queryFactory.subQuery(pr1).where(pr1.mailAddr.eq(pr0.mailAddr), pr1.appliedAt.gt(pr0.appliedAt))
						.notExists()).uniqueResult(pr0.id);
		if (requestId == null) {
			if (log.isDebugEnabled()) {
				log.debug("Request unmatch: mailAddr={0}, token={1}, validInSec={2}", mailAddr, token, validInSec);
			}
			return false;
		}

		long count0 = queryFactory.update(pr0).set(pr0.doneFlg, FlagCode.TRUE.code())
				.set(pr0.lockVersion, pr0.lockVersion.add(1)).where(pr0.id.eq(requestId)).execute();
		checkState(count0 == 1L, "failed to update {0}: id={1}", pr0.getTableName(), requestId);

		long count1 = queryFactory.update(ua).set(ua.password, passwordEncoder.encode(password))
				.set(ua.lockVersion, ua.lockVersion.add(1)).where(ua.mailAddr.eq(mailAddr)).execute();
		checkState(count1 == 1L, "failed to update {0}: mailAddr={1}", ua.getTableName(), mailAddr);

		return true;
	}

	@Setter
	@Getter
	@ToString
	public static class Model implements MailModel {
		private String mailAddr;
		private String uri;
	}

}
