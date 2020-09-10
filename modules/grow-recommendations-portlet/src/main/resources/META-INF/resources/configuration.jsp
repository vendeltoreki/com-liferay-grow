<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>"
    var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>"
    var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">

    <aui:input name="<%= Constants.CMD %>" type="hidden"
        value="<%= Constants.UPDATE %>" />

    <aui:input name="redirect" type="hidden"
        value="<%= configurationRenderURL %>" />

    <aui:fieldset>

        <aui:input name="likedForRecommendation" label="Number of liked articles to reccomend from"
            value="<%= likedForRecommendation %>" />

        <aui:input name="instanceNumber" label="Instance number"
            value="<%= instanceNumber %>" />

        <aui:input name="totalNumberOfInstances" label="Total number of instances available on the given page"
            value="<%= totalNumberOfInstances %>" />

        <aui:select name="random" label="Is the recommendation random?"
            value="<%= random %>">
            <aui:option value="true">true</aui:option>
            <aui:option value="false">false</aui:option>
        </aui:select>


    </aui:fieldset>
    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>