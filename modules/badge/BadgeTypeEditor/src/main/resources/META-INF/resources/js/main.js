function addBadgeType() {
	$.validate({
		form : '#_badgetypeeditor_badgeForm',
		modules : 'html5, file',
	});
}

function addBadgeGroup() {
	$.validate({
		form : '#_badgetypeeditor_badgeGroupForm',
		modules : 'html5',
	});
}