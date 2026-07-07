class Solution {

    public long sumAndMultiply(int n) {
        String s = String.valueOf(n);

        long x = 0;
        int sum = 0;

        for (char c : s.toCharArray()) {
            int digit = c - '0';
            sum += digit;

            if (digit != 0) {
                x = x * 10 + digit;
            }
        }

        return x * sum;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.sumAndMultiply(65463628));
    }
}