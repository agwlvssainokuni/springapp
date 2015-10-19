<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true" type="cherry.goods.paginate.PageSet"%>
<div class="ex-pager-desc">
	<s:message text="全{0}件中 {1}件目〜{2}件目" arguments="${pageSet.last.to+1},${pageSet.current.from+1},${pageSet.current.to+1}" />
</div>
