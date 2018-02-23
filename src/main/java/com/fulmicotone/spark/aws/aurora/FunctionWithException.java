package com.fulmicotone.spark.aws.aurora;

@FunctionalInterface
public interface FunctionWithException<T, R> {


    R apply(T t) throws Exception;
}
