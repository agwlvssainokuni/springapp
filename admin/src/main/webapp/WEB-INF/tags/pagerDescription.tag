<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true" type="cherry.goods.paginate.PageSet"%>
<div class="pager-description">
	<s:message code="tag/pagerDescription.label"
		arguments="${pagedList.pageSet.last.to+1},${pagedList.pageSet.current.from+1},${pagedList.pageSet.current.to+1}" />
</div>
