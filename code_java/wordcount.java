import java.io.*;
import java.util.*;

public class wordcount {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // File path
        String filePath = "gutenberg-1G.txt";

        // Create a map to hold the counts
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Open the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Process the file line by line
            while ((line = br.readLine()) != null) {
                // Remove punctuation and convert to lower case
                line = line.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

                // Split the line into words
                StringTokenizer tokenizer = new StringTokenizer(line);

                // Count the words (map step)
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();

                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word) + 1);
                    } else {
                        wordCountMap.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the counts (reduce step)
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println("Word: " + entry.getKey() + " | Count: " + entry.getValue());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nElapsed time: " + (endTime - startTime) + " milliseconds");
    }
}
