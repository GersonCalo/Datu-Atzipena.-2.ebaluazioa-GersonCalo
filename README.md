
# REST APIren dokumentazioa

## 1. Hasierako datu-iturria

Gure REST API zerbitzurako hasierako datu-iturria MongoDB datu-base lokala da. Datuak prozesu honen bidez kargatu dira:

* 1-**Datuen jatorria:** Datuak [Kangel](https://www.kaggle.com/datasets/maso0dahmed/football-players-data/data) webgunetik lortzen dira, jatorrizko fitxategia CSV bat da.
* 2-**Datuen eraldaketa:** Datuak baldintzak betetzeko prozesatu ziren. Hori lortzeko, proiektu honetarako berariaz garatu nuen programa bat erabili nuen. Programak CSV fitxategi bat jasotzen du eta JSON formatu bihurtzen du.
* 3-**MongoDBn kargatzea:** Eraldatutako datuak MongoDB datu-basean txertatzeko, proiektu honetarako berariaz garatutako programa bat ere erabiltzen dut.


## 2. REST zerbitzua
![Screenshot swagger](img/Captura%20de%20pantalla%202024-02-12%20015828.png)
#### Endpointen taula

| Tipo de Petición | Endpoint   | Resultado   |
| -----------------|------------|-------------|
| PUT              | /futbolariak/updateValue | Balio bat eguneratu     |
| POST             | /futbolariak/newFutbolaria            | Erregistro berri bat sortzea |
| GET              | /futbolarlak/futbolarlakById        | Erregistro baten xehetasunak ID bidez |
| GET              | /futbolarlak/futbolarlakAll        | Erregistro guztien xehetasunak |
| GET              | /futbolariak/findNationalityAndPosition| Erregistroen xehetasunak, nazionalitatearen eta posizioaren arabera   |
| DELETE              | /Futbolariak/deleteByNameAndBirthDate| Ezabatu erregistroak izenaren eta urtebetetzearen arabera   |
|  DELETE            | /futbolariak/deleteNyId  |Ezabatu ID bidezko erregistro bat|




## 3.MongoDB
#### Zerbitzariaren xehetasunak
* MongoDB zerbitzaria: Localhost:8080
* Versión de MongoDB: 1.41.0
#### Datu basea
* Datu basearen izena: futbolariakDataBase
#### Bilduma
* Bilduma-ren izena: futbolariak
* Dokumentuak: futbolarien informasioa gordetzen du(Izena, Izen osoa, jaiotse data, adina, ...)

## Martxan jartzea
    1- Klonatu repositoroa hemendik.

    2- Konfiguratu MongoDB datu-baserako konexioa application.properties-en.
    3- Aplikazioa exekutatu
