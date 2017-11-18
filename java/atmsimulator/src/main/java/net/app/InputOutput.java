package net.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutput {

    private Scanner scanner = null;

    private static InputOutput instance = null;

    public static InputOutput getInstance() {
        if (instance == null) {
            synchronized (InputOutput.class) {
                if (instance == null) {
                    instance = new InputOutput(System.in, System.out);
                }
            }
        }
        return instance;
    }

    public static InputOutput getInstance(InputStream in, PrintStream printStream) {
        if (instance == null) {
            synchronized (InputOutput.class) {
                if (instance == null) {
                    instance = new InputOutput(in, printStream);
                }
            }
        }
        return instance;
    }

    private InputOutput(InputStream in, PrintStream printStream) {
        this.scanner = new Scanner(in);
        System.setOut(printStream);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void print(Object x) {
        System.out.print(x);
    }

    public void printLine(Object x) {
        System.out.println(x);
    }

    //For the sake of testing.
    public void resetStreams(InputStream in, PrintStream printStream) {
        this.scanner = new Scanner(in);
        System.setOut(printStream);
    }
}
