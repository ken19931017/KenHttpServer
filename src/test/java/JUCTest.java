import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class JUCTest {

    ReentrantLock lock  = new ReentrantLock();

    public void testReentrantlock(){
        try {
            lock.lock();
            while(true) {
                System.out.println("I have a lock!");
            }
        }finally {
            lock.unlock();
        }
    }

    @Test public void testTwoThread(){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testReentrantlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testReentrantlock();
            }
        });

        t1.setName("thread1");
        t2.setName("thread2");
        t1.start();
        t2.start();

    }

}
