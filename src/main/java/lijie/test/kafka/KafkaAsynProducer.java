package lijie.test.kafka;

import com.alibaba.fastjson.JSONObject;
import lijie.test.threadPoolTest.ThreadPool;
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
        KafkaRequest kafkaRequest = new KafkaRequest();
        kafkaRequest.setOrgId(1);
        kafkaRequest.setAccessName("芒果动力");
        kafkaRequest.setAccessType("api接入");
        kafkaRequest.setSessionId("1");
        kafkaRequest.setUniqueId("1234");


        KafkaRequest kafkaRequest1 = new KafkaRequest();
        kafkaRequest1.setOrgId(1);
        kafkaRequest1.setAccessName("芒果动力");
        kafkaRequest1.setAccessType("api接入");
        kafkaRequest1.setSessionId("22");
        kafkaRequest1.setUniqueId("1234");
        KafkaProducer<String, String> producer = ProducerKafka.getProducer();
        try {
            for (int i = 0; i < 1; i++) {
                if (i == 0) {
                    producer.send(new ProducerRecord("TestTopic", "test", JSONObject.toJSONString(kafkaRequest1)), new Callback() {
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
                    producer.send(new ProducerRecord("TestTopic", "test", JSONObject.toJSONString(kafkaRequest)), new Callback() {
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