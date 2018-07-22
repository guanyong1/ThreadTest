import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1  = "xixi";
                    System.out.println("线程"+Thread.currentThread().getName()
                            +"正在把数据"+data1+"换出去");
                    Thread.sleep((long)(Math.random()*10000));
                    String data2 = (String)exchanger.exchange(data1);
                    System.out.println("线程"+Thread.currentThread().getName()
                    +"换回数据为"+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1  = "hehe";
                    System.out.println("线程"+Thread.currentThread().getName()
                            +"正在把数据"+data1+"换出去");
                    Thread.sleep((long)(Math.random()*10000));
                    String data2 = (String)exchanger.exchange(data1);
                    System.out.println("线程"+Thread.currentThread().getName()
                            +"换回数据为"+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
