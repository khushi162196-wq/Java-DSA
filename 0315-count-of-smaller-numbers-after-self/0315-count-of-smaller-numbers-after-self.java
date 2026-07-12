import java.util.*;

class Solution {
    private int[] count;
    private int[] indices;
    private int[] nums;
    
    public List<Integer> countSmaller(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        count = new int[n];
        indices = new int[n];
        
        for (int i = 0; i < n; i++) indices[i] = i;
        
        mergeSort(0, n - 1);
        
        List<Integer> res = new ArrayList<>();
        for (int c : count) res.add(c);
        return res;
    }
    
    private void mergeSort(int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }
    
    private void merge(int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        int rightCount = 0; // how many from right half we've placed
        
        while (i <= mid && j <= r) {
            // if right element is smaller, it will be "before" left element
            if (nums[indices[j]] < nums[indices[i]]) {
                temp[k] = indices[j];
                rightCount++;
                j++;
            } else {
                temp[k] = indices[i];
                count[indices[i]] += rightCount; // all rightCount elements are smaller
                i++;
            }
            k++;
        }
        
        while (i <= mid) {
            temp[k] = indices[i];
            count[indices[i]] += rightCount;
            i++; k++;
        }
        
        while (j <= r) {
            temp[k] = indices[j];
            j++; k++;
        }
        
        // copy back
        for (int p = 0; p < temp.length; p++) {
            indices[l + p] = temp[p];
        }
    }
}