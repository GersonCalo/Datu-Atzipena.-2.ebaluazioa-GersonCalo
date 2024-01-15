# Datuak gehitu
Este proyecto tiene como objetivo agregar datos a una base de datos de MongoDB desde un archivo CSV. El archivo "App.java" inserta todos los datos en una sola colección, mientras que "AppNationality.java" crea una colección por cada nacionalidad presente en el archivo CSV.

## Descripción del Archivo App.java

### 1. Conexión a la base de datos MongoDB:

- Se utiliza la clase `MongoClient` para establecer una conexión con la base de datos MongoDB en `localhost` y el puerto `27017`.

### 2. Selección de la base de datos y colección:

- Se selecciona la base de datos llamada "futbolariakDataBase" mediante `mongoClient.getDatabase("futbolariakDataBase")`.
- Se obtiene la colección "futbolariak" en la base de datos mediante `database.getCollection("futbolariak")`.

### 3. Lectura del archivo CSV:

- Se utiliza la biblioteca `opencsv` para leer un archivo CSV ubicado en la ruta especificada por `csvFilePath`.
- Se crea un objeto `CSVReader` para leer los datos del archivo.

### 4. Procesamiento de los registros del archivo CSV:

- Los registros se leen uno por uno después de haber leído todos los registros del archivo CSV.
- El primer registro (índice 0) contiene los nombres de las columnas.
- Para cada registro (a partir del índice 1), se crea un documento MongoDB (`Document`) que representará la información del futbolista.

### 5. Manejo de conversiones de tipos:

- Dependiendo de la columna, se realizan conversiones de tipos para adaptar los datos del CSV a los tipos de datos adecuados para MongoDB.
- Por ejemplo, las columnas que representan números enteros (`"age"`, `"overall_rating"`, etc.) se convierten a enteros, y las que representan números decimales (`"height_cm"`, `"weight_kgs"`, etc.) se convierten a valores de punto flotante.
- Se manejan casos especiales, como cuando un valor está vacío, asignándole un valor predeterminado (por ejemplo, 0.0).

### 6. Inserción en la colección MongoDB:

- Después de crear el documento MongoDB para un registro, se utiliza `collection.insertOne(document)` para insertar el documento en la colección MongoDB.

### 7. Impresión de información en la consola:

- Se imprime en la consola información sobre el proceso de procesamiento de registros, así como el documento MongoDB insertado en cada iteración.

### 8. Manejo de excepciones:

- Se utiliza un bloque `try-catch` para manejar posibles excepciones que puedan ocurrir durante la ejecución, como errores al leer el archivo o al conectarse a la base de datos.

## Descripción del Archivo AppNationality.java

### 1. Conexión a la base de datos MongoDB:

- Al igual que en `App.java`, se utiliza la clase `MongoClient` para establecer una conexión con la base de datos MongoDB en `localhost` y el puerto `27017`.

### 2. Selección de la base de datos:

- Se selecciona la base de datos llamada "futbolariakNationaityDataBase" mediante `mongoClient.getDatabase("futbolariakNationaityDataBase")`.

### 3. Lectura del archivo CSV:

- Al igual que en `App.java`, se utiliza la biblioteca `opencsv` para leer un archivo CSV ubicado en la ruta especificada por `csvFilePath`.
- Se crea un objeto `CSVReader` para leer los datos del archivo.

### 4. Procesamiento de los registros del archivo CSV:

- Los registros se leen uno por uno después de haber leído todos los registros del archivo CSV.
- El primer registro (índice 0) contiene los nombres de las columnas.

### 5. Declaración de la colección fuera del bucle:

- Se declara la colección (`MongoCollection<Document> collection`) fuera del bucle `for` para evitar múltiples instancias innecesarias.

### 6. Actualización de la colección según la nacionalidad:

- Se introduce un nuevo caso en el `switch` para la columna `"nationality"`. La colección se actualiza según el valor de este campo. Cada nacionalidad se convierte en el nombre de la colección en MongoDB.

### 7. Manejo de conversiones de tipos:

- Se realizan conversiones de tipos según las columnas, similar a `App.java`. Por ejemplo, se convierten las columnas numéricas a enteros o valores de punto flotante.

### 8. Inserción en la colección MongoDB:

- Después de crear el documento MongoDB para un registro, se utiliza `collection.insertOne(document)` para insertar el documento en la colección correspondiente a la nacionalidad del futbolista.

### 9. Impresión de información en la consola:

- Se imprime en la consola información sobre el proceso de procesamiento de registros, así como el documento MongoDB insertado en cada iteración.

### 10. Manejo de excepciones:

- Al igual que en `App.java`, se utiliza un bloque `try-catch` para manejar posibles excepciones que puedan ocurrir durante la ejecución, como errores al leer el archivo o al conectarse a la base de datos.

Este programa, al contrario que `App.java`, crea una colección separada para cada nacionalidad presente en el archivo CSV, utilizando el campo "nationality". Luego, inserta los documentos en la colección correspondiente a cada nacionalidad.
