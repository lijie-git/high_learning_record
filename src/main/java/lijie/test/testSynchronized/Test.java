package lijie.test.testSynchronized;

public class Test {
    public static void main(String[] args) {

        final TestSynchronized test = new TestSynchronized();
        final TestSynchronized test1 = new TestSynchronized();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                test.minus();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                test.minus();
            }
        });

        thread1.start();
        thread2.start();
    }
}
