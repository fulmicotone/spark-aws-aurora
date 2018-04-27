package com.fulmicotone.spark.aws.aurora.sqlCommand;

import com.fulmicotone.spark.aws.aurora.sqlCommand.generic.AuroraSqlCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CmdRenameTable extends AuroraSqlCmd {


    private final Logger log= LoggerFactory.getLogger(CmdRenameTable.class);


    public CmdRenameTable(String tableName,String tableNameRenamed){


        params.put("tableName",tableName);
        params.put("tableNameRenamed",tableNameRenamed);
    }

    @Override
    protected String sqlStm() {
        return "ALTER TABLE {tableName} RENAME TO {tableNameRenamed};";
    }
}
