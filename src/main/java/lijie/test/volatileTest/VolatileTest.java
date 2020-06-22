package lijie.test.volatileTest;

public class VolatileTest {

    public static void main(String[] args) {
        Pserson pserson = new Pserson();
        pserson.see();
        pserson.isTrue = false;
    }
}

class Pserson {
    public boolean isTrue = true;

    public void see() {
        while (isTrue) {
            System.out.println("我正在死循环呢");
        }
    }
}
