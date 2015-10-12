<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<%@ attribute name="sortBy" required="true" rtexprvalue="true"%>
<%@ attribute name="sortOrder" required="true" rtexprvalue="true"%>
<%@ attribute name="sortByItems" required="true" rtexprvalue="true" type="java.util.List"%>
<%@ attribute name="cssClass" required="true" rtexprvalue="true"%>
<fieldset class="page-sorter">
	<f:label path="${sortBy}" for="${sortBy}3">並び順</f:label>
	<f:select path="${sortBy}" id="${sortBy}3" cssClass="${cssClass}">
		<f:options itemLabel="codeLabel" itemValue="codeValue" items="${sortByItems}" />
	</f:select>
	<f:radiobuttons path="${sortOrder}" id="${sortOrder}3" itemLabel="codeLabel" itemValue="codeValue"
		items="${foundation:getLabeledEnumCodeList('cherry.example.web.SortOrder')}" cssClass="${cssClass}" />
</fieldset>
