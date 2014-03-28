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
<h1 class="ui-widget-header">
	<s:message code="secure/pwdtool/index.message.0" />
</h1>
<div class="ui-widget">
	<div>
		<input id="plainText" type="password" class="app-fullwidth">
	</div>
	<div>
		<button id="encodeButton" onclick="JavaScript:encode();"
			class="app-button">
			<s:message code="secure/pwdtool/index.encodeButton" />
		</button>
	</div>
	<div>
		<input id="encodedText" type="text" class="app-fullwidth">
	</div>
</div>
<script type="text/javascript">
	function encode() {
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
	}
</script>
