package startup;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue;
    private Thread[] taskExecutors;
    private volatile boolean isRunning;

    public ThreadPool(int nThreads) {
        this.taskQueue = new LinkedBlockingDeque<>();
        this.taskExecutors = new Thread[nThreads];
        this.isRunning = true;

        for (int i = 0; i < nThreads; i++) {
            taskExecutors[i] = new WorkerThread();
            taskExecutors[i].start();
        }
    }

    public void execute(Runnable task) throws InterruptedException {
        if (isRunning) {
            taskQueue.put(task);
        }
    }

    public void shutdown() {
        this.isRunning = false;
    }

    private class WorkerThread extends Thread {
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
