# DOCKER-VERSION 0.3.4

FROM ubuntu:12.04

MAINTAINER wookoouk "wookoouk@gmail.com"

# update / install deps
RUN apt-get update
RUN apt-get -q -y install git unzip wget default-jre

# get Merit
RUN git clone https://github.com/wookoouk/Merit.git

# get play!
RUN wget http://downloads.typesafe.com/play/2.2.1/play-2.2.1.zip
RUN unzip play-2.2.1.zip
RUN export PATH=$PATH:/play-2.2.1/play

# open port 9000
EXPOSE  9000

ENTRYPOINT  ["/Merit"]