/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.foundation.download;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.foundation.download.DownloadAction;
import cherry.spring.foundation.download.DownloadHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class DownloadHelperImplTest {

	@Autowired
	private DownloadHelper downloadHelper;

	@Test
	public void testDownload00() throws IOException {
		HttpServletResponse response = createMock();
		LocalDateTime timestamp = LocalDateTime.now();
		String ts = DateTimeFormat.forPattern("yyyyMMddHHmmss")
				.print(timestamp);

		DownloadAction action = new DownloadAction() {
			@Override
			public long doDownload(Writer writer) throws IOException {
				return 123L;
			}
		};
		downloadHelper.download(response, "text/csv", "test_{0}.csv",
				timestamp, action);
		verify(response).setContentType("text/csv");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response).setHeader("Content-Disposition",
				"attachment; filename=\"test_" + ts + ".csv\"");
	}

	@Test
	public void testDownload01() throws IOException {
		HttpServletResponse response = createMock();
		LocalDateTime timestamp = LocalDateTime.now();
		String ts = DateTimeFormat.forPattern("yyyyMMddHHmmss")
				.print(timestamp);

		final IOException ioex = new IOException();
		DownloadAction action = new DownloadAction() {
			@Override
			public long doDownload(Writer writer) throws IOException {
				throw ioex;
			}
		};

		try {
			downloadHelper.download(response, "text/csv", "test_{0}.csv",
					timestamp, action);
			fail("Excption must be thrown");
		} catch (IllegalStateException ex) {
			assertSame(ex.getCause(), ioex);
		}
		verify(response).setContentType("text/csv");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response).setHeader("Content-Disposition",
				"attachment; filename=\"test_" + ts + ".csv\"");
	}

	private HttpServletResponse createMock() throws IOException {
		HttpServletResponse response = mock(HttpServletResponse.class);
		ServletOutputStream out = mock(ServletOutputStream.class);
		when(response.getOutputStream()).thenReturn(out);
		return response;
	}

}
