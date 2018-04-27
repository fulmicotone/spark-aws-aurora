package com.fulmicotone.spark.aws.aurora;

import org.apache.spark.sql.SparkSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SparkAwsAuroraTest {


    @Test
    public void SparkRowToStringTest(){


        AtomicInteger i= new AtomicInteger();
        List<String> expectedResults = Arrays.asList("\"Qal eh-ye Now\"\t\"Qal eh-ye\"\t\"34.98300013\"\t\"63.13329964\"\t\"2997\"\t\"Afghanistan\"\t\"AF\"\t\"AFG\"\t\"Badghis\"\n",
                "\"Chaghcharan\"\t\"Chaghcharan\"\t\"34.5167011\"\t\"65.25000063\"\t\"15000\"\t\"Afghanistan\"\t\"AF\"\t\"AFG\"\t\"Ghor\"\n",
                "\"Lashkar Gah\"\t\"Lashkar Gah\"\t\"31.58299802\"\t\"64.35999955\"\t\\N\t\"Afghanistan\"\t\"AF\"\t\"AFG\"\t\"Hilmand\"\n");

        SparkRowToString fn=new SparkRowToString();

       SparkSession.builder().master("local").getOrCreate()
                .read()
                .option("header",true)
                .csv(SparkAwsAuroraTest.class.getClassLoader().getResource("cities.csv").getPath())
                .collectAsList().stream()
                .map(fn::apply).collect(Collectors.toList())
                .forEach(rowAsString->{

                    Assert
                            .assertTrue("unexpectedValue found "+rowAsString+" expected "+
                                            expectedResults.get(i.get()),
                                    rowAsString.equals(expectedResults.get(i.get())));
                    i.getAndIncrement();
                });
        


       


    }


}
