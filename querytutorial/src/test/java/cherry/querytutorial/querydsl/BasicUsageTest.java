/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.querytutorial.querydsl;

import static java.lang.System.out;
import static java.text.MessageFormat.format;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.querytutorial.db.gen.dto.Author;
import cherry.querytutorial.db.gen.query.QAuthor;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.QBean;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class BasicUsageTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Test
	public void sec0101_Tupleとして取出す() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.all()));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			String valName = tuple.get(a.name);
			LocalDateTime valUpdatedAt = tuple.get(a.updatedAt);
			LocalDateTime valCreatedAt = tuple.get(a.createdAt);
			Integer valLockVersion = tuple.get(a.lockVersion);
			Integer valDeletedFlg = tuple.get(a.deletedFlg);
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}", valId,
					valLoginId, valName, valUpdatedAt, valCreatedAt, valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec0102_Beanとして取出す_QBean() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Author> list = queryDslJdbcOperations.query(query, new QBean<Author>(Author.class, a.all()));

		/* クエリの結果を表示する。 */
		for (Author entity : list) {
			Long valId = entity.getId();
			String valLoginId = entity.getLoginId();
			String valName = entity.getName();
			LocalDateTime valUpdatedAt = entity.getUpdatedAt();
			LocalDateTime valCreatedAt = entity.getCreatedAt();
			Integer valLockVersion = entity.getLockVersion();
			Integer valDeletedFlg = entity.getDeletedFlg();
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}", valId,
					valLoginId, valName, valUpdatedAt, valCreatedAt, valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec0103_Beanとして取出す_RowMapper() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Author> list = queryDslJdbcOperations.query(query, rowMapperCreator.create(Author.class), a.all());

		/* クエリの結果を表示する。 */
		for (Author entity : list) {
			Long valId = entity.getId();
			String valLoginId = entity.getLoginId();
			String valName = entity.getName();
			LocalDateTime valUpdatedAt = entity.getUpdatedAt();
			LocalDateTime valCreatedAt = entity.getCreatedAt();
			Integer valLockVersion = entity.getLockVersion();
			Integer valDeletedFlg = entity.getDeletedFlg();
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}", valId,
					valLoginId, valName, valUpdatedAt, valCreatedAt, valLockVersion, valDeletedFlg));
		}
	}

}
