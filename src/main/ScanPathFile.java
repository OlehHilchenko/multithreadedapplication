package main;

import java.util.Scanner;

public class ScanPathFile {

    static public String scanPathFile (){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter directory: (like C:/Windows)");
        return scanner.next();
    }
}
