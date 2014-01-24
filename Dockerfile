# DOCKER-VERSION 0.3.4

FROM ubuntu:12.04

MAINTAINER wookoouk "wookoouk@gmail.com"

# update / install deps
RUN apt-get update
RUN apt-get -q -y install git unzip wget default-jre

# get Merit
RUN git clone https://github.com/wookoouk/Merit.git
# RUN ln -s /Merit/start-dev /usr/local/bin/start-dev

# get play!
RUN wget http://downloads.typesafe.com/play/2.2.1/play-2.2.1.zip
RUN unzip -d /opt play-2.2.1.zip
RUN chmod +x /opt/play-2.2.1/play
RUN ln -s /opt/play-2.2.1/play /usr/local/bin/play
RUN rm play-2.2.1.zip

# open port 9000
EXPOSE  9000

CMD ["/Merit/start-dev"]