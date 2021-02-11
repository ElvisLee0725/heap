// Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
// Implement KthLargest class:

// KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
// int add(int val) Returns the element representing the kth largest element in the stream.

// Create a min heap with size k. dump every number from input array into the min heap
// If a new number is larger than the top of min heap, pop it out and insert it into the min heap
// Else, just skip the new number
// Keep the top of the min heap to be the kth largest among all numbers in the data stream
// Therefore, when add() is called, return the top of min heap
// Time: O(n*logk), Space: O(k)

import java.util.PriorityQueue;

class KthLargest {
    PriorityQueue<Integer> minHeap;
    int size;
    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue(k);
        size = k;
        for(int i = 0; i < nums.length; i++) {
            if(i < k) {
                minHeap.offer(nums[i]);
            }
            else {
                if(nums[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(nums[i]);
                }
            }
        }
    }

    public int add(int val) {
        // Check if the min heap has the size k, if not, just add current number into the heap
        if(minHeap.size() < size) {
            minHeap.offer(val);
            return minHeap.peek();
        }
        else if(val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}