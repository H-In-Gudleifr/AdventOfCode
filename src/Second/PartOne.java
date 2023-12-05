package Second;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    public static void main(String[] args) {
        try {
            int result = 0;
            Scanner scanner = new Scanner(new File("src\\Second\\question.txt"));
            String[] colors = {"red", "green", "blue"};
            String s;
            int gameNumber = 1;
            while (scanner.hasNextLine()){
                int redMax = 0, blueMax = 0, greenMax = 0;
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
                                        if (redMax < Integer.parseInt(matcherColor.group().split(" ")[0]))
                                            redMax = Integer.parseInt(matcherColor.group().split(" ")[0]);
                                    }
                                    case "blue" -> {
                                        if (blueMax < Integer.parseInt(matcherColor.group().split(" ")[0]))
                                            blueMax = Integer.parseInt(matcherColor.group().split(" ")[0]);
                                    }
                                    case "green" -> {
                                        if (greenMax < Integer.parseInt(matcherColor.group().split(" ")[0]))
                                            greenMax = Integer.parseInt(matcherColor.group().split(" ")[0]);
                                    }
                                }
                            }
                        }

                    }
                }
                if (redMax <= 12 && greenMax <= 13 && blueMax <= 14){
                    result += gameNumber;
                }
                System.out.println("Game " + gameNumber + " Red " + redMax + " Blue " + blueMax + " Green " + greenMax);
                gameNumber++;
                System.out.println(result);
            }
        } catch (IOException e){
            System.out.println("File reading problem :<");
        }

    }
}