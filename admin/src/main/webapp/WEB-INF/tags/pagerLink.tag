<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true"
	type="cherry.spring.common.lib.pager.PageSet"%>
<div class="app-pager-link">
	<ul class="app-flat">
		<li><span class="app-current" title="${pageSet.current.no+1}"></span></li>
		<li><span class="app-page" title="${pageSet.prev.no+1}">&lt;</span></li>
		<li><span class="app-page" title="${pageSet.first.no+1}">${pageSet.first.no+1}</span></li>
		<c:if test="${pageSet.range[0].no > pageSet.first.no+1}">
			<li><span class="app-spacer">...</span></li>
		</c:if>
		<c:forEach var="page" items="${pageSet.range}">
			<li><span class="app-page" title="${page.no+1}">${page.no+1}</span></li>
		</c:forEach>
		<c:if
			test="${pageSet.range[fn:length(pageSet.range)-1].no <  pageSet.last.no-1}">
			<li><span class="app-spacer">...</span></li>
		</c:if>
		<li><span class="app-page" title="${pageSet.last.no+1}">${pageSet.last.no+1}</span></li>
		<li><span class="app-page" title="${pageSet.next.no+1}">&gt;</span></li>
	</ul>
</div>
