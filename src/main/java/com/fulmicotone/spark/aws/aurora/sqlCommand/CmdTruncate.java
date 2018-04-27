package com.fulmicotone.spark.aws.aurora.sqlCommand;

import com.fulmicotone.spark.aws.aurora.sqlCommand.generic.AuroraSqlCmd;

public class CmdTruncate extends AuroraSqlCmd
{


    public CmdTruncate(String tableName){ params.put("tableName",tableName); }


    @Override
    protected String sqlStm() { return "TRUNCATE TABLE  {tableName} RESTART IDENTITY;";}



}
