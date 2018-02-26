#!/bin/bash  
cd `dirname $0`  
BIN_DIR=`pwd`  
cd ..  
DEPLOY_DIR=`pwd`  
CONF_DIR=$DEPLOY_DIR/conf  

SERVER_NAME=${dubbo.application.name}
SERVER_PROTOCOL=${dubbo.protocol.name}  
SERVER_PORT=${dubbo.protocol.port}
LOG_PATH=${logback.path}
  
if [ -z "$SERVER_NAME" ]; then  
    SERVER_NAME=`hostname`  
fi  
  
PIDS=`ps -f | grep java | grep "$CONF_DIR" |awk '{print $2}'`  
if [ -n "$PIDS" ]; then  
    echo "ERROR: The $SERVER_NAME already started!"  
    echo "PID: $PIDS"  
    exit 1  
fi  
  
if [ -n "$SERVER_PORT" ]; then  
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`  
    if [ $SERVER_PORT_COUNT -gt 0 ]; then  
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"  
        exit 1  
    fi  
fi  
  
if [ ! -d $LOG_PATH ]; then  
    mkdir -p $LOG_PATH  
fi  
STDOUT_FILE=$LOG_PATH/stdout.log  
  
LIB_DIR=$DEPLOY_DIR/lib  
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`  
  
JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "  
JAVA_DEBUG_OPTS=""  
if [ "$1" = "debug" ]; then  
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "  
fi  
JAVA_JMX_OPTS=""  
if [ "$1" = "jmx" ]; then  
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "  
fi  
JAVA_MEM_OPTS=""  
BITS=`java -version 2>&1 | grep -i 64-bit`  
if [ -n "$BITS" ]; then  
    JAVA_MEM_OPTS=" -server -Xmx${vm.64bit.xmx} -Xms${vm.64bit.xms} -Xmn${vm.64bit.xmn} -Xss${vm.64bit.xss} -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "  
else  
    JAVA_MEM_OPTS=" -server -Xmx${vm.32bit.xmx} -Xms${vm.32bit.xms} -XX:SurvivorRatio=2 -XX:+UseParallelGC "  
fi  
  
echo -e "Starting the $SERVER_NAME ...\c"  
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS ${main.class} > $STDOUT_FILE 2>&1 &  
  
COUNT=0  
while [ $COUNT -lt 1 ]; do      
    echo -e ".\c"  
    sleep 1   
    if [ -n "$SERVER_PORT" ]; then  
        if [ "$SERVER_PROTOCOL" == "dubbo" ]; then  
            COUNT=`echo status | nc -i 1 127.0.0.1 $SERVER_PORT | grep -c OK`  
        else  
            COUNT=`netstat -an | grep $SERVER_PORT | wc -l`  
        fi  
    else  
        COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`  
    fi  
    if [ $COUNT -gt 0 ]; then  
        break  
    fi  
done  
  
echo "OK!"  
PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`  
echo "PID: $PIDS"  
echo "STDOUT: $STDOUT_FILE"  
