function deleteApplicationConfirm(application) {
	$('#applicationSelector').val(application);
	$('#actionSelector').val('confirm');
	$('#applicationProcessor').submit();
    $('#' + application).remove();
}

function deleteApplicationDeny(application) {
	$('#applicationSelector').val(application);
	$('#actionSelector').val('deny');
	$('#applicationProcessor').submit();
    $('#' + application).remove();
}