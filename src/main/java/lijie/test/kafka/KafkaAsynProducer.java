package lijie.test.kafka;

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
        KafkaProducer<String, String> producer = ProducerKafka.getProducer();
        try {
            for (int i = 0; i < 4; i++) {
                producer.send(new ProducerRecord("TestTopic", "test", "你好啊: " + i), new Callback() {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        return null;
    }
}