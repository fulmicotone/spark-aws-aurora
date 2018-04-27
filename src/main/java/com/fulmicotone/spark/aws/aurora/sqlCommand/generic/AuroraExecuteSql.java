package com.fulmicotone.spark.aws.aurora.sqlCommand.generic;

import com.fulmicotone.spark.aws.aurora.AuroraPropertiesSupplier;
import com.fulmicotone.spark.aws.aurora.PropertiesLabels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.BiConsumer;

public class AuroraExecuteSql implements BiConsumer<AuroraPropertiesSupplier,String> {


    private final Logger log= LoggerFactory.getLogger(AuroraExecuteSql.class);

    @Override
    public void accept(AuroraPropertiesSupplier auroraPropertiesSupplier,String sql) {

        PreparedStatement stm=null;

        try(Connection conn = DriverManager
                .getConnection(auroraPropertiesSupplier.get()
                        .getProperty(PropertiesLabels.Aurora.DATABASE_CONNECTION_STRING), auroraPropertiesSupplier.get())){

             stm = conn.prepareStatement(sql);
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
