# iDownloader

this is a project used for collect data from database and download it in Excel format.

## about collection.

custom collection class should implements ```CollectionService``` interface, and it's name should be added into ```CollectionEnum```. 

## about download.

- using ```InputStreamResource``` for stream download, without fully loaded in memory;
- using ```HTTP```'s header, ```Etag```, ```If-None_Match```, ```Last-Modified``` and ```If-Modified-Since``` to avoid download repeatedly;
- throttle download speed, with ```RateLimiter``` in ```guava``` library.

## test(on one 1 core, 1 GB memory, 1MB bandwidth cloud machine)

when downloading one *206B* file.

> ab -n 1000 -c 100 -p 'param.txt' -T 'application/json;charset=utf8' http://localhost:9068/download/file/5e11b06470dc850850bfa131

``` 
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            9068

Document Path:          /download/file/5e11b06470dc850850bfa131
Document Length:        19 bytes

Concurrency Level:      100
Time taken for tests:   8.246 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      233000 bytes
Total body sent:        188000
HTML transferred:       19000 bytes
Requests per second:    121.26 [#/sec] (mean)
Time per request:       824.649 [ms] (mean)
Time per request:       8.246 [ms] (mean, across all concurrent requests)
Transfer rate:          27.59 [Kbytes/sec] received
                        22.26 kb/s sent
                        49.86 kb/s total

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0  100  74.0    109     254
Processing:     9  713 1411.9    252    4949
Waiting:        9  663 1415.5    197    4905
Total:         76  814 1380.4    368    4963

Percentage of the requests served within a certain time (ms)
  50%    368
  66%    380
  75%    413
  80%    434
  90%   4923
  95%   4946
  98%   4949
  99%   4952
 100%   4963 (longest request)
 ```

when downloading one *283MB* file.

> ab -n 1000 -c 100 -p 'param.txt' -T -k -s 6000 'application/json;charset=utf8' http://localhost:9068/download/file/5e11b06470dc850850bfa131

```
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            9068

Document Path:          /download/file/5e11b06470dc850850bfa131
Document Length:        512 bytes

Concurrency Level:      100
Time taken for tests:   356.059 seconds
Complete requests:      1000
Failed requests:        0
Keep-Alive requests:    1000
Total transferred:      731000 bytes
Total body sent:        212000
HTML transferred:       512000 bytes
Requests per second:    2.81 [#/sec] (mean)
Time per request:       35605.872 [ms] (mean)
Time per request:       356.059 [ms] (mean, across all concurrent requests)
Transfer rate:          2.00 [Kbytes/sec] received
                        0.58 kb/s sent
                        2.59 kb/s total

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    2   5.0      0      19
Processing: 35306 35591 141.8  35563   35946
Waiting:    35306 35591 141.9  35563   35946
Total:      35306 35592 140.7  35563   35946

Percentage of the requests served within a certain time (ms)
  50%  35563
  66%  35638
  75%  35675
  80%  35720
  90%  35812
  95%  35862
  98%  35900
  99%  35916
 100%  35946 (longest request)
```
