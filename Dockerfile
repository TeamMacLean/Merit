# DOCKER-VERSION 0.3.4

from ubuntu:12.04

maintainer wookoouk "wookoouk@gmail.com"

# update / install deps
run apt-get update
run apt-get -q -y install curl git default-jre

# get Merit
cd /opt && git clone https://github.com/wookoouk/Merit.git

# start geefu
run start geefu