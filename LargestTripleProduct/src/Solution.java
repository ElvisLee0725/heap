import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        int [] arr = new int[]{2, 1, 2, 1, 2};
        int [] res = new Solution().findMaxProduct2(arr);

        for(Integer val : res) {
            System.out.print(val + " ");
        }
    }
    // Add any helper functions you may need here
    // For res [] at index 0 and 1, enter -1. For index 2 and above, find the top 3 numbers to multiply and store to res[i]
    // Use a max heap to store the value include and before index i
    // Iterate the input array, get the product from top 3 numbers in max heap and store the result to res[i]. Put 3 numbers back to heap
    // Return res array after loop
    // Time: O(nlogn), Space: O(n)

    int[] findMaxProduct(int[] arr) {
        // Write your code here
        int [] res = new int[arr.length];
        PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
        for(int i = 0; i < arr.length; i++) {
            if(i < 2) {
                res[i] = -1;
                maxHeap.offer(arr[i]);
            }
            else {
                maxHeap.offer(arr[i]);
                int max = maxHeap.poll();
                int max2 = maxHeap.poll();
                res[i] = max * max2 * maxHeap.peek();
                maxHeap.offer(max);
                maxHeap.offer(max2);
            }
        }
        return res;
    }

    int[] findMaxProduct2(int[] arr) {
        int [] res = new int [arr.length];
        Arrays.fill(res, -1);
        int max = 0;
        int max2 = 0;
        int max3 = 0;
        if(arr[0] > arr[1]) {
            max = arr[0];
            max2 = arr[1];
        }
        else {
            max = arr[1];
            max2 = arr[0];
        }
        for(int i = 2; i < arr.length; i++) {
            if(arr[i] > max) {
                max3 = max2;
                max2 = max;
                max = arr[i];
            }
            else if(arr[i] > max2) {
                max3 = max2;
                max2 = arr[i];
            }
            else if(arr[i] > max3) {
                max3 = arr[i];
            }
            res[i] = max * max2 * max3;
        }
        return res;
    }
}
