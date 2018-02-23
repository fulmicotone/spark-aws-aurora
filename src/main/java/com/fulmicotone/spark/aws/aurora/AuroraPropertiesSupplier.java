package com.fulmicotone.spark.aws.aurora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Supplier;

public class AuroraPropertiesSupplier implements Supplier<Properties>{

    Logger log= LoggerFactory.getLogger(AuroraPropertiesSupplier.class);
    @Override
    public Properties get() {

        Properties auroraProps= new Properties();
        try {

            auroraProps.load(Thread.currentThread().getContextClassLoader().getResource("aurora.properties").openStream());
        } catch (IOException e) {
            log.error("properties file  not found",e.toString() );
        }

        return auroraProps;
    }
}
