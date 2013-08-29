<<<<<<< HEAD
play "start -Dhttp.port=80 -DapplyEvolutions.default=true"

#New Relic Monitoring
# play "start -Dhttp.port=80 -DapplyEvolutions.default=true -javaagent:/home/play/newrelic/newrelic.jar -Dnewrelic.bootstrap_classpath=true"
=======
target/start -Dhttp.port=80 -DapplyEvolutions.default=true

# New Relic support
# target/start -Dhttp.port=80 -DapplyEvolutions.default=true -javaagent:newrelic/newrelic.jar -Dnewrelic.bootstrap_classpath=true
>>>>>>> bc8c9e2509f8f5cc0774248ea8c9c54e92f344f3
