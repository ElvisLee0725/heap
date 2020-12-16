import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        int [] arr = {2, 4, 7, 1, 5, 3};
        int [] res = new Solution().findMedian(arr);
        for(int n : res) {
            System.out.print(n + " ");
        }
    }
    // Use a min heap to store numbers greater or equal to median, and a max heap to store numbers smaller than median
    // Insert and keep both heap balance or minHeap is always 1 size greater than maxHeap

    // Get Median:
    // Case 1: When current index is odd, median is (top of min + top of max) / 2
    // Case 2: When current index is even, median is the top of min heap

    // Iterate the input array, put the cur number to min or max heap and get the median, return the median to res[i]
    // Time: O(nlogn), Space: O(n)

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public void insert(int num) {
        if(minHeap.isEmpty() || num >= minHeap.peek()) {
            minHeap.offer(num);
        }
        else {
            maxHeap.offer(num);
        }

        if(minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
        else if(maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public int getMedian(int index) {
        if(index % 2 == 0) {
            return minHeap.peek();
        }
        else {
            return (minHeap.peek() + maxHeap.peek()) / 2;
        }
    }

    int[] findMedian(int[] arr) {
        // Write your code here
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Collections.reverseOrder());

        int [] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            insert(arr[i]);
            int curMedian = getMedian(i);
            res[i] = curMedian;
        }
        return res;
    }
}
