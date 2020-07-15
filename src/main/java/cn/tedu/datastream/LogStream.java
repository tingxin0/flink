package cn.tedu.datastream;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class LogStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers", "hadoop01:9092");
        properties.setProperty("group.id", "test");


        DataStreamSource<String> source=env.addSource(
                new FlinkKafkaConsumer<>("flux",new SimpleStringSchema(),properties));

        source.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return !value.equals("");
            }
        }).map(new MapFunction<String, Log>() {
            @Override
            public Log map(String value) throws Exception {
                String[] s=value.split("\\|");
                Log log=new Log();
                log.setUrl(s[0]);
                log.setUrlName(s[1]);
                log.setUvid(s[13]);
                log.setSsid(s[14].split("_")[0]);
                log.setSscount(s[14].split("_")[1]);
                log.setSstime(s[14].split("_")[2]);
                log.setCip(s[15]);
                log.setCount(1L);

                return log;
            }
        }).keyBy("urlName").timeWindow(Time.seconds(5))
                .sum("count").print().setParallelism(1);

        env.execute("LogStream");



    }
}
