import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Check_Palindrome_by_Filtering_Non_Letters {

    public static void main(String args[]) {
        String test = "Ab1ba";

        System.out.println(isAlphabeticPalindrome(test));
    }

    //Abba size 4 size()/2
    //Ab1ba size 5. size -1 /2
    public static boolean isAlphabeticPalindrome(String code) {
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (isLetter(code.charAt(i))) {
                char toAdd = code.charAt(i);
                if (toAdd >= 'A' && toAdd <= 'Z') {
                    toAdd = (char) (toAdd + ('a' - 'A'));
                }
                letters.append(toAdd);
            }
        }

        boolean result = true;


        int half = -1;
        if (letters.length() % 2 == 0) {
            half = letters.length() / 2;
        } else {
            half = (letters.length() + 1) / 2;
        }

        for (int i = 0; i < half; i++) {


            if (letters.charAt(i) != letters.charAt(letters.length() - 1 - i)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean isLetter(char element) {
        return (element >= 65 && element <= 90) || (element >= 97 && element <= 122);
    }
}
