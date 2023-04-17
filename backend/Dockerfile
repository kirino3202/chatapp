FROM ubuntu

RUN apt update -y
RUN apt install -y openjdk-11-jdk
RUN apt install -y git

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64

RUN useradd -m -d /home/devcontainer -s /bin/bash devcontainer
USER devcontainer
