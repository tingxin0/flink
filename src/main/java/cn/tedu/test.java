package cn.tedu;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

public class test {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<Integer> source = env.fromElements(1, 2, 3, 4, 5);
        source.map(new MapFunction<Integer, Integer>() {
            public Integer map(Integer value) throws Exception {
                return value*10;
            }
        }).print();

    }
}

