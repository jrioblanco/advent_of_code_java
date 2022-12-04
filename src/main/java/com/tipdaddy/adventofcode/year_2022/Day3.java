package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.ResourceBundle;

public class Day3 implements Day {

    public int part1_getRoundScore(final String commonChar) {
        char ch = commonChar.charAt(0);
        int value = 0;
        if (Character.isUpperCase(ch)) 
            value = Character.getNumericValue(ch) - Character.getNumericValue('A') + 27;
        else 
            value = Character.getNumericValue(ch) - Character.getNumericValue('a') + 1;     
        System.out.println("value " + ch + " = " + value);   
        return value;
    }

    @Override
    public void run() {
        List<String> contents = FileReader.readMultiLineFile("2022", "3");

        final int totalScorePart1 = contents
                .stream()
                .mapToInt(line -> {
                    // final String[] moves = line.split("(?<=\\d)(?=\\D)");
                    String[] moves = line.split("(?<=\\G.{" + line.length()/2 + "})");
                        final String common = moves[0].replaceAll("[^"+moves[1]+"]", ""); 
                    return part1_getRoundScore(common);
                }).sum();
        System.out.printf("Score for part 1 is %d%n", totalScorePart1);

        final List<String> groups =  part2_getLineGroups(contents);
        final int totalScorePart2 = groups
                .stream()
                .mapToInt(line -> { 
                    return part1_getRoundScore(line);
                }).sum();
        System.out.printf("Score for part 2 is %d%n", totalScorePart2);
    }

    private List<String> part2_getLineGroups(List<String> contents) {
        List<String> result = new LinkedList<>();
        // StringBuffer buffer = new StringBuffer();
        // Map<String, Integer> counters = new LinkedHashMap<>();
        int linesProcessed = 0;
        int group = 0;
        for (int i = 0; i < contents.size()-2;) {
            String first = contents.get(i++);
            String second = contents.get(i++);
            String third =  contents.get(i++);
            linesProcessed += 3;

            String common = null;
            ++group;
            for (int j = 0; j < first.length(); j++) {
                Character ch = first.charAt(j);
                if (second.contains(""+ch) && third.contains(""+ch)) {
                    common = ""+ch;
                    System.out.println("Common ch for group " + group + " is = " + common);
                }
            }

            result.add(common);

            // System.out.println("Common ch for group " + (++group) + " is = " + result.get(result.size()-1));

            /*
            System.out.println(first);
            System.out.println(second);
            System.out.println(third)
            */
        }
        System.out.println("Lines processed = " + linesProcessed);
        return result;
    }
}
