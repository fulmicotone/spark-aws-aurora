package com.fulmicotone.spark.aws.aurora;


import org.apache.commons.lang.ArrayUtils;
import org.apache.spark.sql.Row;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SparkRowIteratorToInputStream implements FunctionWithException<Iterator<Row>,InputStream> {
    @Override
    public InputStream apply(Iterator<Row> rows) throws Exception {

        SparkRowToString sparkRowToByte=new SparkRowToString();
        List<Byte> rowsAsBytes=new ArrayList<>();
        rows.forEachRemaining(row->{ rowsAsBytes.addAll(Arrays.asList(ArrayUtils.toObject(sparkRowToByte.apply(row).getBytes())));});
        Byte[] bytes = rowsAsBytes.toArray(new Byte[rowsAsBytes.size()]);
        ByteArrayInputStream is=new ByteArrayInputStream(ArrayUtils.toPrimitive(bytes));
        return is;
    }
}
