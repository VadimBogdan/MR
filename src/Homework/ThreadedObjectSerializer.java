package Homework;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

public class ThreadedObjectSerializer<T> {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Thread writeThread;
    private Thread readThread;
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    private File file;

    private List<T> subscribers = new ArrayList<>();
    private boolean isNewFile;

    public ThreadedObjectSerializer(String fileName) throws IOException {
        file = new File(fileName);
        isNewFile = file.createNewFile();
    }

    private void readSubscribers(Consumer<List<T>> completion) {
        // if (isNewFile) { return; }
        if (readThread == null) {
           initReadThread(completion);
        } else if (!readThread.isAlive()) {
            initReadThread(completion);
        }
    }

    @SuppressWarnings("unchecked")
    private void initReadThread(Consumer<List<T>> completion) {

        readThread = new Thread(() -> {
            readLock.lock();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                if (fileInputStream.available() != 0) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    subscribers = (List<T>) objectInputStream.readObject();
                    objectInputStream.close();
                }
                fileInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
            if (completion != null) {
                completion.accept(subscribers);
            }
        });
        readThread.start();
    }

    private void initWriteThread() {
        writeThread = new Thread(() -> {
            writeLock.lock();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(subscribers);

                fileOutputStream.close();
                objectOutputStream.close();
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            } finally {
                writeLock.unlock();
                // Now it consists 1 Element
                isNewFile = false;
            }
        });
        writeThread.start();
    }

    public void writeSubscriber(T subscriber) {
        subscribers.add(subscriber);
        if (writeThread == null ) {
            initWriteThread();
        } else if (!writeThread.isAlive()) {
            initWriteThread();
        }
    }

    public void show(Consumer<List<T>> completion) {
        // if (isNewFile) { completion.accept(null); return; }
        readSubscribers(completion);
    }
}
