<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="app-subject">
	<s:message code="secure/gallery/init.message.0" />
</h1>
<div class="app-portion">
	<h2 class="app-subject">
		<s:message code="secure/gallery/init.message.1" />
	</h2>
	<table class="app-collabel">
		<tbody>
			<tr>
				<th><s:message code="secure/gallery/init.label.zipCode" /></th>
				<td><input id="zipCode" name="zipCode" class="app-width10"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.label.pref" /></th>
				<td><input id="pref" name="pref" class="app-width20"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.label.city" /></th>
				<td><input id="city" name="city" class="app-width20"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.label.addr1" /></th>
				<td><input id="addr1" name="addr1" class="app-width20"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.label.addr2" /></th>
				<td><input id="addr2" name="addr2" class="app-width20"></td>
			</tr>
		</tbody>
	</table>
</div>
