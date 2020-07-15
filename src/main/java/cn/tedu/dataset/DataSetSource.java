package cn.tedu.dataset;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

/*
针对DataSet 的source练习
fromElements 从E对象中读取数据
fromCollection 从C对象中读取数据
readTextFile 从文件对象中读取数据(支持从分布式文件系统中读取)
    readTextFile("hdfs://hadoop01:9000/dongcc/a.jsp")
 */
public class DataSetSource {

    public static void main(String[] args) throws Exception {
        //1.执行环境
        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
        //2.获取数据源
//        List<Integer> list=new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        DataSource<Integer> source=env.fromCollection(list);

        DataSource<String> source=env.readTextFile("data.txt");


        //3.转化数据
//        source.map(new MapFunction<Integer, Integer>() {
//
//            @Override
//            public Integer map(Integer value) throws Exception {
//                return value*10;
//            }
//        }).print();//4.输出数据

        source.map(new MapFunction<String, String>() {

            @Override
            public String map(String s) throws Exception {
                return s;
            }
        }).print();

        //5.触发执行

    }

}
