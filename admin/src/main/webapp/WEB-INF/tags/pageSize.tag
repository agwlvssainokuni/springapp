<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="form" required="true" rtexprvalue="true"%>
<%@ attribute name="psz" required="true" rtexprvalue="true"%>
<fieldset class="page-size">
	<label><s:message code="tag/pageSize.label" /> <select
		data-form="${form}" data-psz="${psz}">
			<option value="10">10 items</option>
			<option value="25">25 items</option>
			<option value="50">50 items</option>
			<option value="100">100 items</option>
	</select></label>
</fieldset>
