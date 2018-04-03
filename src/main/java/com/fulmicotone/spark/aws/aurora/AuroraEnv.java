package com.fulmicotone.spark.aws.aurora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Supplier;

public enum AuroraEnv {

    prod,
    dev,
    local
}
