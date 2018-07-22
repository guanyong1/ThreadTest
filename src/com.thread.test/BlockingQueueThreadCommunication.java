import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author:guang yong
 * Description:阻塞队列做线程同步通信技术
 * @Date:Created in 15:47 2018/7/9
 * @Modified By:
 */
public class BlockingQueueThreadCommunication {

    public static void main(String[] args) throws InterruptedException {
        final Business business =new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                            for (int i = 1; i <= 50 ; i++) {
                                try {
                                    business.sub(i);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    }
                }
        ).start();

        for (int i = 1; i <= 50 ; i++) {
           business.main(i);
        }
    }

    static class Business{
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);
        //匿名构造方法，运作时机在所有构造方法之前，创建对象时运行
        {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void sub(int i) throws InterruptedException {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("线程1："+j+"线程2："+i);
            }
            queue2.take();
        }

        public void main(int i) throws InterruptedException {
            queue2.put(1);
            for (int j = 1; j <= 10; j++) {
                System.out.println("main:线程1："+j+"线程2："+i);
            }
            queue1.take();
        }
    }
}

