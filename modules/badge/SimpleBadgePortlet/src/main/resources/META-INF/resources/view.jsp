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
<%@ page import="com.liferay.grow.gamification.model.BadgeType" %><%@
page import="com.liferay.portal.kernel.model.User" %>

<%@ page import="java.util.List" %>

<%
List<BadgeType> badgeTypes = (List<BadgeType>)request.getAttribute("BADGE_TYPES");
List<User> users = (List<User>)request.getAttribute("USER_LIST");
%>

<%
if (themeDisplay.isSignedIn()) {
%>

<portlet:actionURL name="addBadge" var="addBadgeURL">
	<portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>" />
</portlet:actionURL>

<div class="container">
	<div class="custom row">
		<div class="col-sm-8">
			<select class="flexselect" id="nameSelect" name="nameSelect" palceholder="a is placeholder this">
			<option value="select-a-user"></option>

					<%
					for (User userItem : users) {
					%>

						<option value="<%= userItem.getUserId() %>"><%= userItem.getFullName().trim().equals("") ? "" : userItem.getFullName() %></option>

					<%
					}
					%>

			</select>
		</div>

		<div class="col-sm-2">
			<div class="btn-group">
				<button type="button" class="btn btn-info dropdown-toggle" id="simpleDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" disabled>
					Add a Badge
				</button>

				<div class="dropdown-menu">
					<%for (BadgeType badgeType : badgeTypes) { %>

						<a class="dropdown-item" data-target="#simpleBadgeModal" data-toggle="modal" href="#" onclick="showSimpleBadgeDialog(<%= badgeType.getBadgeTypeId() %>, '<%= badgeType.getType() %>');"><%= badgeType.getType() %></a>

					<%
					}
					%>

				</div>
			</div>
		</div>
	</div>
</div>

<div aria-hidden="true" class="modal" id="simpleBadgeModal" role="dialog" style="display:none; z-index" tabindex="-1">
	<div class="flex">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<aui:form action="<%= addBadgeURL %>" id="simpleBadgeForm" method="post" name="simpleBadgeForm">
				<aui:input id="userId" name="userId" type="hidden" />
				<aui:input id="userName" name="userId" type="hidden" />
				<aui:input id="badgeTypeId" name="badgeTypeId" type="hidden" />

				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Add Badge To title</h5>

						<button aria-label="Close" class="close" data-dismiss="modal" type="button">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
						<div class="form-group">
							<aui:input class="form-control" name="description" type="textarea" value="" />
						</div>
					</div>

					<div class="modal-footer">
						<button class="btn btn-secondary" data-dismiss="modal" type="button">Cancel</button>
						<button class="btn btn-primary" onclick="addSimpleBadge()" type="submit">Add Badge</button>
					</div>
				</div>
			</aui:form>
		</div>
	</div>
</div>
<% } %>