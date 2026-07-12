class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        // Step 1: Sabka XOR kar do. Result = a ^ b
        for(int num : nums) {
            xor ^= num;
        }

        // Step 2: xor me koi bhi set bit dhoondo. Ye bit a aur b me alag hai
        int diffBit = xor & (-xor); // rightmost set bit

        // Step 3: Array ko 2 group me baant do us bit ke basis pe
        int a = 0;
        for(int num : nums) {
            if((num & diffBit)!= 0) { // jinke us bit pe 1 hai
                a ^= num;
            }
        }

        int b = xor ^ a; // dusra number

        return new int[]{a, b};
    }
}