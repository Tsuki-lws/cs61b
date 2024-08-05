import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /**
     * Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for (char i = 'a'; i <= 'z'; i++) {
            characterIntegerMap.put(i, i - 'a' + 1);
        }
        return characterIntegerMap;
    }

    /**
     * Returns a map from the integers in the list to their squares. For example, if the input list
     * is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        if (nums.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> a = new HashMap<>();
        for (int i : nums) {
            a.put(i, i * i);
        }
        return a;
    }

    /**
     * Returns a map of the counts of all words that appear in a list of words.
     */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        if (words.isEmpty()) {
            return null;
        }
        Map<String, Integer> a = new HashMap<>();
        for (String b : words) {
            if (a.containsKey(b)) {
                a.put(b, a.get(b) + 1);
            } else {
                a.put(b, 1);
            }
        }
        return a;
    }
}
