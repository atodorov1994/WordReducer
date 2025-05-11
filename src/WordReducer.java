import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class WordReducer {

    private static Map<Integer, Map<String, Set<String>>> dictionary;

    private static final Map<String, Set<String>> MEMORY = new HashMap<>();
    private static final Set<String> VALID_ONE_LETTER_WORDS = Set.of("I", "A");


    public static void main(String[] args) throws IOException {

        var loadingStart = System.currentTimeMillis();

        dictionary = loadAllWords().stream()
                .filter(word -> word.length() <= 9 && VALID_ONE_LETTER_WORDS.stream().anyMatch(word::contains))
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.groupingBy(
                                s -> s.substring(0, 1),
                                Collectors.toSet())));

        Set<String> nineLetterWords = dictionary.get(9).values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        Map<String, Set<String>> result = new TreeMap<>();

        var start = System.currentTimeMillis();

        for (String word : nineLetterWords) {
            MEMORY.put(word, new TreeSet<>((o1, o2) -> Long.compare(o2.length(), o1.length())));
            if (canReduce(word, true, word)) {
                result.put(word, MEMORY.get(word));
            }
        }

        var end = System.currentTimeMillis();

        System.out.println("NUMBER OF RESULTS: " + result.size());
        System.out.println("LOADING PHASE: " + (start-loadingStart));
        System.out.println("EXECUTION: " + (end-start));

        result.forEach((s, strings) -> System.out.println(strings));
    }

    private static boolean canReduce(String word, boolean isOriginalWord, String originalWord) {

        if (!isOriginalWord && !wordExists(word)) {
            return false;
        }

        if (word.length() == 1) {
            var exists = wordExists(word);
            if (exists) {
                MEMORY.get(originalWord).add(word);
            }
            return exists;
        }

        for (int i = 0; i < word.length(); i++) {
            String reduced = word.substring(0, i) + word.substring(i + 1);
            if (canReduce(reduced, false, originalWord)) {
                MEMORY.get(originalWord).add(word);
                return true;
            }
        }

        return false;
    }

    private static boolean wordExists(String word) {

        return Optional.ofNullable(dictionary.get(word.length()).get(word.substring(0, 1)))
                .map(list -> list.contains(word))
                .orElse(false);
    }

    private static Set<String> loadAllWords() throws IOException {
        URL wordsUrl = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            Set<String> resp = br.lines().skip(2).collect(Collectors.toSet());
            resp.addAll(VALID_ONE_LETTER_WORDS);
            return resp;
        }
    }
}