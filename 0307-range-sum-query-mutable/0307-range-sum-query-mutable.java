class NumArray {
    private int[] bit;
    private int[] nums;
    private int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.bit = new int[n];
        for (int i = 0; i < n; i++) {
            add(i, nums[i]);
        }
    }
    
    // add delta to index i
    private void add(int i, int delta) {
        for (; i < n; i = i | (i + 1)) {
            bit[i] += delta;
        }
    }
    
    // prefix sum from 0 to i
    private int prefixSum(int i) {
        int sum = 0;
        for (; i >= 0; i = (i & (i + 1)) - 1) {
            sum += bit[i];
        }
        return sum;
    }
    
    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        add(index, delta);
    }
    
    public int sumRange(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */