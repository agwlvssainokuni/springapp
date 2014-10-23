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

package cherry.spring.foundation.type.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.DataBinder;

import cherry.spring.fwcore.type.format.IpAddrFormat;
import cherry.spring.fwcore.type.format.IpAddrFormatAnnotationFormatterFactory;
import cherry.spring.fwcore.type.format.IpAddrFormat.Version;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class IpAddrFormatTest {

	@Autowired
	private ConversionService conversionService;

	@Test
	public void testInetAddrComp() throws BindException {
		String name = "inetAddrComp";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "111.222.333.444");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888:");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testInetAddrDecomp() throws BindException {
		String name = "inetAddrDecomp";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111:0:0:0:0:0:0:8888",
				parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111:0:0:0:0:0:0:8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "111.222.333.444");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888:");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testInet4Addr() throws BindException {
		String name = "inet4Addr";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testInet6AddrComp() throws BindException {
		String name = "inet6AddrComp";
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "11.22.33.44");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testInet6AddrDeomp() throws BindException {
		String name = "inet6AddrDecomp";
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111:0:0:0:0:0:0:8888",
				parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111:0:0:0:0:0:0:8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "11.22.33.44");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testBigIntAnyComp() throws BindException {
		String name = "bigIntAnyComp";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "111.222.333.444");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888:");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testBigIntAnyDecomp() throws BindException {
		String name = "bigIntAnyDecomp";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "111.222.333.444");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888:");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testBigInt4() throws BindException {
		String name = "bigInt4";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testBigInt6Comp() throws BindException {
		String name = "bigInt6Comp";
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "11.22.33.44");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testBigInt6Deomp() throws BindException {
		String name = "bigInt6Decomp";
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "11.22.33.44");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testStrAnyComp() throws BindException {
		String name = "strAnyComp";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "111.222.333.444");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888:");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testStrAnyDecomp() throws BindException {
		String name = "strAnyDecomp";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "111.222.333.444");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888:");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testStr4() throws BindException {
		String name = "str4";
		assertEquals("11.22.33.44", parseAndPrint(name, "11.22.33.44"));
		try {
			parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testStr6Comp() throws BindException {
		String name = "str6Comp";
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111::8888", parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "11.22.33.44");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testStr6Deomp() throws BindException {
		String name = "str6Decomp";
		assertEquals("1111:2222:3333:4444:5555:6666:7777:8888",
				parseAndPrint(name, "1111:2222:3333:4444:5555:6666:7777:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111:0:0:0:0:0:0:8888"));
		assertEquals("1111:0000:0000:0000:0000:0000:0000:8888",
				parseAndPrint(name, "1111::8888"));
		try {
			parseAndPrint(name, "11.22.33.44");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testFactory() {
		IpAddrFormatAnnotationFormatterFactory factory = new IpAddrFormatAnnotationFormatterFactory();
		try {
			factory.getPrinter(null, Object.class);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
		try {
			factory.getParser(null, Object.class);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	private String parseAndPrint(String name, String value)
			throws BindException {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(name, value);

		Form form = new Form();
		DataBinder binder = new DataBinder(form, "target");
		binder.setConversionService(conversionService);
		binder.bind(new MutablePropertyValues(paramMap));

		BindingResult binding = BindingResultUtils.getBindingResult(
				binder.close(), "target");
		return (String) binding.getFieldValue(name);
	}

	@Getter
	@Setter
	public static class Form {

		@IpAddrFormat(v6compress = true)
		private InetAddress inetAddrComp;

		@IpAddrFormat(v6compress = false)
		private InetAddress inetAddrDecomp;

		@IpAddrFormat
		private Inet4Address inet4Addr;

		@IpAddrFormat(v6compress = true)
		private Inet6Address inet6AddrComp;

		@IpAddrFormat(v6compress = false)
		private Inet6Address inet6AddrDecomp;

		@IpAddrFormat(value = Version.ANY, v6compress = true)
		private BigInteger bigIntAnyComp;

		@IpAddrFormat(value = Version.ANY, v6compress = false)
		private BigInteger bigIntAnyDecomp;

		@IpAddrFormat(Version.V4)
		private BigInteger bigInt4;

		@IpAddrFormat(value = Version.V6, v6compress = true)
		private BigInteger bigInt6Comp;

		@IpAddrFormat(value = Version.V6, v6compress = false)
		private BigInteger bigInt6Decomp;

		@IpAddrFormat(value = Version.ANY, v6compress = true)
		private String strAnyComp;

		@IpAddrFormat(value = Version.ANY, v6compress = false)
		private String strAnyDecomp;

		@IpAddrFormat(Version.V4)
		private String str4;

		@IpAddrFormat(value = Version.V6, v6compress = true)
		private String str6Comp;

		@IpAddrFormat(value = Version.V6, v6compress = false)
		private String str6Decomp;
	}

}
