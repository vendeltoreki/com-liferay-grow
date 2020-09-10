<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />-root"></div>

<aui:script require="<%= mainRequire %>">
	var likedForRecommendation = "<%= likedForRecommendation %>"
	var instanceNumber = "<%= instanceNumber %>"
	var totalNumberOfInstances = "<%= totalNumberOfInstances %>"
	var random = "<%= random %>"
	
	main.default('<portlet:namespace />-root', likedForRecommendation, instanceNumber, totalNumberOfInstances, random);
</aui:script>