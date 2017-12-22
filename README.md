测试oracle-7-jdk版本
```sh
sudo docker run -it --rm chenmins/java-centos:oracle-7-jdk java -version
```
下载代码
```sh
git clone https://github.com/chenmins/fly.git
cd fly
chmod a+x *w
```
开发环境运行代码
```sh
sudo docker run -it --rm --name maven-build -p 8888:8080 -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/grailsw run-app
```
打包生产环境
```sh
sudo docker run -it --rm --name maven-build -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/grailsw war
```
测试运行生产环境
```sh
sudo docker run -it --rm --name maven-build -p 8888:8080 -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -jar /root/build/libs/fly-*.war
```
容器化运行生产环境
```sh
run -d --name fly -p 8888:8080 -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -jar /root/build/libs/fly-*.war
```