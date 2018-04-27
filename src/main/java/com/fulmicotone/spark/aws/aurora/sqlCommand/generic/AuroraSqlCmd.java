package com.fulmicotone.spark.aws.aurora.sqlCommand.generic;

import com.fulmicotone.spark.aws.aurora.AuroraPropertiesSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AuroraSqlCmd {


    private static Logger log= LoggerFactory.getLogger(AuroraSqlCmd.class);

    protected Map<String,Object> params=new HashMap<>();
    protected abstract  String sqlStm();




    public void apply(AuroraPropertiesSupplier propSupplier) throws  AuroraSqlException{


        try {
            new AuroraStatementCreator()
                    .andThen(preparedStm -> {
                        new AuroraExecuteSql()
                                .accept(propSupplier, preparedStm);
                        return "";
                    })
                    .apply(sqlStm(), params);

        }catch (Exception ex){


            log.error("errors in Aurora command "+ex.toString());

            throw new AuroraSqlException(ex.toString());
        }


    }


    }

