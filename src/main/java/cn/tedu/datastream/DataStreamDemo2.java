package cn.tedu.datastream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class DataStreamDemo2 {
    public static void main(String[] args) throws Exception {

        //222222222222
        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source=env.fromElements("Operators transform one by one DataStream into a new DataStream");

        source.flatMap(new FlatMapFunction<String,Word>() {

            @Override
            public void flatMap(String value, Collector<Word> out) throws Exception {
                String[] words=value.split(" ");
                for(String s:words){
                    Word word=new Word();
                    word.setWord(s);
                    word.setCount(1);
                    out.collect(word);
                }
            }
        }).keyBy("word").reduce(new ReduceFunction<Word>() {

            @Override
            public Word reduce(Word value1, Word value2) throws Exception {
                value1.setCount(value1.getCount()+value2.getCount());
                return value1;
            }
        }).print();



        env.execute("DataStreamDemo2");


        //11111111111
//        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
//        DataStreamSource source=env.fromElements("Operators transform one by one DataStream into a new DataStream");
//
//        source.flatMap(new FlatMapFunction<String,Word>() {
//
//            @Override
//            public void flatMap(String value, Collector<Word> out) throws Exception {
//                String[] words=value.split(" ");
//                for(String s:words){
//                    Word word=new Word();
//                    word.setWord(s);
//                    word.setCount(1);
//                    out.collect(word);
//                }
//            }
//        }).print();
//
//        env.execute("DataStreamDemo2");


    }


}
