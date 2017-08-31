# counter
Evaluation

Below steps to be followed follow for build, run and test the counter:

Download the counter repository

Counter boot application runs on port 8081. If conflicts with port number, change in application.properties.

Build the counter project
=========================
$ ./gradlew build

Run the counter project
=========================
$ ./gradlew bootRun

Task 1: Search the following texts, which will return the counts respectively.
=============================================================================
Request:
$ curl http://localhost:8081/counter-api/search -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H "Content-Type: application/json" -d '{"searchText": ["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -X POST

Response:
{"counts":{"Augue":7,"Duis":11,"123":0,"Pellentesque":6,"Sed":16,"Donec":8}}

Task 2: Provide the top 20 (as Path Variable) Texts, which has the highest counts in the Sample Paragraphs provided.
====================================================================================================================
Request:
curl http://localhost:8081/counter-api/top/20 -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H "Accept: text/csv"

Response:
eget|17,vel|17,sed|16,in|15,et|14,eu|13,ut|13,sit|12,nulla|12,metus|12,amet|12,id|12,ac|12,ipsum|11,duis|11,nec|11,vitae|11,at|11,dolor|10,non|10
