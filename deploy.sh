#!/bin/bash

###############################################################################################
##
##  Record for me in the future (:D)
##  If you're still building and using the server on RaspberryPi
##  Check the contents below
##  Server architecture : arm64(arrch64) == arm64/v8
##  So we need an image that supports arm64/v8
##  
##  Example)
##  Check https://hub.docker.com/_/gradle OS/ARCH tab :)
##  (If the tool you are going to use does not support arm, Search on the DockerHub)
##  Then to use CI/CD you have to build multi-cpu architecture for every architecture !
##
###############################################################################################

FROM 7.6-jdk11-jammy AS builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

# APP
FROM openjdk:11.0-slim
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# root 대신 nobody 권한으로 실행
USER nobody
ENTRYPOINT [                                                \
   "java",                                                 \
   "-jar",                                                 \
   "-Djava.security.egd=file:/dev/./urandom",              \
   "-Dsun.net.inetaddr.ttl=0",                             \
   "app.jar"              \
]


# deploy.sh
# 실행조건
# deploy.sh 파일이 Project Root Directory에 위치
# 프로젝트 명 == 이미지 태그 == 컨테이너 이름
# url, username, password => 환경변수
# sudo 권한 or docker 권한
# {
#     <ProjectName> : ${args[0]}
#     <url> : ${args[1]}
#     <username> : ${args[2]}
#     <password> : ${args[3]}
#     <port> : ${args[4]}
#     <-d> : ${args[5]}
# }
# 입력예시
# -> springboot url root 1234 8080:8080 Y

# params 입력받기
echo "**********************************************"
echo "Please Enter The Params"
read -p "ProjectName : "  ProjectName
read -p "DB_URL : " DB_URL
read -p "DB_UserName : " DB_UserName
read -p "DB_Password : " DB_Password
read -p "Port : " Port
read -p "Daemon <Y/N> : " Daemon
echo "**********************************************"

# 데몬 조건문
if [ "${Daemon}" == "Y" ]
then
        DemonOpt="-d"
        echo "********************"
        echo "*** DemonOpt: ON ***"
        echo "********************"
elif [ "${Daemon}" == "N" ]
then
        DaemonOpt=""
        echo "*********************"
        echo "*** DemonOpt: OFF ***"
        echo "*********************"
else
        exit 1
        echo "It's the wrong option Try Again"
fi


# 최종 입력 값
echo "*****************************************************************"
echo "*** <ProjectName> <DB_URL> <DB_UserName> <DB_Password> <Port> <Deamon> ***"
echo "*****************************************************************"
echo ""
echo "Your params >>" ${ProjectName} ${DB_URL} ${DB_UserName} ${DB_Password} ${Port}  ${DemonOpt}
echo ""

# 기존 컨테이너 중지
echo "*******************************"
echo "*** Stop Existing Container ***"
echo "*******************************"
docker stop ${ProjectName}


# 기존 컨테이너 이름 변경
docker rename ${ProjectName} old${ProjectName}


# # 프로젝트 폴더 진입 (절대경로)
# echo "**************************************"
# echo "*** Entering The Project Directory ***"
# echo "**************************************"
# cd ~/dev/${ProjectName}


# git pull
echo "***********************"
echo "*** Pull repository ***"
echo "***********************"
git pull


# 이미지 빌드
ImageTag=${ProjectName}
echo "*******************"
echo "*** Build image ***"
echo "*******************"
docker build -t ${ImageTag} .


# 컨테이너 빌드 docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
echo "***********************"
echo "*** Build Container ***"
echo "***********************"
docker run ${DemonOpt} \
--name ${ProjectName} \
-p ${Port} \
-e SPRING_DATASOURCE_URL=${DB_URL} \
-e SPRING_DATASOURCE_USERNAME=${DB_UserName} \
-e SPRING_DATASOURCE_PASSWORD=${DB_Password} \
${ImageTag}

# 완료 문장
echo "##########################################################################################"
echo "###################################  Deploy is Done! #####################################"
echo "##########################################################################################"




