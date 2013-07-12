$(document).ready(function () {

	 $(function(){

		 var url = window.location.pathname;  
		 var activePage = url.substring(url.lastIndexOf('/'));

	     $(".nav").children("li").each(function(){
	    	 var pageurl = $(this).children("a:first-child").attr("href");
	    	 var pagelink = pageurl.substring(pageurl.lastIndexOf('/'));
	         if(pagelink == activePage){
	             $(this).addClass("active");
	         }
	     });
	     
	  });
  
});