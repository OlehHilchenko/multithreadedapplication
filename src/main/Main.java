package main;


        import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        AdditionalStreams additionalStreams = new AdditionalStreams(new LinkedList<>(), ScanPathFile.scanPathFile(), 0);

        additionalStreams.getFileList();
        additionalStreams.fourNewThread();
        System.out.println("end main thread ");

    }
}
