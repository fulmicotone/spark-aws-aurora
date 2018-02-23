package com.fulmicotone.spark.aws.aurora;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class  DatasetPostgresqlCopy implements  IDatasetPostgresqlCopy< AuroraPropertiesSupplier,String,Dataset<Row>> {



    @Override
    public void save(AuroraPropertiesSupplier propsProvider,String tableName, Dataset<Row> inputDataset) throws DatasetPostgresqlCopyException {


        SparkRowIteratorToInputStream srtois=new SparkRowIteratorToInputStream();

        Properties auroraProperties=propsProvider.get();

        inputDataset.foreachPartition(r->{

            try(Connection conn = DriverManager
                    .getConnection(auroraProperties
                            .getProperty(PropertiesLabels.Aurora.DATABASE_CONNECTION_STRING), auroraProperties)){

                CopyManager cm = new CopyManager(conn.unwrap(BaseConnection.class));

                cm
                        .copyIn("COPY " + tableName+" FROM STDIN WITH (NULL '\\N', FORMAT CSV, DELIMITER E'\\t')",
                                srtois.apply(r));


            }catch (Exception e){


               e.printStackTrace();
            }

        });


    }
}
