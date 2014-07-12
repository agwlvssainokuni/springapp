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

package cherry.spring.common.custom.format;

import static cherry.spring.common.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.DataBinder;

import cherry.spring.common.custom.format.IpAddrFormat.Version;

public class IpAddrFormatTest {

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

	private String parseAndPrint(String name, String value)
			throws BindException {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(name, value);

		Form form = new Form();
		DataBinder binder = new DataBinder(form, "target");
		binder.setConversionService(getBean(ConversionService.class));
		binder.bind(new MutablePropertyValues(paramMap));

		BindingResult binding = BindingResultUtils.getBindingResult(
				binder.close(), "target");
		return (String) binding.getFieldValue(name);
	}

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

		public InetAddress getInetAddrComp() {
			return inetAddrComp;
		}

		public void setInetAddrComp(InetAddress inetAddrComp) {
			this.inetAddrComp = inetAddrComp;
		}

		public InetAddress getInetAddrDecomp() {
			return inetAddrDecomp;
		}

		public void setInetAddrDecomp(InetAddress inetAddrDecomp) {
			this.inetAddrDecomp = inetAddrDecomp;
		}

		public Inet4Address getInet4Addr() {
			return inet4Addr;
		}

		public void setInet4Addr(Inet4Address inet4Addr) {
			this.inet4Addr = inet4Addr;
		}

		public Inet6Address getInet6AddrComp() {
			return inet6AddrComp;
		}

		public void setInet6AddrComp(Inet6Address inet6AddrComp) {
			this.inet6AddrComp = inet6AddrComp;
		}

		public Inet6Address getInet6AddrDecomp() {
			return inet6AddrDecomp;
		}

		public void setInet6AddrDecomp(Inet6Address inet6AddrDecomp) {
			this.inet6AddrDecomp = inet6AddrDecomp;
		}

		public BigInteger getBigIntAnyComp() {
			return bigIntAnyComp;
		}

		public void setBigIntAnyComp(BigInteger bigIntAnyComp) {
			this.bigIntAnyComp = bigIntAnyComp;
		}

		public BigInteger getBigIntAnyDecomp() {
			return bigIntAnyDecomp;
		}

		public void setBigIntAnyDecomp(BigInteger bigIntAnyDecomp) {
			this.bigIntAnyDecomp = bigIntAnyDecomp;
		}

		public BigInteger getBigInt4() {
			return bigInt4;
		}

		public void setBigInt4(BigInteger bigInt4) {
			this.bigInt4 = bigInt4;
		}

		public BigInteger getBigInt6Comp() {
			return bigInt6Comp;
		}

		public void setBigInt6Comp(BigInteger bigInt6Comp) {
			this.bigInt6Comp = bigInt6Comp;
		}

		public BigInteger getBigInt6Decomp() {
			return bigInt6Decomp;
		}

		public void setBigInt6Decomp(BigInteger bigInt6Decomp) {
			this.bigInt6Decomp = bigInt6Decomp;
		}

		public String getStrAnyComp() {
			return strAnyComp;
		}

		public void setStrAnyComp(String strAnyComp) {
			this.strAnyComp = strAnyComp;
		}

		public String getStrAnyDecomp() {
			return strAnyDecomp;
		}

		public void setStrAnyDecomp(String strAnyDecomp) {
			this.strAnyDecomp = strAnyDecomp;
		}

		public String getStr4() {
			return str4;
		}

		public void setStr4(String str4) {
			this.str4 = str4;
		}

		public String getStr6Comp() {
			return str6Comp;
		}

		public void setStr6Comp(String str6Comp) {
			this.str6Comp = str6Comp;
		}

		public String getStr6Decomp() {
			return str6Decomp;
		}

		public void setStr6Decomp(String str6Decomp) {
			this.str6Decomp = str6Decomp;
		}
	}

}
