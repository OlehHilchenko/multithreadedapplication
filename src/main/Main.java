package main;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        MainThread mainThread = new MainThread(new LinkedList<>(),"C:/wot", 0);

        mainThread.getFileList();
        mainThread.fourNewThread();
        System.out.println("end mait thread ");

    }
}
