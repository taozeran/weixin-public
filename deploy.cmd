@rem
call mvn clean package -Dmaven.test.skip=true -Ptx
scp ./target/demo-0.0.1-SNAPSHOT.jar "root@%SERVER_IP%:/home/app.jar"
ssh "root@%SERVER_IP%" "kill -9 `pidof java`"
ssh "root@%SERVER_IP%" "java -jar /home/app.jar &"