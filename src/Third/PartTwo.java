package Third;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] arg){
        try {
            ArrayList<int[]> stars = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Third/question.txt"));
            String line;
            char[][] arr = null;
            for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
                if (arr == null){
                    arr = new char[140][line.length()];
                }
                arr[i] = line.toCharArray();
                stars.add(Pattern.compile("\\*").matcher(line).results().map(MatchResult::start).mapToInt(Integer::intValue).toArray());
            }
            int result = 0;
            for (int i = 0; i < stars.size(); i++)
                for (int j = 0; j < stars.get(i).length; j++) {
                    String word = checkSurrounding(arr, i, stars.get(i)[j]);
                    System.out.println(word);
                        int firstX, lastX, firstY, lastY;
                        String[] firstArr = word.split(" ");
                            firstX = Integer.parseInt(firstArr[0].split(":")[0]);
                            lastX = Integer.parseInt(firstArr[firstArr.length - 1].split(":")[0]);
                            firstY = Integer.parseInt(firstArr[0].split(":")[1]);
                            lastY = Integer.parseInt(firstArr[firstArr.length - 1].split(":")[1]);
                            if (findFullNumber(arr[firstX], firstY) != findFullNumber(arr[lastX], lastY)) {
                                result += findFullNumber(arr[firstX], firstY) * findFullNumber(arr[lastX], lastY);
                            }
                  System.out.println(result);
                }
            System.out.println(result);
        } catch (IOException e){
            System.out.println("Something bad happened at file reading part :>");
        }
    }
    private static String checkSurrounding(char[][] list, int x, int y) {
        String numbers = "";
        for(int dx = -1; dx <= 1; dx++) {
            if ((x + dx >= 0) && (x + dx < list.length)) {
                for(int dy = -1; dy <= 1; dy++) {
                    if ((y + dy >= 0) && (y + dy < list[x + dx].length) && (!(dx == 0 && dy == 0))) {
                        System.out.print(list[x + dx][y + dy]);
                        if (Character.isDigit(list[x + dx][y + dy])) {
                                numbers += (x + dx) + ":" + (y + dy) + " ";
                        }

                    }
                }
            }
        }
        System.out.println();
        return numbers;
    }
    public static int findFullNumber(char[] arr, int index){
        int result;
        if (index != 0 && Character.isDigit(arr[index - 1])){
            result = findFullNumber(arr, index - 1);
        } else {
            result = Integer.parseInt(findAllNextNumbers(arr, index));
        }
        return result;
    }

    public static String findAllNextNumbers(char[] arr, int index){
        String result = "";
        if (index != arr.length - 1 && Character.isDigit(arr[index + 1])){
            result += arr[index] + findAllNextNumbers(arr, index + 1);
        } else {
            result += arr[index];
        }
        return result;
    }
}
