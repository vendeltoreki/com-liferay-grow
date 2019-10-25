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

<portlet:actionURL name="importBadges" var="importBadgesURL" />

<aui:form action="<%= importBadgesURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input label="Please select a CSV file" name="file" type="file" />
	<aui:input name="" type="submit" value="Import Badges" />
	<aui:input checked="true" label="dryRun" name="dryRun" type="checkbox" />

	<liferay-ui:error exception="<%= FileNameException.class %>" message="the-file-has-to-be-a-comma-separated-list-csv" />
	<liferay-ui:error exception="<%= NoSuchFileException.class %>" message="no-file-has-been-chosen" />
</aui:form>