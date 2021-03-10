package utils;

public class TextComparator {

    private TextComparator() {
    }

    private static double getSimilarity(String textFirst, String textSecond) {
        double longestCommonSubsequenceLength = LongestCommonSubsequenceAlgorithm.getLongestCommonSubsequenceLength(textFirst.toCharArray(), textSecond.toCharArray());
        double textFirstLength = textFirst.length();
        double textSecondLength = textSecond.length();
        return 2 * longestCommonSubsequenceLength / (textFirstLength + textSecondLength);
    }

}
