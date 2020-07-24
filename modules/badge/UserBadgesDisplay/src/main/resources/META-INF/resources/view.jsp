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
<%@ page import="com.liferay.grow.gamification.badges.display.constants.UserBadgesDisplayPortletKeys" %><%@
page import="com.liferay.grow.gamification.badges.display.model.BadgeAggregator" %><%@
page import="com.liferay.grow.gamification.model.BadgeType" %>

<%@ page import="java.util.List" %>

<%
List<BadgeAggregator> aggregators = (List<BadgeAggregator>)request.getAttribute(UserBadgesDisplayPortletKeys.BADGE_AGGRETAGORS);
List<BadgeType> badgeTypes = (List<BadgeType>)request.getAttribute(UserBadgesDisplayPortletKeys.BADGE_TYPES);
long userId = 0;
String userName = "";
%>

<portlet:actionURL name="addBadge" var="addBadgeURL">
	<portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>" />
</portlet:actionURL>

<div aria-hidden="true" class="modal" id="badgeModal" role="dialog" style="display:none; z-index" tabindex="-1">
	<div class="flex">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<aui:form action="<%= addBadgeURL %>" id="badgeForm" method="post" name="badgeForm">
				<aui:input id="userId" name="userId" type="hidden" />
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
						<button class="btn btn-primary" onclick="addBadge()" type="submit">Add Badge</button>
					</div>
				</div>
			</aui:form>
		</div>
	</div>
</div>

<div class="container">

	<%
	if (themeDisplay.getScopeGroup().isUser()) {
		userId = (long)request.getAttribute(UserBadgesDisplayPortletKeys.BADGE_USER_ID);
		userName = (String)request.getAttribute(UserBadgesDisplayPortletKeys.BADGE_USER_NAME);
		if (aggregators != null) {
			for (BadgeAggregator aggregator : aggregators) {
	%>

	<div class="row">
		<div class="col-sm-1">
		<img class="badge-image" src="<%= aggregator.getImage() %>" />

			<%
				if (aggregator.getCount() > 1) {
			%>

				<span class="badge badge-danger"> <%= (aggregator.getCount() > 99) ? "99+" : aggregator.getCount() %></span>
			<% }
			%>

			</div>

			<div class="col-sm-6">
			<p class="badge-type"><%= aggregator.getName() %></p>
			</div>
		</div>
	<% }
		}

		if (themeDisplay.isSignedIn()) {
	%>

	<div class="row">
			<div class="btn-group">
				<button aria-expanded="false" aria-haspopup="true" class="btn btn-info dropdown-toggle" data-toggle="dropdown" id="dropdownMenu" type="button">
					Add a Badge
				</button>

				<div class="dropdown-menu">
					<%for (BadgeType badgeType : badgeTypes) { %>

						<a class="dropdown-item" data-target="#badgeModal" data-toggle="modal" href="#" onclick="showBadgeDialog(<%= userId %>, <%= badgeType.getBadgeTypeId() %>, '<%= badgeType.getType() %>', '<%= userName %>')"><%= badgeType.getType() %></a>

					<%
					}
					%>

				</div>
			</div>
	</div>
	<% }
		} %>
</div>