package main;

import java.io.File;

public class MyThread implements Runnable {

    String name;
    Thread t;

    MyThread (String threadName){

        name = threadName;
        t = new Thread(this, name);
        System.out.println("new thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        int size = AdditionalStreams.getSizeQueueList();
        do {
            String fullFileName = AdditionalStreams.getFileName();
            if (fullFileName != null) {
                String fullPathFile = AdditionalStreams.pathFile + "/" + fullFileName;
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
            size = AdditionalStreams.getSizeQueueList();
            System.out.println(name + " next iterate");
        }while (size > 0);
    }
}
