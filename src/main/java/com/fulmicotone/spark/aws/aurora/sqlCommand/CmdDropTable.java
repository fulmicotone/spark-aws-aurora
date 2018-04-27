package com.fulmicotone.spark.aws.aurora.sqlCommand;

import com.fulmicotone.spark.aws.aurora.AuroraPropertiesSupplier;
import com.fulmicotone.spark.aws.aurora.PropertiesLabels;
import com.fulmicotone.spark.aws.aurora.sqlCommand.generic.AuroraSqlCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.BiConsumer;

public class CmdDropTable extends AuroraSqlCmd {


    private final Logger log= LoggerFactory.getLogger(CmdDropTable.class);


    public CmdDropTable(String nameTable){


        params.put("tableName",nameTable);
    }



    @Override
    protected String sqlStm() {
        return "DROP TABLE IF EXISTS {tableName};";
    }
}
