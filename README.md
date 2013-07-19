PlayBadges
==========

A web app for serving Mozilla Badges built on top of Play Framework 2

info gathered from:
https://github.com/mozilla/openbadges/wiki/Issuer-API
https://github.com/mozilla/openbadges/wiki/Assertions

How To
======

Install Play!(2.x):
	OSX:
		brew: 'brew install play'.
		or
		cli: Download the latest Play binary package and extract it in /Applications. Edit /etc/paths and add the line /Applications/play-1.2.4 (for example).
		
	Linux: Download the latest Play binary package, place it somewhere sensible, add the the location to your $PATH.
	
	Windows: Download the latest Play binary package, place it somewhere sensible, add the location to your %PATH%.
	
Start the app:
	'cd' to the location of the app and run 'play run'. This will start it in development mode.
	If you want to run this in production you can run 'play start' or play clean compile stage, you will be provided with a start up script inside 'target/stage'.
	
	