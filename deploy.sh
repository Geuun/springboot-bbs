#!/bin/bash

# 실행조건
# 프로젝트 명 == 이미지 태그 == 컨테이너 이름 
# url, username, password => 환경변수
# sudo 권한 or docker 권한
# 입력예시 
# -> springboot url root 1234 8080:8080 Y

echo "========================================================================================="
echo "Please Enter The Params"
echo "<ProjectName> <url> <username> <password> <port> <-d : Y/N>"
read -p ">>" -a args
echo "========================================================================================="

if [ "${args[5]}" == "Y" ]
then
        DemonOpt="-d"
        echo "DemonOpt: ON"
elif [ "${args[5]}" = "N" ]
then
        DemonOpt=""
        echo "DemonOpt: OFF"
else
        exit 1
        echo "It's the wrong option Try Again"
fi

echo "========================================================================================="
echo "<ProjectName> <url> <username> <password> <port> <-d : Y/N>"
echo "Your params:" ${args[0]} ${args[1]} ${args[2]} ${args[3]} ${args[4]} ${DemonOpt}
echo "========================================================================================="
echo "Stop Existing Container"
docker stop ${args[0]}
docker rename ${args[0]} old${args[0]}
echo "========================================================================================="
echo "Entering The Project Directory"
cd ~/dev/${args[0]}
echo "========================================================================================="
echo "Pull repository"
git pull
echo "========================================================================================="
echo "Build image tag:" ${args[0]}
docker build -t ${args[0]} .
echo "========================================================================================="
echo "Build Container"
docker run ${DemonOpt} --name ${args[0]} -p ${args[4]} -e SPRING_DATASOURCE_URL=${args[1]} -e SPRING_DATASOURCE_USERNAME=${args[2]} -e SPRING_DATASOURCE_PASSWORD=${args[3]} ${args[0]}
echo "***********************************  Deploy is Done! **************************************"