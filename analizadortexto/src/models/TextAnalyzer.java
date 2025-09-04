package models;

public class TextAnalyzer {
    private String text;

    public TextAnalyzer(String text) {
        this.text = text;
    }

    public int countWords() {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public int countOccurrences(String word) {
        String[] words = text.trim().split("\\s+");
        int counter = 0;
        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                counter++;
            }
        }
        return counter;
    }

    public String replaceWord(String oldWord, String newWord) {
        return text.replaceAll("(?i)\\b" + oldWord + "\\b", newWord);
    }
}
