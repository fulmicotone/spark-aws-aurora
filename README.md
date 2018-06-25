## **Java Spark Aurora Writer**
### Utility to write  spark dataset on postgresql database


There are two way to write dataset on postgresql  through the spark api or through the postgresql copy function


## Spark api 

 ```
 dataset..mode(SaveMode.Append)
               .jdbc(connectionString,
                       ,auroraProperties);
                       
```

 ### Save Mode
 
 - Append,    will append the new records to existent table
 - Overwrite, will DROP the table and replace it with these new values.
 - Ignore,    will the table exist or in case of error will skip the operation


## Spark And postgresql COPY 

 ```
    new DatasetPostgresqlCopy().save(new AuroraPropertiesSupplier(),"tableName",dataset);
                       
```                                
                       
                       
           
           
 ### Aurora properties    
 
 ```
 url=jdbc:postgresql://addressDatabase:5432/schemaname
 user=username
 password=password
 driver=org.postgresql.Driver

 ```
  
                       
                       
