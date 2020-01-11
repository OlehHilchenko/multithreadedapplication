package main;


        import java.util.LinkedList;
        import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();

        AdditionalStreams additionalStreams = new AdditionalStreams(new LinkedList<>(), ScanPathFile.scanPathFile());

        additionalStreams.getFileList();

        Semaphore semaphore = new Semaphore(1, true);

        FileProcessing fileProcessing = new FileProcessing(semaphore,"One");
        FileProcessing fileProcessing2 =new FileProcessing(semaphore,"Two");
        FileProcessing fileProcessing3 =new FileProcessing(semaphore,"Three");

        try {
            fileProcessing.t.join();
            fileProcessing2.t.join();
            fileProcessing3.t.join();
        }catch (InterruptedException e){
            System.out.println("main thread interrupted");
        }

        System.out.println("end main thread ");
        long l2 = System.currentTimeMillis();
        System.out.println(l1);
        System.out.println(l2);
    }
}
