class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE; // sabse chota
        int second = Integer.MAX_VALUE; // second sabse chota

        for (int num : nums) {
            if (num <= first) {
                first = num; // naya sabse chota mil gaya
            }
            else if (num <= second) {
                second = num; // first ke baad ka sabse chota mil gaya
            }
            else {
                // num > second > first → triplet mil gaya
                return true;
            }
        }
        return false;
    }
}