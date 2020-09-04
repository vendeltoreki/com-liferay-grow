<%--
/**
* Copyright (c) 2000-present Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= Validator.isNotNull(journalContentHeaderDisplayContext) %>">
	<%
		Header header = journalContentHeaderDisplayContext.getFilledHeader();
	%>
	<div class="journal-title">
					<span class="">
						<i class="icon-check"></i>
						<span class="taglib-text hide-accessible"></span>
					</span>
		<h1><%= header.getTitle()%></h1>
	</div>
	<div class="journal-author-details">
		<ul class="list-unstyled">
			<li><a href=""><%= header.getCreator()%></a>,</li>
			<li><%= header.getCreateDate()%></li>
			<li><%= header.getViewCount()%> Views</li>
		</ul>
	</div>
	<div class="journal-parent-article">
	<p>◄ <strong>Parent</strong>: <a href=""><%= header.getParentPage()%></a> </p>
	</div>
</c:if>