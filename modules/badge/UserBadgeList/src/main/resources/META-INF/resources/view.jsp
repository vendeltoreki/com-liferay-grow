<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.document.library.kernel.util.DLUtil" %>
<%@ page import="com.liferay.portal.kernel.repository.model.FileEntry" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil" %>
<%@ page import="com.liferay.grow.gamification.service.BadgeLocalServiceUtil" %>
<%@ page import="com.liferay.grow.gamification.model.BadgeType" %>
<%@ page import="com.liferay.grow.gamification.badges.list.constants.UserBadgeListPortletKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.liferay.portal.kernel.util.DateUtil" %>

<%
long userId = 0;

if (request.getAttribute(UserBadgeListPortletKeys.BADGE_USER_ID) != null) {
	userId = (long)request.getAttribute(UserBadgeListPortletKeys.BADGE_USER_ID);
}
else if (request.getParameter("userId") != null) {
	userId = Long.parseLong(request.getParameter("userId"));
}
List<BadgeType> badgeTypes = (List<BadgeType>)request.getAttribute(UserBadgeListPortletKeys.BADGE_TYPES);
List<User> users = (List<User>)request.getAttribute(UserBadgeListPortletKeys.USER_LIST);
User selectedUser = null;
System.out.println("userId:" + userId);
if (userId > 0) {
	selectedUser = UserLocalServiceUtil.fetchUser(userId);
}
if (!themeDisplay.getScopeGroup().isUser()) { %>
<portlet:actionURL name="selectUser" var="selectUserURL">
	<%if(userId > 0) { %>
		<portlet:param name="userId" value="<%= String.valueOf(userId) %>" />
	<% } %>
</portlet:actionURL>

<div class="container">
	<div class="row">
		<div class="col-sm">
			<div class="btn-group">
				<aui:form method="post" action="${selectUserURL}" name="userForm">
					<aui:input type="hidden" name="userId" value="<%= userId %>" />
					<aui:input type="hidden" name="userName" value="<%=  ((selectedUser == null) || (selectedUser.getFullName().trim().equals(""))) ? "Select User" : selectedUser.getFullName() %>" />
					<button type="button" class="btn btn-warn dropdown-toggle" id="dropdownListUserMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						
					</button>
					<div class="dropdown-menu">
						<% for (User userItem : users) { 	%>
							<a class="dropdown-item" href="#" onclick="selectUser(<%= userItem.getUserId() %>, '<%= (userItem.getFullName().trim().equals("")) ? "Select user" : userItem.getFullName()  %>')"><%= (userItem.getFullName().trim().equals("")) ? "Select user" :  userItem.getFullName()  %></a>
						<% } %>
					</div>
				</aui:form>
			</div>
		</div>
	</div>
<% } 
if (userId != 0) {
%>
<liferay-portlet:renderURL varImpl="iteratorURL2">
	<liferay-portlet:param name="userId" value="<%= String.valueOf(userId) %>"/>
</liferay-portlet:renderURL>
	<div class="row">
		<div class="col-sm">
				<liferay-ui:search-container deltaConfigurable="<%= true %>" total="<%= BadgeLocalServiceUtil.getBadgesOfUser(userId).size() %>" iteratorURL="<%= iteratorURL2 %>">
				<liferay-ui:search-container-results	
					results="<%= BadgeLocalServiceUtil.getBadgesOfUser(userId, searchContainer.getStart(), searchContainer.getEnd()) %>" />
					<liferay-ui:search-container-row className="com.liferay.grow.gamification.model.Badge" modelVar="badge">
					<%
					long fileEntryId = 0;
					String type = "";
						for (BadgeType badgeType : badgeTypes) {
							if (badge.getBadgeTypeId() == badgeType.getBadgeTypeId()) {
								fileEntryId = badgeType.getFileEntryId();
								type = badgeType.getType();
								break;
							}
						}
					%>
						<liferay-ui:search-container-column-text name="Badge">
							<%
								
								FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
								String downloadUrl = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, "", false, true);
							%>
							<p class="badge-icon">
								<img class="badge-image" src="<%= downloadUrl %>" />
							</p>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="Type">
							<%= type %>
						</liferay-ui:search-container-column-text>
			
						<liferay-ui:search-container-column-text name="Description">
							<c:out value="${badge.getDescription()}"></c:out>
						</liferay-ui:search-container-column-text>
			
						<liferay-ui:search-container-column-text name="Received at">
							<%= DateUtil.getDate(badge.getCreateDate(), "yyyy-MM-dd", Locale.US) %>
						</liferay-ui:search-container-column-text>
			
						<liferay-ui:search-container-column-text name="Given by">
							<c:out value="${badge.getUserName()}"></c:out>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>
				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>
<%} else { %>
<liferay-portlet:renderURL varImpl="iteratorURL" />
<div class="row">
		<div class="col-sm">
		
				<liferay-ui:search-container deltaConfigurable="<%= true %>" total="<%= BadgeLocalServiceUtil.getBadgesCount()%>" iteratorURL="<%= iteratorURL %>">
				<liferay-ui:search-container-results
					results="<%= BadgeLocalServiceUtil.getBadges(searchContainer.getStart(), searchContainer.getEnd())%>" />
					<liferay-ui:search-container-row className="com.liferay.grow.gamification.model.Badge" modelVar="badge">
					<%
					long fileEntryId = 0;
					String type = "";
						for (BadgeType badgeType : badgeTypes) {
							if (badge.getBadgeTypeId() == badgeType.getBadgeTypeId()) {
								fileEntryId = badgeType.getFileEntryId();
								type = badgeType.getType();
								break;
							}
						}
					User badgeReceiver = UserLocalServiceUtil.getUserById(badge.getToUserId());
					%>
					<c:out value="${badgeReceiver.getFirstName()}"></c:out>
						<liferay-ui:search-container-column-text name="Badge">
							<%
								FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
								String downloadUrl = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, "", false, true);
							%>
							<p class="badge-icon">
								<img class="badge-image" src="<%= downloadUrl %>" />
							</p>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="Receiver">
							<%=badgeReceiver.getFullName()  %>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="Type">
							<%= type %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text name="Description">
							<c:out value="${badge.getDescription()}"></c:out>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text name="Received at">
							<%= DateUtil.getDate(badge.getCreateDate(), "yyyy-MM-dd", Locale.US) %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text name="Given by">
							<c:out value="${badge.getUserName()}"></c:out>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>
				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>
<% } %>
</div>