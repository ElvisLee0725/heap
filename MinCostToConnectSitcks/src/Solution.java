import java.util.PriorityQueue;

// Start from the shortest stick, sum up the next shortest stick and put it back. Keep doing until there is just 1 stick
// Use a min heap. While the min heap is not empty, pop from it and sum up accumulate sum and cur number
// Update the individual sum
// Return the final result
// Time: O(nlogn), Space: O(n)
class Solution {
    public int connectSticks(int[] sticks) {
        if(sticks.length == 1) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue();
        for(Integer n : sticks) {
            minHeap.offer(n);
        }

        int sum = 0;
        while(minHeap.size() > 1) {
            int s1 = minHeap.poll();
            int s2 = minHeap.poll();

            int tmp = s1 + s2;
            sum += tmp;
            minHeap.offer(tmp);
        }
        return sum;
    }
}
