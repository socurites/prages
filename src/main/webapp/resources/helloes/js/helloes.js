var toggleModal = function(id, show) {
	$('#' + id).modal(show);
};

var toggleCollapse = function(id) {
	if ( $('#' + id).hasClass('collapsed-box') ) {
		$('#' + id).removeClass('collapsed-box');
		$('#' + id + " > .box-body").show();
	} else {
		$('#' + id).addClass('collapsed-box');
		$('#' + id + " > .box-body").hide();
	}
};