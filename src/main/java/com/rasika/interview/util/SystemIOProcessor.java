package com.rasika.interview.util;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Rasika Khiste
 */
public class SystemIOProcessor implements IOProcessor {

    private Scanner scanner;

    public SystemIOProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readNext() throws IOException {
        return scanner.next();
    }

    @Override
    public String readNextLine() throws IOException {
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }

}
