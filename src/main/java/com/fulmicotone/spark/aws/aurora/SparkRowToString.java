package com.fulmicotone.spark.aws.aurora;


import org.apache.spark.sql.Row;
import scala.collection.JavaConversions;

import java.util.function.Function;
import java.util.stream.Collectors;

public class SparkRowToString implements Function<Row,String> {
    @Override
    public String apply(Row row)  {

        return JavaConversions.seqAsJavaList(row.toSeq())
                .stream().map((x) -> {
                    if (x == null) { return "\\N"; } else {
                        return "\"" + x.toString().replaceAll("\"", "\"\"") + "\"";
                    }
                }).collect(Collectors.joining("\t")) + "\n";


    }
}
