For run this app:

1) Run click house  
docker run -d --name some-clickhouse-server -p 8123:8123 --ulimit nofile=262144:262144 yandex/clickhouse-server  
2) Create table   
echo 'CREATE TABLE default.test (event_date Date,uid String,search_text String,long Float32,lat Float32,search_result String) ENGINE = MergeTree(event_date,(event_date,uid),8192)' | curl 'http://localhost:8123/' --data-binary @- &
3) OPTIONAL if you want to change CLICHOUSE_ADDR  
export CLICHOUSE_ADDR=my.clickhouse.com:8123  
4) Run app  
mvn clean spring-boot:run  

You can change max throughput of queue which consume events from api and produce ones in ClickHouse in runtime 
Run Java Mission Control, "$> $JAVA_HOME/bin/jmc" for example "$> /usr/lib/jvm/jdk1.8.0_172/bin/jmc"
in "JVM Browser" find your process -> click by arrow in the left side one ->
choice in "select list" "MBean server" -> double click -> in a new window (bellow there is tabs-panel)
find the tab "MBean Browser" click by one -> choice ClickHouse->JMX->and select 
ClickHousePropertyComponent or ClickHouseProducerCron to changed respectitive parameters  

Show current stats http://localhost:8080/get_stat_by_events  

Curl to api  
curl -H "Content-Type:application/json" \  
-d '{"json-rpc":2.0, "id":123,"method":"save_to_ch", "params":{"event_timestamp": 1535068800, "uid": "uid_123", "search_text": "Тестовы запрос \t в базу КХ\n", "long": 55.1111,"lat": 33.2222, "search_result": {"search_engine":"Test","values":["prod_uid_1","prod_uid_2","prod_uid_3","prod_uid_4"]}}}' \  
http://localhost:8080/send_event_msg  

Curl to ClickHouse  
curl 'http://localhost:8123/default?user=default&query=INSERT%20INTO%20test%20FORMAT%20TabSeparatedWithNamesAndTypes' -i -X POST -d "$(echo -ne 'event_date\tuid\tsearch_text\tlong\tlat\tsearch_result\nDate\tString\tString\tFloat32\tFloat32\tString\n2018-11-11\tuid_1\tТестовы запрос \\t в базу КХ\\n\t55.1111\t33.2222\t{"search_engine":"Test","values":["prod_uid_1","prod_uid_2","prod_uid_3","prod_uid_4"]}\n')"  
echo -ne 'event_date\tuid\tsearch_text\tlong\tlat\tsearch_result\nDate\tString\tString\tFloat32\tFloat32\tString\n2018-11-11\tuid_1\tТестовы запрос \\t в базу КХ\\n\t55.1111\t33.2222\t{"search_engine":"Test","values":["prod_uid_1","prod_uid_2","prod_uid_3","prod_uid_4"]}\n' | curl 'http://localhost:8123/default?user=default&query=INSERT%20INTO%20test%20FORMAT%20TabSeparatedWithNamesAndTypes' --data-binary @-   

Docker  
docker run -d --name some-clickhouse-server -p 8123:8123 --ulimit nofile=262144:262144 yandex/clickhouse-server  
echo 'CREATE TABLE default.test (event_date Date,uid String,search_text String,long Float32,lat Float32,search_result String) ENGINE = MergeTree(event_date,(event_date,uid),8192)' | curl 'http://localhost:8123/' --data-binary @-  

Clickhouse-client
docker run -it --rm --link some-clickhouse-server:clickhouse-server yandex/clickhouse-client --host clickhouse-server