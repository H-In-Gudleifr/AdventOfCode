package First;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) {
        int sum = 0;
        try {
            Scanner scanner = new Scanner(new File("src\\First\\question.txt"));
            while (scanner.hasNextLine()) {
                String[] arr = Sorted(scanner.nextLine());
                int[] tab = new int[arr.length];
                for (int i = 0; i < arr.length; i++){
                    if (arr[i].length() < 2) {
                        tab[i] = Integer.parseInt(arr[i]);
                    } else {
                        tab[i] = Number(arr[i]);
                    }
                }
                sum += tab[0] * 10 + tab[tab.length - 1];
            }
        } catch (IOException e) {
            System.out.println(":< File Error");
        }
        System.out.println(sum);
    }
    private static int Number(String word) {
        int answer = -1;
        String[] tab = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        for (int i = 0; i < tab.length; i++){
            if(word.equals(tab[i])) {
                answer = i + 1;
            }
        }
        return answer;
    }

    public static String[] Sorted (String s) {
        String regex = "" + "(one|two|three|four|five|six|seven|eight|nine|onenine|oneight|twone|eightwo|\\d)" + "";
        return Pattern.compile(regex)
                .matcher(s)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }
}