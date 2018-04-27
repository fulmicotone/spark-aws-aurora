package com.fulmicotone.spark.aws.aurora.sqlCommand.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.BiFunction;

public class AuroraStatementCreator implements BiFunction<String,Map<String,Object>,String> {


    private final Logger log= LoggerFactory.getLogger(AuroraStatementCreator.class);

    @Override
    public String apply(String s, Map<String, Object> params) {


       for(Map.Entry<String, Object> entry:params.entrySet()){

           s=s.replace("{"+entry.getKey()+"}",String.valueOf(entry.getValue()));

        }


        return s;

    }
}
