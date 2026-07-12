import java.util.*;

class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // chote numbers
    private PriorityQueue<Integer> minHeap; // bade numbers

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Step 1: Pehle maxHeap me daalo
        maxHeap.offer(num);

        // Step 2: maxHeap ka sabse bada minHeap me bhej do
        minHeap.offer(maxHeap.poll());

        // Step 3: Balance karo. maxHeap me 1 zyada ho sakta hai
        if(maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size() > minHeap.size()) {
            return maxHeap.peek(); // odd
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0; // even
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */