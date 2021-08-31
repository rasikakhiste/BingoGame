package com.rasika.interview.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rasika Khiste
 */
public class GameHelper {

    private GameHelper () { }

    /**
     * Generate a list of numbers given a range
     * @param start
     * @param end
     * @return List
     */
    public static List<Integer> generateListWithinRange(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++)
            list.add(i);
        return list;
    }
}
