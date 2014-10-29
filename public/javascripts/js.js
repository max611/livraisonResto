$( document ).ready(function() {
	$("#translateButton").click( function(){
		jsRoutes.controllers.Application.changeLanguage();
	});
});

