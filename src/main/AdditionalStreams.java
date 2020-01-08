package main;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class AdditionalStreams{

    static private volatile Queue<String> queueList;
    static volatile String pathFile;
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

    public static synchronized String getPathFile (){
        return pathFile;
    }

    public static synchronized String getFileName() {
        return queueList.poll();
    }

    public static int getSizeQueueList() {
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
}