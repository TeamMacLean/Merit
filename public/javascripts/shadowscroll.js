var MAX_SCROLL = 8;
var range;

var navbarTop = $('.navbar .brand').css("padding-top").replace(/[^-\d\.]/g, '');
var navbarBottom = $('.navbar .brand').css("padding-bottom").replace(
		/[^-\d\.]/g, '');

var brandTop = $('.navbar .brand').css("padding-top").replace(/[^-\d\.]/g, '');
var brandBottom = $('.navbar .brand').css("padding-bottom").replace(
		/[^-\d\.]/g, '');

$(window)
		.scroll(
				function() {
					var height = $(window).height();
					var scrollTop = $(window).scrollTop() / 4;
					if (window.innerWidth >= 995) {
						if (scrollTop < MAX_SCROLL) {
							range = scrollTop;
						} else {
							range = MAX_SCROLL;
						}

						$('.navbar .nav > li > a').css("padding-top",
								navbarTop - range + "px");
						$('.navbar .nav > li > a').css("padding-bottom",
								navbarBottom - range + "px");

						$('.navbar .brand').css("padding-top",
								brandTop - range + "px");
						$('.navbar .brand').css("padding-bottom",
								brandBottom - range + "px");

						$('.navbar').css("boxShadow",
								"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
						$('.navbar').css("-webkit-box-shadow",
								"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
						$('.navbar').css("-moz-box-shadow",
								"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
					}
				});