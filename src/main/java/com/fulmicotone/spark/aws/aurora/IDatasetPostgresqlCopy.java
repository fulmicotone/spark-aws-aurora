package com.fulmicotone.spark.aws.aurora;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

@FunctionalInterface
public interface IDatasetPostgresqlCopy<T extends AuroraPropertiesSupplier,X extends String, E extends Dataset<Row>> {


    void save(T auroraProperties,X tableName, E inputDataset);
}
