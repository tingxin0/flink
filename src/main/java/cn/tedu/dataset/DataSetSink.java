package cn.tedu.dataset;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

/*
针对DataSet的sink练习
要求从Collection中读取数据，并输出到本地文件writeAsText()
 */
public class DataSetSink {
    public static void main(String[] args) throws Exception {
        //1.获得环境
        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
        //2.加载，创建初始数据
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        DataSource<Integer> source=env.fromCollection(list);
        //3.指定对此数据的转换

        //4.指定将计算结果放在何处
        source.writeAsText("result.txt");
        //5.触发执行程序
        env.execute("DataSetSink");

    }
}
