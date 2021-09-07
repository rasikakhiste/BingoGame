package com.rasika.interview.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Rasika Khiste
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemIOProcessor implements IOProcessor {

    private Scanner scanner;

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
