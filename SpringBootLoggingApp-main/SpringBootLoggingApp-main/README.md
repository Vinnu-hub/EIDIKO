
**log transfer from filebeat to elasticsearch  via logstash**



filebeat.exe -e -c filebeat.yml

logstash.bat -f ./config/logstash-sample.conf --config.reload.automatic

# Read input from filebeat by listening to port 5044 on which filebeat will send the data
input {
    beats {
	    #type => "test"
        port => "5044"
    }
}
 

output {
   
  stdout {
    codec => rubydebug
  }
 
  # Sending properly parsed log events to elasticsearch
  elasticsearch {
    hosts => ["https://localhost:9200/"]
	index => "logfromfilebeat-%{+YYYY.MM.dd}"
	user  => "elastic"
	password => "NlfGhKDDy=36FpvM*o0l"
	ssl => true
	ssl_certificate_verification => false
  }
}


filebeat.inputs:
- type: filestream
  id: my-filestream-id
  paths:
    - c:\Tools\logs\*.log
      
output:
  logstash:
    hosts: ["localhost:5044"]	
	
	
https://github.com/patilsushil1001/SpringBootLoggingApp
