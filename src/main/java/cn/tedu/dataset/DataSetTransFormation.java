package cn.tedu.dataset;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.util.Collector;

/*

 */
public class DataSetTransFormation {

    public static void main(String[] args) throws Exception {

        //888888888
        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> source1= env.fromElements("1|陈子书|男","2|齐雷|男","3|刘佩霞|女");
        DataSource<String> source2=env.fromElements("1|总监","2|程序员","3|经理");

        MapOperator<String,Tuple3<String,String,String>> map1=source1.map(
                new MapFunction<String, Tuple3<String,String,String>>() {
            @Override
            public Tuple3<String, String, String> map(String value) throws Exception {
                String[] s=value.split("\\|");

                return new Tuple3<>(s[0],s[1],s[2]);
            }
        });

        MapOperator<String,Tuple2<String,String>> map2=source2.map(
                new MapFunction<String, Tuple2<String,String>>() {
            @Override
            public Tuple2<String, String> map(String value) throws Exception {
                String[] s=value.split("\\|");
                return new Tuple2<>(s[0],s[1]);
            }
        });


        map1.join(map2).where(0).equalTo(0).
                projectFirst(0,1,2).projectSecond(1).print();


//        //7777777777777777
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<Integer> source= env.fromElements(1,2,3,4,4,4,4);
//        source.distinct().print();


//        //666666666666
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<Tuple2<String,Integer>> source= env.fromElements(new Tuple2<String,Integer>("wutx1",1),new Tuple2<String,Integer>("wutx2",2));
//        source.sum(1).print();
//        source.min(1).print();

//        //5555555555
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<Integer> source= env.fromElements(1,2,3,4,5);
//        source.reduceGroup(new GroupReduceFunction<Integer, Integer>() {
//
//            @Override
//            public void reduce(Iterable<Integer> values, Collector<Integer> out) throws Exception {
//                Integer sum=0;
//                for(Integer value:values){
//                    sum+=value;
//                    out.collect(sum);
//                }
//            }
//        }).print();


//        //4444444444444444
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<Integer> source= env.fromElements(1,2,3,4,5,6,7,8);
//        source.reduce(new ReduceFunction<Integer>() {
//            @Override
//            public Integer reduce(Integer value1, Integer value2) throws Exception {
//                System.out.println(value1+value2);
//                return value1+value2;
//            }
//        }).print();


//        //33333333333
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<String> source= env.fromElements("007_linux_male_tainjin",
//                                                            "008_lyj_male_baotou",
//                                                            "009_lpx_female_baoding",
//                                                            "010_ql_male_baoding");
//        source.map(new MapFunction<String, Tuple4<String,String,String,String>>() {
//            @Override
//            public Tuple4<String, String, String, String> map(String value) throws Exception {
//                String[] s=value.split("_");
//                return new Tuple4<>(s[0],s[1],s[2],s[3]);
//            }
//        }).filter(new FilterFunction<Tuple4<String, String, String, String>>() {
//
//            @Override
//            public boolean filter(Tuple4<String, String, String, String> value) throws Exception {
//                return value.f2.equals("male");
//            }
//        }).print();




        //222222222
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<String> source= env.fromElements("007_linux_female_tainjin");
//        source.flatMap(new FlatMapFunction<String, String>() {
//
//            @Override
//            public void flatMap(String value, Collector<String> out) throws Exception {
//                String[] split=value.split("_");
//                for (String s:split){
//                    out.collect(s);
//                }
//            }
//        }).print();


            //1111111111111
//        ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
//        DataSource<Integer> source= env.fromElements(1,2,3,4,5);
//
//        source.map(new MapFunction<Integer, String>() {
//
//            @Override
//            public String map(Integer value) throws Exception {
//                return value.toString();
//            }
//        }).print();


    }
}
