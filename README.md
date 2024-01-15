
# Datu atzipeneko 2. ebaluaketako bakarkako lana. REST API baten garapena. Greson Calo

Proiektuaren helburu nagusia Mongo datu-base bateko datuak atzitzeko Rest API bat garatzea izango da, Spring Java teknologia erabiliz.


## Datu Basea

Datuak mongo datu-base batetik kudeatuko ditugu. Horretarako Mongo zerbitzariaren MongoDBCompass GUI ofiziala erabiliko ditugu lana erosoago egiteko.
## Rest API
| Endpoint  | Funtzioa |
| ------------- | ------------- |
| localhost:8080/futbolariak/updateValue?futbolariId=123&newValue=100| futbolari baten balioa aldatu.|
| localhost:8080/futbolariak/newfutbolaria?name=messi&full_name=Lionel Andrés Messi Cuccittini&age=31&value_euro=110500000| futbolari baten oinarrizko erregistroa sortu|
| localhost:8080/futbolariak/futbolariakNationality?nationality=spain| nazio hori duten futbolari-ak erakutsi.|
| localhost:8080/futbolariak/futbolariakById?futbolariId=123| futbolari zehatz bat erakutsi id-an oinarrituta.|
| localhost:8080/futbolariak/futbolariakAll| futbolari guztien erregistroak erakutsi.|
| localhost:8080/futbolariak/deletefutbolariName?name=messi| izen hori duten futbolari-ak ezabatu.|
| localhost:8080/futbolariak/deletefutbolariById?futbolariId=123| futbolari zehatz bat ezabatu id-an oinarrituta.|