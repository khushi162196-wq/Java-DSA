import java.util.*;

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // Step 1: Word -> Index map banao
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        // Step 2: Har word ke har cut pe check karo
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();

            for (int cut = 0; cut <= len; cut++) {
                String prefix = word.substring(0, cut);
                String suffix = word.substring(cut);

                // Case 1: prefix palindrome hai, to suffix ka reverse dhoondo
                if (isPalindrome(prefix)) {
                    String revSuffix = new StringBuilder(suffix).reverse().toString();
                    if (map.containsKey(revSuffix) && map.get(revSuffix)!= i) {
                        ans.add(Arrays.asList(map.get(revSuffix), i));
                    }
                }

                // Case 2: suffix palindrome hai, to prefix ka reverse dhoondo
                // cut!= len isliye kyunki duplicate na ho
                if (cut!= len && isPalindrome(suffix)) {
                    String revPrefix = new StringBuilder(prefix).reverse().toString();
                    if (map.containsKey(revPrefix) && map.get(revPrefix)!= i) {
                        ans.add(Arrays.asList(i, map.get(revPrefix)));
                    }
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++)!= s.charAt(r--)) return false;
        }
        return true;
    }
}