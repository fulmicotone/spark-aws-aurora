package com.fulmicotone.spark.aws.aurora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AuroraTruncate implements BiConsumer<AuroraPropertiesSupplier,String> {

    
    private final Logger log= LoggerFactory.getLogger(AuroraTruncate.class);

    @Override
    public void accept(AuroraPropertiesSupplier auroraPropertiesSupplier,String tableName) {

        PreparedStatement stm=null;

        try(Connection conn = DriverManager
                .getConnection(auroraPropertiesSupplier.get()
                        .getProperty(PropertiesLabels.Aurora.DATABASE_CONNECTION_STRING), auroraPropertiesSupplier.get())){


             stm = conn.prepareStatement("TRUNCATE TABLE " + tableName + " RESTART IDENTITY;");
             stm.execute();


        }catch (Exception e){
            log.error(e.toString());
        }finally {
            try {
                stm.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
    }
}
