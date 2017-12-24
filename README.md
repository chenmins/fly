**测试oracle-7-jdk版本**
```sh
sudo docker run -it --rm chenmins/java-centos:oracle-7-jdk java -version
```
**github下载并安装支持Grails3.3的rest插件代码**
```sh
git clone https://github.com/chenmins/grails-jaxrs.git
chmod -R  a+x grails-jaxrs/*
sudo docker run -it --rm --name maven-build -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -w /root/grails-jaxrs -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/grails-jaxrs/gradlew install
```
**github下载fly代码**
```sh
git clone https://github.com/chenmins/fly.git
sudo chmod a+x fly/*
```
**开发环境运行代码**
```sh
sudo docker run -it --rm --name maven-build -p 8080:8080 -w /root/fly -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/fly/grailsw run-app
```
**打包生产环境**
```sh
sudo docker run -it --rm --name maven-build -w /root/fly -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/fly/grailsw war
```
**测试运行生产环境**
```sh
sudo docker run -it --rm --name maven-build -p 8080:8080 -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -jar /root/fly/build/libs/fly-0.1.war
```
**容器化运行生产环境**
```sh
sudo docker run -d --name fly -p 8080:8080 -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -jar /root/fly/build/libs/fly-0.1.war
```
**访问生产环境**

[http://127.0.0.1:8080/](http://127.0.0.1:8080/)