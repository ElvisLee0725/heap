import java.util.Collections;
import java.util.PriorityQueue;

// You have N bags of candy. The ith bag contains arr[i] pieces of candy, and each of the bags is magical!
// It takes you 1 minute to eat all of the pieces of candy in a bag (irrespective of how many pieces of candy are inside), and as soon as you finish, the bag mysteriously refills. If there were x pieces of candy in the bag at the beginning of the minute, then after you've finished you'll find that floor(x/2) pieces are now inside.
// You have k minutes to eat as much candy as possible. How many pieces of candy can you eat?
public class Solution {
    public static void main(String[] args) {
        int [] arr = {19, 78, 76, 72, 48, 8, 24, 74, 29};
        System.out.print(new Solution().maxCandies(arr, 3));
    }
    // For each round, take the most of Candys in the bags and eat them (sum up the amount of candies eaten). Do it total of k times
    // To get the bag that contains the most of candies, use a max heap to store all numbers of candies. At the top of max heap has the biggest number
    // Each round, pop from the top of max heap, then, push the floor(x / 2) back to the heap
    // Return the count of all candies eaten at the end
    // Time: O(n + klogn), Space: O(n)
    int maxCandies(int[] arr, int k) {
        // Write your code here
        PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
        for(int c : arr) {
            maxHeap.offer(c);
        }

        int count = 0;
        while(k > 0 && maxHeap.peek() > 0) {
            int candy = maxHeap.poll();
            count += candy;
            maxHeap.offer(candy / 2);
            k--;
        }

        return count;
    }
}
