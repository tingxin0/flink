package cn.tedu.dataset;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;

/*
* Flink DataSet 入门案例
 */
public class DataSetDemo1 {
    public static void main(String[] args) throws Exception {
        //1.执行环境
        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
        //2.获取数据源
        DataSource<String> source=env.fromElements("hadoop","flink","hive","kafka","flink");
        //3.转化数据
        source.map(new MapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return new Tuple2<>(value,1); //("hadoop",1)
            }
        }).groupBy(0).sum(1).print();//4.输出数据

        //5.提交执行(暂时不管)
    }

}
