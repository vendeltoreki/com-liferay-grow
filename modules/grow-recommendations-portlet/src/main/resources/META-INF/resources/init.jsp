
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="growrecommendationsportlet.configuration.GrowRecommendationsConfiguration" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    String mainRequire = (String)renderRequest.getAttribute("mainRequire");

    GrowRecommendationsConfiguration configuration =
        (GrowRecommendationsConfiguration)
        renderRequest.getAttribute(GrowRecommendationsConfiguration.class.getName());

        String likedForRecommendation = StringPool.BLANK;
        String instanceNumber = StringPool.BLANK;
        String totalNumberOfInstances = StringPool.BLANK;
        String random = StringPool.BLANK;

    if (Validator.isNotNull(configuration)) {
        likedForRecommendation =
            portletPreferences.getValue(
                "likedForRecommendation", configuration.likedForRecommendation());

        instanceNumber =
            portletPreferences.getValue(
                "instanceNumber", configuration.instanceNumber());

        totalNumberOfInstances =
            portletPreferences.getValue(
                "totalNumberOfInstances", configuration.totalNumberOfInstances());

        random =
            portletPreferences.getValue(
                "random", configuration.random());
    }
%>