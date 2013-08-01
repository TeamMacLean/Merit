PlayBadges
==========

A web app for serving Mozilla Badges built on top of Play Framework 2

info gathered from:
https://github.com/mozilla/openbadges/wiki/Issuer-API
https://github.com/mozilla/openbadges/wiki/Assertions

How To
======

Start the app:
	'cd' to the location of the app and run 'play run'. This will start it in development mode.
	If you want to run this in production you can run 'play start' or play clean compile stage, you will be provided with a start up script inside 'target/stage'.

Create badge assertion via API:
	Create BASE64 Basic Auth header (key:"Authorization", value:"Basic [BASE64 String of username:password].
	Add header for 'recipient' (email address:String);
	Add header for 'evidence' (URL);
	Add header for 'badgeId' (Long)
	POST to [http://live-instace-url]/api 
	
	
TODO:
Check urls on POST to avoid 'invalid' url issues when adding the badge to backpack.
Signed badges.
Add users.
	