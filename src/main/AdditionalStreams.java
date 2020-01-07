package main;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class AdditionalStreams extends Thread {

    private volatile Queue<String> queueList;
    volatile String pathFile;
    volatile int countThread;

    private synchronized void reduceAndIncreaseCountThread(int arg) {
        if (arg < 0)
            countThread = countThread + 1;
        else if (arg > 0)
            countThread = countThread - 1;
    }

    AdditionalStreams(LinkedList<String> q, String p, int c) {
        queueList = q;
        pathFile = p;
        countThread = c;
    }

    private synchronized String getFileName() {
        return queueList.poll();
    }

    public int getSizeQueueList() {
        return queueList.size();
    }

    public void getFileList() {
        File file = new File(pathFile);

        if (file.isDirectory()) {
            System.out.println("is a directory");

            String s[] = file.list();
            for (String s2 : s) {
                queueList.add(s2);
            }


            for (String s1 : s)
                System.out.println(s1);

            System.out.println();
        }
    }

    public void fourNewThread() throws InterruptedException {
        int l = getSizeQueueList();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                reduceAndIncreaseCountThread(1);

                String fullFileName = getFileName();
                if (fullFileName != null) {
                    String fullPathFile = pathFile + "/" + fullFileName;
                    File file = new File(fullPathFile);
                    if (file.isDirectory()) {
                        System.out.println("this is a directory: " + file.getName());
                    } else {
                        String exp = "";

                        boolean trig = false;
                        for (int j = 0; j < fullFileName.length(); j++) {
                            if (fullFileName.charAt(j) == '.')
                                trig = true;

                            if (trig) {
                                exp += fullFileName.charAt(j);
                            }
                        }
                        System.out.println(fullFileName + " " + "form: " + exp + " size: " + file.length());
                    }
                }
                reduceAndIncreaseCountThread(-1);
            }
        };

        for (int i = 0; i < l; i++) {
            if (countThread <= 4) {
                Thread thread = new Thread(task);
                thread.start();
                System.out.println(thread.getName());
                sleep(25l);
            } else {
                sleep(750l);
                i--;
            }
        }
    }
}