package cn.tedu.datastream;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import javax.sql.DataSource;

public class DataStreamDemo {
    public static void main(String[] args) throws Exception {

        //22222222222222
        //获取
        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        //加载
        DataStreamSource<String> source=env.socketTextStream("localhost",9999);
        //指定对数据的转换

        source.map(new MapFunction<String, User>() {

            @Override
            public User map(String value) throws Exception {
                String[] s=value.split("\\|");
                User user=new User();
                user.setId(s[0]);
                user.setName(s[1]);
                user.setAge(s[2]);
                user.setGender(s[3]);
                user.setCount(s[4]);

                return user;
            }
        }).keyBy("gender").sum("count").print();
        //指定将计算结果放在何处

        //触发程序执行
        env.execute("DataStreamDemo");



//        //111111111
//        //获取
//        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
//        //加载
//        DataStreamSource<Integer> source=env.fromElements(1,2,3,4,5,6,7,8,9,10);
//        //DataStreamSource<String> source=env.socketTextStream("localhost",9999);
//        //指定对数据的转换
//        //source.print().setParallelism(2);
//        source.print();
//        //指定将计算结果放在何处
//
//        //触发程序执行
//        env.execute("DataStreamDemo");
    }
}
