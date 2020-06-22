package lijie.test.threadPoolTest;

import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        BMW bmw = new BMW();
        Future future = ThreadPool.submit(bmw);
    }
}
