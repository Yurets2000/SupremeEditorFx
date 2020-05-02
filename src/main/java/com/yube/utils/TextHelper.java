package com.yube.utils;

public class TextHelper {

    public static String formatFromCenter(int length, String left, String right){
        if(left.length() + right.length() > length) throw new IllegalArgumentException();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(" ");
        }
        result.delete(0, left.length());
        result.delete(result.length() - right.length(), result.length());
        result.insert(0, left);
        result.append(right);
        return result.toString();
    }

    public static String cropToLength(String line, int length){
        if(line.length() <= length) return line;
        return line.substring(0, length - 3) + "...";
    }
}
