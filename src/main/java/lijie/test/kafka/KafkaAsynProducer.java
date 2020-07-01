package lijie.test.kafka;

import com.alibaba.fastjson.JSONObject;
import lijie.test.threadPoolTest.ThreadPool;
import org.apache.jute.CsvOutputArchive;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Callable;

/**
 * @author: Coline
 * @ClassName: KafkaAsynProducer
 * @Date: 2020/3/8 22:37
 * @Description: 异步模式发送kafka消息
 */
public class KafkaAsynProducer implements Callable {

    public static void main(String[] args) {
        ThreadPool.submit(new KafkaAsynProducer());
    }

    public Object call() throws Exception {
        Person person = new Person();
        person.setAge("12");
        person.setName("小s");
        person.setLike("篮球");
        person.setPassword("a12345s6");
        Person person1 = new Person();
        person1.setAge("12");
        person1.setName("小s");
        person1.setLike("篮球");
        KafkaProducer<String, String> producer = ProducerKafka.getProducer();
        try {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    producer.send(new ProducerRecord("TestTopic", "test", JSONObject.toJSONString(person1)), new Callback() {
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (null != exception) {
                                exception.printStackTrace();
                            }
                            if (null != metadata) {
                                System.out.println("offset: " + metadata.offset() + " "
                                        + "partition: " + metadata.partition());
                            }
                        }
                    });
                } else {
                    producer.send(new ProducerRecord("TestTopic", "test", JSONObject.toJSONString(person)), new Callback() {
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (null != exception) {
                                exception.printStackTrace();
                            }
                            if (null != metadata) {
                                System.out.println("offset: " + metadata.offset() + " "
                                        + "partition: " + metadata.partition());
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        return null;
    }
}