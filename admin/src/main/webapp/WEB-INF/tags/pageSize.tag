<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="id" required="true" rtexprvalue="true"%>
<%@ attribute name="form" required="true" rtexprvalue="true"%>
<%@ attribute name="psz" required="true" rtexprvalue="true"%>
<div class="page-size">
	<label for="${id}"><s:message code="tag/pageSize.label" /></label>
	<select id="${id}" data-form="${form}" data-psz="${psz}">
		<option value="10">10 per page</option>
		<option value="25">25 per page</option>
		<option value="50">50 per page</option>
		<option value="100">100 per page</option>
	</select>
</div>
