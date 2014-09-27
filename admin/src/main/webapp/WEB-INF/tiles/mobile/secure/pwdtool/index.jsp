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
<h1>
	<s:message code="secure/pwdtool/index.message.0" />
</h1>
<div data-role="fieldcontain">
	<input id="plainText" type="password" />
</div>
<div data-role="fieldcontain">
	<button id="encodeButton">
		<s:message code="secure/pwdtool/index.encodeButton" />
	</button>
</div>
<div data-role="fieldcontain">
	<input id="encodedText" type="text" />
</div>
<script type="text/javascript">
	$(function() {
		$("#encodeButton").click(function() {
			$.ajax("<c:url value="/secure/pwdtool/encode" />", {
				type : "POST",
				data : {
					"plainText" : $("#plainText").val(),
					"${_csrf.parameterName}" : "${_csrf.token}"
				},
				dataType : "text",
				success : function(data, textStatus, textStatus) {
					$("#encodedText").val(data);
				}
			});
		});
	});
</script>
