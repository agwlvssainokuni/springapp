/*
 * Copyright 2012,2014 agwlvssainokuni
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

package cherry.spring.common.lib.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

/**
 * {@link SqlUtil} のテスト.
 */
public class SqlUtilTest {

	private SqlUtil sqlUtil = new SqlUtil();

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行に1つのSQL (デリミタなし)
	 */
	@Test
	public void testNextSql_00() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT * FROM DUAL");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT * FROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行に1つのSQL (デリミタあり)
	 */
	@Test
	public void testNextSql_01() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT * FROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT * FROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * 2行に2つのSQL (末尾のデリミタなし)
	 */
	@Test
	public void testNextSql_02() throws IOException {

		// 準備
		Reader reader = new StringReader(
				"SELECT 111 FROM DUAL;SELECT 222 FROM DUAL");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);
		String sql3 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT 111 FROM DUAL", sql1);
		assertEquals("SELECT 222 FROM DUAL", sql2);
		assertNull(sql3);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * 2行に2つのSQL (末尾のデリミタあり)
	 */
	@Test
	public void testNextSql_03() throws IOException {

		// 準備
		Reader reader = new StringReader(
				"SELECT 111 FROM DUAL;SELECT 222 FROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);
		String sql3 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT 111 FROM DUAL", sql1);
		assertEquals("SELECT 222 FROM DUAL", sql2);
		assertNull(sql3);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文中に引用符 (') あり
	 */
	@Test
	public void testNextSql_10() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT '111' FROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT '111' FROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文中に引用符 (') あり、引用符のエスケープあり
	 */
	@Test
	public void testNextSql_11() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT '11''11' FROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT '11''11' FROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文中に引用符 (') あり、引用符中にハイフン (-) あり
	 */
	@Test
	public void testNextSql_12() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT '11-11' FROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT '11-11' FROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文中にハイフン (-) あり
	 */
	@Test
	public void testNextSql_20() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT 11-11 FROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT 11-11 FROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文中にコメントあり
	 */
	@Test
	public void testNextSql_21() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT 11-11 --COMMENT\r\nFROM DUAL;");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT 11-11 \r\nFROM DUAL", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文の末尾にハイフン (-) あり
	 */
	@Test
	public void testNextSql_22() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT * FROM DUAL -");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT * FROM DUAL -", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextSql(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL文の末尾にコメント
	 */
	@Test
	public void testNextSql_23() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT * FROM DUAL --");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextSql(reader);

		// 検証
		assertEquals("SELECT * FROM DUAL ", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行に1つのコメント
	 */
	@Test
	public void testNextComment_00() throws IOException {

		// 準備
		Reader reader = new StringReader("-- COMMENT");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行に1つのコメント(改行あり)
	 */
	@Test
	public void testNextComment_01() throws IOException {

		// 準備
		Reader reader = new StringReader("-- COMMENT\r\n");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT\r\n", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 2行にコメント
	 */
	@Test
	public void testNextComment_10() throws IOException {

		// 準備
		Reader reader = new StringReader("-- COMMENT1\r\n-- COMMENT2\r\n");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);
		String sql3 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT1\r\n", sql1);
		assertEquals("-- COMMENT2\r\n", sql2);
		assertNull(sql3);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行目にコメント、2行目にSQL
	 */
	@Test
	public void testNextComment_11() throws IOException {

		// 準備
		Reader reader = new StringReader("-- COMMENT\r\nSELECT x FROM dual;");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT\r\n", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行目にコメント、2行目にSQL
	 */
	@Test
	public void testNextComment_12() throws IOException {

		// 準備
		Reader reader = new StringReader("-- COMMENT\r\nSELECT x FROM dual;");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextSql(reader);
		String sql3 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT\r\n", sql1);
		assertEquals("SELECT x FROM dual", sql2);
		assertNull(sql3);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行目にSQL、2行目にコメント
	 */
	@Test
	public void testNextComment_13() throws IOException {

		// 準備
		Reader reader = new StringReader(
				"SELECT x FROM dual;\r\n-- COMMENT\r\n");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT\r\n", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * 1行目にSQL、2行目にコメント
	 */
	@Test
	public void testNextComment_14() throws IOException {

		// 準備
		Reader reader = new StringReader(
				"SELECT x FROM dual;\r\n-- COMMENT\r\n");

		// 実行
		String sql1 = sqlUtil.nextSql(reader);
		String sql2 = sqlUtil.nextComment(reader);
		String sql3 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("SELECT x FROM dual", sql1);
		assertEquals("-- COMMENT\r\n", sql2);
		assertNull(sql3);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * SQL中にハイフンが一つ、その後ろにコメント
	 */
	@Test
	public void testNextComment_20() throws IOException {

		// 準備
		Reader reader = new StringReader("SELECT 1-1 FROM dual;-- COMMENT");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT", sql1);
		assertNull(sql2);
	}

	/**
	 * 対象: {@link SqlUtil#nextComment(Reader)}.<br>
	 * 区分: 正常<br>
	 * コメントの後ろに、SQL中にハイフンが一つ
	 */
	@Test
	public void testNextComment_21() throws IOException {

		// 準備
		Reader reader = new StringReader("-- COMMENT\r\nSELECT 1-1 FROM dual;");

		// 実行
		String sql1 = sqlUtil.nextComment(reader);
		String sql2 = sqlUtil.nextComment(reader);

		// 検証
		assertEquals("-- COMMENT\r\n", sql1);
		assertNull(sql2);
	}

}
