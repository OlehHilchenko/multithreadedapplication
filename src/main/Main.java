package main;


        import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {


        AdditionalStreams additionalStreams = new AdditionalStreams(new LinkedList<>(), ScanPathFile.scanPathFile(), 0);

        additionalStreams.getFileList();

        MyThread myThread = new MyThread("One");
        MyThread myThread2 =new MyThread("Two");
        MyThread myThread3 =new MyThread("Three");

        try {
            myThread.t.join();
            myThread2.t.join();
            myThread3.t.join();
        }catch (InterruptedException e){
            System.out.println("main thread interrupted");
        }

        System.out.println("end main thread ");

    }
}
