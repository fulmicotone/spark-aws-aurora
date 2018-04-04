package com.fulmicotone.spark.aws.aurora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Supplier;

public class AuroraPropertiesSupplier implements Supplier<Properties>{


    private final AuroraEnv env;
    private final Logger log= LoggerFactory.getLogger(AuroraPropertiesSupplier.class);

    public AuroraPropertiesSupplier(AuroraEnv env){


        this.env=env;
    }


    @Override
    public Properties get() {

        Properties auroraProps= new Properties();
        try {

            auroraProps.load(Thread.currentThread().getContextClassLoader().getResource("aurora-"+env.toString()+".properties").openStream());
        } catch (IOException e) {
            log.error("properties file  not found",e.toString() );
        }

        return auroraProps;
    }
}
