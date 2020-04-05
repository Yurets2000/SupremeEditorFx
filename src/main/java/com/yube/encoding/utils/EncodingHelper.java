package com.yube.encoding.utils;

import java.util.List;
import java.util.stream.Collectors;

public class EncodingHelper {

    public static List<String> splitTextOnSymbols(String text) {
        return text.codePoints()
                .mapToObj(Character::toChars)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public static String codepointToString(int cp) {
        StringBuilder sb = new StringBuilder();
        if (Character.isBmpCodePoint(cp)) {
            sb.append((char) cp);
        } else if (Character.isValidCodePoint(cp)) {
            sb.append(Character.highSurrogate(cp));
            sb.append(Character.lowSurrogate(cp));
        } else {
            sb.append('?');
        }
        return sb.toString();
    }

    public static String byteToByteBlock(byte b){
        return "0x" + String.format("%2s", Integer.toHexString(b & 0xFF)).replace(' ', '0').toUpperCase();
    }

    public static String byteToBitBlock(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public static String intToBitBlock(int i, int length) {
        return String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    public static int getCodepoint(String symbol) {
        return Character.codePointAt(symbol.toCharArray(), 0, symbol.length());
    }

    public static String getUnicodeRepresentation(int codepoint) {
        String hexRepresentation = Integer.toHexString(codepoint);
        if (hexRepresentation.length() <= 4) {
            return "0x" + String.format("%0" + (4 - hexRepresentation.length()) + "d%s", 0, hexRepresentation).toUpperCase();
        } else {
            return "0x" + String.format("%s", hexRepresentation).toUpperCase();
        }
    }

    public static int getCodepointFromUnicodeRepresentation(String unicode){
        return Integer.parseInt(unicode.substring(2), 16);
    }
}
