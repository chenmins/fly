����oracle-7-jdk�汾
```sh
sudo docker run -it --rm chenmins/java-centos:oracle-7-jdk java -version
```
���ش���
```sh
git clone https://github.com/chenmins/fly.git
cd fly
chmod a+x *w
```
�����������д���
```sh
sudo docker run -it --rm --name maven-build -p 8888:8080 -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/grailsw run-app
```
�����������
```sh
sudo docker run -it --rm --name maven-build -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk /root/grailsw war
```
����������������
```sh
sudo docker run -it --rm --name maven-build -p 8888:8080 -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -jar /root/build/libs/fly-*.war
```
������������������
```sh
run -d --name fly -p 8888:8080 -e JAVA_HOME=/usr/java/jdk1.7.0_80/ -v "$PWD":/root chenmins/java-centos:oracle-7-jdk java -jar /root/build/libs/fly-*.war
```