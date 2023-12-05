package First;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) {
        int sum = 0;
        try {
            Scanner scanner = new Scanner(new File("src\\First\\question.txt"));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                int firstNum = 0;
                int lastNum = 0;
                boolean isLast = false;
                for (int i = 0; i < line.length(); i++){
                    if (isInteger(line.charAt(i))) {
                        int Number = Integer.parseInt(String.valueOf(line.charAt(i)));
                        if (!isLast){
                            firstNum = Number;
                            isLast = true;
                        } else {
                            lastNum = Number;
                        }
                    }
                }
                if (lastNum == 0) {
                    lastNum = firstNum;
                }
                sum += firstNum * 10 + lastNum;
            }
        } catch (IOException e) {
            System.out.println(":< File Error");
        }
        System.out.println(sum);
    }

    public static boolean isInteger(char c){
        try {
            Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}