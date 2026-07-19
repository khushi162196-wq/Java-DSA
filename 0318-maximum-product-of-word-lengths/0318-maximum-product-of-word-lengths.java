import java.util.*;

class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n]; // har word ka bitmask
        int[] lens = new int[n]; // har word ki length

        // Step 1: Har word ke liye mask aur length calculate karo
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) {
                mask |= 1 << (c - 'a'); // us char ka bit ON kar do
            }
            masks[i] = mask;
            lens[i] = words[i].length();
        }

        // Step 2: Saare pairs check karo
        int maxProd = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Agar dono ke mask me & karne pe 0 aaya = no common char
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, lens[i] * lens[j]);
                }
            }
        }
        return maxProd;
    }
}