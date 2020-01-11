package main;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class AdditionalStreams{

    static private  Queue<String> queueList;
    static String pathFile;


    AdditionalStreams(LinkedList<String> q, String p) {
        queueList = q;
        pathFile = p;
    }

    public static String getPathFile (){
        return pathFile;
    }

    public static String getFileName() {
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