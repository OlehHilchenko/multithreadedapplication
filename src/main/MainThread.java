package main;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class MainThread extends Thread {

    private volatile Queue<String> queueList;
    volatile String pathFile;
    volatile int countThread;

    private synchronized void increaseCountThread() {
        countThread = countThread + 1;
    }

    private synchronized void reduceCountThread() {
        countThread = countThread - 1;
    }

    MainThread(LinkedList<String> q, String p, int c) {
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

        for (int i = 0; i < getSizeQueueList(); ) {
            if (countThread <= 3) {
                new Thread(this::fileProcessingInAStream).start();
                //System.out.println("start thread num " + getName());
            } else {
                sleep(750l);
            }
        }
    }

    private void fileProcessingInAStream (){
        increaseCountThread();

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
        reduceCountThread();
    }
}
