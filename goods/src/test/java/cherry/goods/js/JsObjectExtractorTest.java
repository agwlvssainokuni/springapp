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

package cherry.goods.js;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Test;

/**
 * {@link JsObjectExtractor} によるJavaScript値変換のテスト.
 */
public class JsObjectExtractorTest {

	/**
	 * 対象: {@link JsObjectExtractor#initialize()}<br>
	 * 区分: 正常<br>
	 * 初期化が正常終了すること.<br>
	 */
	@Test
	public void testInitialize_00() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			extractor.initialize();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#eval(String)}<br>
	 * 区分: 正常<br>
	 * JavaScriptコードの評価が正常終了すること.<br>
	 */
	@Test
	public void testEval_00() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object = extractor
					.eval("var CONFIG = {id: 123, name: '名前'};");

			// 検証
			assertNull(object);
		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#eval(String)}<br>
	 * 区分: 異常<br>
	 * JavaScriptコードの評価が異常終了すること.<br>
	 */
	@Test
	public void testEval_10() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			extractor.eval("var CONFIG = [id: 123, name: '名前'];");

			// 検証
			fail("例外が発生しないのはNG");
		} catch (ScriptException ex) {
			// 検証
			assertTrue(true);
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「null」が変換されること。
	 */
	@Test
	public void testToJavaObject_00() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object = extractor.toJavaObject("null");

			// 検証
			assertNull(object);

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「undefined」が変換されること。
	 */
	@Test
	public void testToJavaObject_01() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object = extractor.toJavaObject("undefined");

			// 検証
			assertNull(object);

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「true/false」が変換されること。
	 */
	@Test
	public void testToJavaObject_02() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object1 = extractor.toJavaObject("true");
			Object object2 = extractor.toJavaObject("false");

			// 検証
			assertEquals(Boolean.TRUE, object1);
			assertEquals(Boolean.FALSE, object2);

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「NaN」が変換されること。
	 */
	@Test
	public void testToJavaObject_03() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object = extractor.toJavaObject("NaN");

			// 検証
			assertEquals(Double.NaN, object);

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「数値」が変換されること。
	 */
	@Test
	public void testToJavaObject_04() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object1 = extractor.toJavaObject("1");
			Object object2 = extractor.toJavaObject("123456789");
			Object object3 = extractor.toJavaObject("12345.6789");
			Object object4 = extractor.toJavaObject("-123456789");
			Object object5 = extractor.toJavaObject("-12345.6789");

			// 検証s
			assertEquals(Double.valueOf(1.0), object1);
			assertEquals(Double.valueOf(123456789.0), object2);
			assertEquals(Double.valueOf(12345.67890), object3);
			assertEquals(Double.valueOf(-123456789.0), object4);
			assertEquals(Double.valueOf(-12345.67890), object5);

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「文字列」が変換されること。
	 */
	@Test
	public void testToJavaObject_05() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object1 = extractor.toJavaObject("''");
			Object object2 = extractor.toJavaObject("'文字列'");

			// 検証
			assertEquals("", object1);
			assertEquals("文字列", object2);

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「配列」が変換されること。
	 */
	@Test
	public void testToJavaObject_06() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object1 = extractor.toJavaObject("[]");
			Object object2 = extractor.toJavaObject("['AAA', 'BBB']");
			Object object3 = extractor.toJavaObject("['AAA', 2]");

			// 検証
			assertNotNull(object1);
			assertTrue(object1 instanceof List);
			assertEquals(0, ((List<?>) object1).size());

			assertNotNull(object2);
			assertTrue(object2 instanceof List);
			assertEquals(2, ((List<?>) object2).size());
			assertEquals("AAA", ((List<?>) object2).get(0));
			assertEquals("BBB", ((List<?>) object2).get(1));

			assertNotNull(object3);
			assertTrue(object3 instanceof List);
			assertEquals(2, ((List<?>) object3).size());
			assertEquals("AAA", ((List<?>) object3).get(0));
			assertEquals(Double.valueOf(2.0), ((List<?>) object3).get(1));

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「オブジェクト」が変換されること。
	 */
	@Test
	public void testToJavaObject_07() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			Object object1 = extractor.toJavaObject("{}");
			Object object2 = extractor.toJavaObject("{a: 'AAA', b: 'BBB'}");
			Object object3 = extractor.toJavaObject("{a: 'AAA', b: 2}");

			// 検証
			assertNotNull(object1);
			assertTrue(object1 instanceof Map);
			@SuppressWarnings("unchecked")
			Map<String, ?> map1 = (Map<String, ?>) object1;
			assertEquals(0, map1.keySet().size());

			assertNotNull(object2);
			assertTrue(object2 instanceof Map);
			@SuppressWarnings("unchecked")
			Map<String, ?> map2 = (Map<String, ?>) object2;
			assertEquals(2, map2.keySet().size());
			assertEquals("AAA", map2.get("a"));
			assertEquals("BBB", map2.get("b"));

			assertNotNull(object3);
			assertTrue(object3 instanceof Map);
			@SuppressWarnings("unchecked")
			Map<String, ?> map3 = (Map<String, ?>) object3;
			assertEquals(2, map3.keySet().size());
			assertEquals("AAA", map3.get("a"));
			assertEquals(Double.valueOf(2.0), map3.get("b"));

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * オブジェクトのリストが変換されること。
	 */
	@Test
	public void testToJavaObject_08() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			List<Map<String, ?>> object = extractor
					.toJavaObject("[{a: 'A1', b: 'B1', c: 1}, {a: 'A2', b: 'B2', c: 2}]");

			// 検証
			assertNotNull(object);
			assertEquals(2, object.size());

			int i = 1;
			for (Map<String, ?> map : object) {
				assertEquals(3, map.keySet().size());
				assertEquals("A" + i, map.get("a"));
				assertEquals("B" + i, map.get("b"));
				assertEquals(Double.valueOf((double) i), map.get("c"));
				i++;
			}

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

	/**
	 * 対象: {@link JsObjectExtractor#toJavaObject(String)}<br>
	 * 区分: 正常<br>
	 * 「設定値」が変換されること。
	 */
	@Test
	public void testToJavaObject_09() {
		JsObjectExtractor extractor = new JsObjectExtractor();
		try {
			// 準備
			extractor.initialize();

			// 実行
			extractor
					.eval("var CONFIG1 = [{a: 'A1', b: 'B1', c: 1}, {a: 'A2', b: 'B2', c: 2}]; var CONFIG2 = {'10': [11, 12], '20': [21, 22]};");
			List<Map<String, ?>> object1 = extractor.toJavaObject("CONFIG1");
			Map<String, List<Double>> object2 = extractor
					.toJavaObject("CONFIG2");

			// 検証
			assertNotNull(object1);
			assertEquals(2, object1.size());

			int i = 1;
			for (Map<String, ?> map : object1) {
				assertEquals(3, map.keySet().size());
				assertEquals("A" + i, map.get("a"));
				assertEquals("B" + i, map.get("b"));
				assertEquals(Double.valueOf((double) i), map.get("c"));
				i++;
			}

			assertNotNull(object2);
			assertEquals(2, object2.size());
			for (int j = 1; j <= 2; j++) {
				List<Double> list = object2.get("" + j + "0");
				assertEquals(2, list.size());
				assertEquals(Double.valueOf((double) (j * 10 + 1)), list.get(0));
				assertEquals(Double.valueOf((double) (j * 10 + 2)), list.get(1));
			}

		} catch (ScriptException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		}
	}

}
