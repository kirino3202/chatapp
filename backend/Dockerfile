FROM ubuntu:22.04

RUN apt update -y
RUN apt install -y openjdk-17-jdk
RUN apt install -y git

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64

RUN useradd -m -d /home/devcontainer -s /bin/bash devcontainer
USER devcontainer
