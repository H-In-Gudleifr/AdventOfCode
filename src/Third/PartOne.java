package Third;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PartOne {
    public static void main(String[] arg){
        try {
            ArrayList<Integer> suma = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Third/question.txt"));
            String line;
            String[][] arr = null;
            for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
                if (arr == null){
                    arr = new String[140][line.length()];
                }
               arr[i] = line.split("");
            }
            int currNumber = 0;
            boolean found = false;
            if (arr != null) {
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[i].length; j++) {
                        if (Character.isDigit(arr[i][j].charAt(0))) {
                            if (i > 0 && i < arr.length - 1) {
                                if (j > 0 && j < arr[i].length - 1) {
                                    if (isNotChar(arr[i][j - 1]) || isNotChar(arr[i][j + 1]) || isNotChar(arr[i + 1][j]) || isNotChar(arr[i - 1][j]) || isNotChar(arr[i + 1][j + 1]) ||
                                            isNotChar(arr[i + 1][j - 1]) || isNotChar(arr[i - 1][j + 1]) || isNotChar(arr[i - 1][j - 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }

                                    }
                                } else if (j == 0) {
                                    if (isNotChar(arr[i][j + 1]) || isNotChar(arr[i + 1][j]) || isNotChar(arr[i + 1][j + 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                } else if (j == arr[i].length - 1) {
                                    if (isNotChar(arr[i][j - 1]) || isNotChar(arr[i + 1][j]) || isNotChar(arr[i + 1][j - 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                }
                            } else if (i == 0) {
                                if (j > 0 && j < arr[i].length - 1) {
                                    if (isNotChar(arr[i][j - 1]) || isNotChar(arr[i][j + 1]) || isNotChar(arr[i + 1][j]) || isNotChar(arr[i + 1][j + 1]) || isNotChar(arr[i + 1][j - 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                } else if (j == 0) {
                                    if (isNotChar(arr[i][j + 1]) || isNotChar(arr[i + 1][j]) || isNotChar(arr[i + 1][j + 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                } else if (j == arr[i].length - 1) {
                                    if (isNotChar(arr[i][j - 1]) || isNotChar(arr[i + 1][j]) || isNotChar(arr[i + 1][j - 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                }
                            } else if (i == arr.length - 1) {
                                if (j > 0 && j < arr[i].length - 1) {
                                    if (isNotChar(arr[i][j - 1]) || isNotChar(arr[i][j + 1]) || isNotChar(arr[i - 1][j]) || isNotChar(arr[i - 1][j + 1]) || isNotChar(arr[i - 1][j - 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                } else if (j == 0) {
                                    if (isNotChar(arr[i][j + 1]) || isNotChar(arr[i - 1][j]) || isNotChar(arr[i - 1][j + 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                } else if (j == arr[i].length - 1) {
                                    if (isNotChar(arr[i][j - 1]) || isNotChar(arr[i - 1][j]) || isNotChar(arr[i - 1][j - 1])) {
                                        if (!found) {
                                            currNumber += findFullNumber(arr[i], j);
                                            found = true;
                                        }
                                    }
                                }
                            }
                        } else if (currNumber != 0) {
                            suma.add(currNumber);
                            currNumber = 0;
                            found = false;
                        }
                    }
                }
            }
            int ans = 0;
            for (int i : suma){
                ans += i;
                System.out.println(i);
            }
            System.out.println(ans);
        } catch (IOException e){
            System.out.println("Something bad happened at file reading part :>");
        }
    }


    public static boolean isNotChar(String s){
        return !Pattern.compile("[\\d|\\.]").matcher(s).matches();
    }

    public static int findFullNumber(String[] arr, int index){
        int result;

        if (index != 0 && Character.isDigit(arr[index - 1].charAt(0))){
            result = findFullNumber(arr, index - 1);
        } else {
            result = Integer.parseInt(findAllNextNumbers(arr, index));
        }
        return result;
    }

    public static String findAllNextNumbers(String[] arr, int index){
        String result = "";
        if (index != arr.length - 1 && Character.isDigit(arr[index + 1].charAt(0))){
            result += arr[index] + findAllNextNumbers(arr, index + 1);
        } else {
            result += arr[index];
        }
        return result;
    }
}
