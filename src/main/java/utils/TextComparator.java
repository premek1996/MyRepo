package utils;

import java.util.List;

public class TextComparator {

    private TextComparator() {
    }

    public static String getMostSimilarText(String text, List<String> texts) {
        String mostSimilarText = "";
        double maxSimilarity = 0;
        double similarity;
        for (String comparedText : texts) {
            similarity = getSimilarity(text, comparedText);
            if (similarity > maxSimilarity) {
                mostSimilarText = comparedText;
                maxSimilarity = similarity;
            }
        }
        return mostSimilarText;
    }

    public static double getSimilarity(String textFirst, String textSecond) {
        double longestCommonSubsequenceLength = LongestCommonSubsequenceAlgorithm.getLongestCommonSubsequenceLength(textFirst.toCharArray(), textSecond.toCharArray());
        double textFirstLength = textFirst.length();
        double textSecondLength = textSecond.length();
        return 2 * longestCommonSubsequenceLength / (textFirstLength + textSecondLength);
    }

}
