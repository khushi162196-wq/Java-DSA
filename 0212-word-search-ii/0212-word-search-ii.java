import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private TrieNode root = new TrieNode();
    private List<String> result = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        // Build Trie
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, root);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }

        char c = board[i][j];

        if (c == '#') return;

        node = node.children[c - 'a'];

        if (node == null) return;

        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates
        }

        board[i][j] = '#';

        dfs(board, i + 1, j, node);
        dfs(board, i - 1, j, node);
        dfs(board, i, j + 1, node);
        dfs(board, i, j - 1, node);

        board[i][j] = c;
    }
}