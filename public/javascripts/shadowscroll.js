var MAX_SCROLL = 8;
var range;

$(window).scroll(
		function() {
			var height = $(window).height();
			var scrollTop = $(window).scrollTop() / 4;

			if (scrollTop < MAX_SCROLL) {
				range = scrollTop;
			} else {
				range = MAX_SCROLL;
			}

			$('.navbar').css("margin-top", -range + "px");
			$('.navbar').css("boxShadow",
					"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
			$('.navbar').css("-webkit-box-shadow",
					"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
			$('.navbar').css("-moz-box-shadow",
					"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
			$('.navbar').css("boxShadow",
					"0 0 0 " + range + "px rgba(0, 0, 0, 0.25)");
		});