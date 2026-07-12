class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!= t.length()) return false;

        int[] freq = new int[26]; // a-z ke liye

        // s ke characters +1 karo
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // t ke characters -1 karo
        for(char c : t.toCharArray()) {
            freq[c - 'a']--;
            if(freq[c - 'a'] < 0) return false; // t me extra char hai
        }

        return true;
    }
}