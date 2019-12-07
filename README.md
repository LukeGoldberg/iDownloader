# iDownloader

this is a project used for collect data from database and download.

about collection.

custom collection class should implements ```CollectionService``` interface, and it's name should be added into ```CollectionEnum```. 

about download.

- using ```InputStreamResource``` for stream download, without fully loaded in memory;
- using ```HTTP```'s power, ```Etag```, ```If-None_Match```, ```Last-Modified``` and ```If-Modified-Since``` to avoid download repeatedly;
- throttle download speed, with ```RateLimiter``` in ```guava``` library.



