package Second;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) {
        try {
            int result = 0;
            Scanner scanner = new Scanner(new File("src\\Second\\question.txt"));
            String[] colors = {"red", "green", "blue"};
            String s;
            int gameNumber = 1;
            while (scanner.hasNextLine()){
                int redOptimal = 0, blueOptimal = 0, greenOptimal = 0;
                s = scanner.nextLine();
                String[] arr = s.split(";");
                for (String element : arr){
                    String regex = "(\\d+ red|\\d+ green|\\d+ blue)";
                    Pattern pattern = Pattern.compile(regex);
                    String[] matcher = pattern.matcher(element).results().map(MatchResult::group).toArray(String[]::new);
                    for (String subsequence : matcher){
                        for (String color : colors){
                            regex = "\\d+ " + color;
                            pattern = Pattern.compile(regex);
                            Matcher matcherColor = pattern.matcher(subsequence);
                            while (matcherColor.find()) {
                                switch (color) {
                                    case "red" -> {
                                        if (redOptimal < Integer.parseInt(matcherColor.group().split(" ")[0]))
                                            redOptimal = Integer.parseInt(matcherColor.group().split(" ")[0]);
                                    }
                                    case "blue" -> {
                                        if (blueOptimal < Integer.parseInt(matcherColor.group().split(" ")[0]))
                                            blueOptimal = Integer.parseInt(matcherColor.group().split(" ")[0]);
                                    }
                                    case "green" -> {
                                        if (greenOptimal < Integer.parseInt(matcherColor.group().split(" ")[0]))
                                            greenOptimal = Integer.parseInt(matcherColor.group().split(" ")[0]);
                                    }
                                }
                            }
                        }

                    }
                }
                result += redOptimal * greenOptimal * blueOptimal;
                System.out.println("Game " + gameNumber + " Red " + redOptimal + " Blue " + blueOptimal + " Green " + greenOptimal);
                gameNumber++;
                System.out.println(result);
            }
        } catch (IOException e){
            System.out.println("File reading problem :<");
        }

    }
}