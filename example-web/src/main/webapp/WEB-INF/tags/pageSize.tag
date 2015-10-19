<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="form" required="true" rtexprvalue="true"%>
<%@ attribute name="psz" required="true" rtexprvalue="true"%>
<div class="ex-page-size form-inline">
	<div class="form-group">
		<select data-form="${form}" data-psz="${psz}" class="form-control input-sm">
			<option value="10">10 件/頁</option>
			<option value="25">25 件/頁</option>
			<option value="50">50 件/頁</option>
			<option value="100">100 件/頁</option>
		</select>
	</div>
</div>
