FROM harbor.lamic.cn/base/tomcat7:with-java8-v2
MAINTAINER zach <zach@lamic.cn>

WORKDIR /data
USER root
RUN apt-get -y update  && apt-get install -y supervisor
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/lz-hospital-service.war  /usr/local/tomcat/webapps
RUN unzip -o  /usr/local/tomcat/webapps/lz-hospital-service.war -d  /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/lz-hospital-service.war
ADD run.sh run.sh
RUN chmod +x run.sh

CMD ["/data/run.sh"]
EXPOSE 8080
