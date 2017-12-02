import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ThirdTask implements Runnable {
    protected static final int THREADS = 3;

    private static class Semaphore {
        public boolean set = false;
    }

    protected static final Semaphore[] semaphores =
            new Semaphore[THREADS];
    protected static final Thread[] threads = new Thread[THREADS];

    protected final int threadNum;

    protected ThirdTask(int num) {
        this.threadNum = num;
    }

    public void run() {
        synchronized (semaphores) {
            semaphores[this.threadNum].set = true;
            semaphores.notify();
        }
        final long startExec = System.currentTimeMillis();
        for (int i = 0; i < 12; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String ipAddress = "google.com";
            InetAddress inet = null;
            try {
                inet = InetAddress.getByName(ipAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            final long finishExec = System.currentTimeMillis();
            try {
                System.out.println(inet.isReachable(5000) ? "Thread N" + this.threadNum + " " +
                        (finishExec - startExec) / 1000 + " секунд" + " Статус проверки доступности <Доступен>" : "Thread N" + this.threadNum +
                        ". Total: " + (finishExec - startExec) / 1000 + " Статус проверки доступности <Не доступен>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<THREADS; i++) {
            semaphores[i] = new Semaphore();
        }

        System.out.println("Starting " + THREADS + " threads...");

        synchronized (ThirdTask.class) {
            synchronized (semaphores) {
                for (int i=0; i<THREADS; i++) {
                    final ThirdTask mainThread = new ThirdTask(i);
                    threads[i] = new Thread(mainThread, "Thread N" + i);
                    threads[i].setDaemon(true);
                    threads[i].start();
                }

                boolean isAllStarted = false;
                while (!isAllStarted) {
                    try {
                        semaphores.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    isAllStarted = true;
                    for (int i=0; i<THREADS; i++) {
                        if (!semaphores[i].set) {
                            isAllStarted = false;
                            break;
                        }
                    }
                }
            }
        }
        for (int i=0; i<THREADS; i++) {
            try {
                Thread.sleep(1000);
                threads[i].join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println("\nEnd!");
    }
}