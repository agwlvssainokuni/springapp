<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
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
				<th><s:message code="secure/gallery/init.address.zipcd" /></th>
				<td><input id="zipcd" name="zipcd" class="app-width10">
					<button id="ZipcdSearch" class="app-button">
						<s:message code="secure/gallery/init.address.searchButton" />
					</button></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.address.pref" /></th>
				<td><input id="pref" name="pref" class="app-width20"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.address.city" /></th>
				<td><input id="city" name="city" class="app-width20"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.address.addr1" /></th>
				<td><input id="addr1" name="addr1" class="app-width20"></td>
			</tr>
			<tr>
				<th><s:message code="secure/gallery/init.address.addr2" /></th>
				<td><input id="addr2" name="addr2" class="app-width20"></td>
			</tr>
		</tbody>
	</table>
	<mytag:zipcdSearchSubWindow />
	<script type="text/javascript">
		$(function() {
			$("#ZipcdSearch").click(function(event) {
				event.preventDefault();
				zipcdSearchSubWindow($("#zipcd").val(), function(data) {
					$("#pref").val(data.pref);
					$("#city").val(data.city);
					$("#addr1").val(data.addr);
				});
			});
		});
	</script>
</div>
