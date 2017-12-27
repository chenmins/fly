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
**修改电子邮箱地址和数据库**
```sh
sudo vi fly/grails-app/conf/application.yml
```
**开发环境运行代码**
```sh
sudo docker run -it --rm --name maven-build -w /root/fly -e JAVA_OPTS="-Dfile.encoding=UTF-8" -e JAVA_OPTS="-Dfile.encoding=UTF-8" -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/fly/grailsw run-app
```
**打包生产环境**
```sh
sudo docker run -it --rm --name maven-build -w /root/fly -e JAVA_OPTS="-Dfile.encoding=UTF-8" -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/fly/grailsw war
```
**测试运行生产环境**
```sh
sudo docker run -it --rm --name maven-build -p 8080:8080 -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -Dfile.encoding=UTF-8 -Duser.timezone=GMT+08 -Xmx900m -Xms200m -Xss1024k -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxPermSize=128m  -jar /root/fly/build/libs/fly-0.1.war
```
**容器化运行生产环境**
```sh
sudo docker run -d --name fly -p 8080:8080 -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -Dfile.encoding=UTF-8 -Duser.timezone=GMT+08 -Xmx900m -Xms200m -Xss1024k -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxPermSize=128m -jar /root/fly/build/libs/fly-0.1.war
```
**访问生产环境**
[http://127.0.0.1:8080/](http://127.0.0.1:8080/)

**演示环境**
[http://www.oldcomputer.com.cn](http://www.oldcomputer.com.cn)

**参考资料**

http://docs.grails.org/snapshot/guide/single.html

http://gorm.grails.org/6.0.x/hibernate/manual/

http://plugins.grails.org/

http://docs.grails.org/latest/guide/theWebLayer.html#cors

http://budjb.github.io/grails-jaxrs/3.x/latest/guide/introduction.html

https://jersey.github.io/documentation/latest/user-guide.html

http://blog.csdn.net/sikaodeluwei/article/details/18005845

http://blog.csdn.net/qq_28562059/article/details/52609773

http://blog.csdn.net/change_on/article/details/71191894

https://www.cnblogs.com/zsychanpin/p/7118659.html