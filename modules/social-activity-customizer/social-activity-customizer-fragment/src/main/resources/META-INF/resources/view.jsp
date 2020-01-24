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

<%
ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

Group scopeGroup = serviceContext.getScopeGroup();

if (scopeGroup.isUser()) {
	CustomSocialActivitiesQueryHelper customSocialActivitiesQueryHelper = (CustomSocialActivitiesQueryHelper)request.getAttribute(SocialActivitiesWebKeys.SOCIAL_ACTIVITIES_QUERY_HELPER);

	customSocialActivitiesQueryHelper.setTypes(null);

	// Social Activities widget uses "Show more" type of pagination, therefore
	// we need to calculate the start and end values for classic pagination and
	// make it available for the servide methods.

	int cur = com.liferay.portal.kernel.util.ParamUtil.getInteger(request, "cur", 1);
	int delta = com.liferay.portal.kernel.util.ParamUtil.getInteger(request, "delta", 10);

	int classicPaginationStart = (cur - 1) * delta;
	int classicPaginationEnd = classicPaginationStart + delta;

	customSocialActivitiesQueryHelper.setClassicPaginationStart(classicPaginationStart);
	customSocialActivitiesQueryHelper.setClassicPaginationEnd(classicPaginationEnd);

	socialActivitiesDisplayContext = new DefaultSocialActivitiesDisplayContext(socialActivitiesRequestHelper, customSocialActivitiesQueryHelper);

	String activityType = ParamUtil.getString(request, "activityType");
	int[] types = null;

	int userActivitiesTotalCount;

	if (!activityType.equals(StringPool.BLANK) && !activityType.equals("ALL")) {
		switch (activityType) {
			case "CREATED":
				types = new int[] {
					WikiActivityKeys.ADD_PAGE
				};

				break;
			case "COMMENTED":
				types = new int[] {
					SocialActivityConstants.TYPE_ADD_COMMENT
				};

				break;
			case "UPDATED":
				types = new int[] {
					WikiActivityKeys.UPDATE_PAGE,
					SocialActivityConstants.TYPE_MOVE_TO_TRASH,
					SocialActivityConstants.TYPE_RESTORE_FROM_TRASH,
					SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH,
					SocialActivityConstants.TYPE_RESTORE_ATTACHMENT_FROM_TRASH,
					SocialActivityConstants.TYPE_ADD_ATTACHMENT
				};

				break;
		}

		customSocialActivitiesQueryHelper.setTypes(types);

		userActivitiesTotalCount = CustomSocialActivitySetLocalServiceUtil.getUserViewableActivitySetsCount(scopeGroup.getClassPK(), types);
	}
	else {
		userActivitiesTotalCount = SocialActivitySetLocalServiceUtil.getUserViewableActivitySetsCount(scopeGroup.getClassPK());
	}

	request.setAttribute("liferay-social-activities:social-activities:userActivitiesTotalCount", userActivitiesTotalCount);
}
%>

<c:if test="<%= socialActivitiesDisplayContext.isTabsVisible() %>">
	<liferay-ui:tabs
		names="<%= socialActivitiesDisplayContext.getTabsNames() %>"
		type="tabs nav-tabs-default"
		url="<%= socialActivitiesDisplayContext.getTabsURL() %>"
		value="<%= socialActivitiesDisplayContext.getSelectedTabName() %>"
	/>
</c:if>

<liferay-social-activities:social-activities
	activitySets="<%= socialActivitiesDisplayContext.getSocialActivitySets() %>"
	feedDisplayStyle="<%= socialActivitiesDisplayContext.getRSSDisplayStyle() %>"
	feedEnabled="<%= false %>"
	feedResourceURL="<%= socialActivitiesDisplayContext.getRSSResourceURL() %>"
	feedTitle="<%= socialActivitiesDisplayContext.getTaglibFeedTitle() %>"
	feedType="<%= socialActivitiesDisplayContext.getRSSFeedType() %>"
	feedURLMessage="<%= socialActivitiesDisplayContext.getTaglibFeedTitle() %>"
/>

<c:if test="<%= !scopeGroup.isUser() %>">
	<c:if test="<%= socialActivitiesDisplayContext.isSeeMoreControlVisible() %>">
		<div class="social-activities-see-more">
			<aui:a cssClass="btn btn-default" href="<%= socialActivitiesDisplayContext.getPaginationURL() %>">
				<liferay-ui:message key="see-more" />
			</aui:a>
		</div>
	</c:if>
</c:if>