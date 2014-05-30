Merit
============

Merit is a web app for issuing [OpenBadges](http://openbadges.org/). Merit makes it very quick and easy to get up and running with issuing badges, it takes about 5 minutes to go from 0-to-live. The app is build on top of [Play! Framework](http://www.playframework.com/) and written in a mix of Java and Scala (with some javascript and css sprinkles).

#UPDATE: Merit now supports play 2.2.3


How to use
----------

Default login details:
email: martin.page@tsl.ac.uk
password: B0nF1rE

You can change the default email and password in: Merit/app/Global.java


###Images
Images must exist before a badge or issuer can be created.

###Issuers
An issuer is a company or institute that provides the badge and can be contacted to ensure that the badge is genuine.

###Alignments
An alignment is the requirement that must be fill before the badge can be given. These are useally standardised.

###Badges
The badge is a template for the assertion, only one of each exists.

###Assertions
Assertions are a individual object that links a user to a badge, it is the object that is created when a user earns a badge.

Docker
------

To test Merit out you can use Docker, Merit is available in the public Docker image server under wookoouk/merit. The conainter can be downloaded and run via `docker run wookoouk/merit -p 9000 -d`.

Installation
------------

###Requirements

* Git
* Java 1.6+
* Thats it!

###Download

`cd /opt`

`git clone https://github.com/wookoouk/Merit.git`

`cd Merit`

###Config

As default Merit will store all the database info in a H2 db, it is configured to save it in the running users home directory.
To change this look in conf/application.conf, I have commented out the option to just save to memory.

If you would like to use an alternative database, just google `play framework *DB NAME*` and it should come back with help.

For MySQL make the following changes:


in project/Build.scala, add:
`
"mysql" % "mysql-connector-java" % "5.1.18"
`

and in conf/application.conf modify:
`
db.default.driver=com.mysql.jdbc.Driver
db.default.url="mysql://root:secret@localhost/myDatabase"
`

Email settings are at the bottom of conf/application.conf, without a smtp server to hook it up to you will not be able to add new users.
For testing I suggest using the gmail smtp server.

###Running

To get Merit up and running for testing you can just execute `play run`, this will start in development mode on port 9000.

To run in production you should stage the app by running `play clean compile stage` and then start the server by running `nohup target/universal/stage/bin/merit -Dhttp.port=80 -Dhttps.port=443 -Dhttps.keyStore=/home/play/Merit/conf/server.keystore -Dhttps.keyStorePassword=importkey -Dapplication.forceHttps=true &`
