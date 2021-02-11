// Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
// A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.

// Iterate each row of the matrix, at each row, count the number of 1s in that row using Binary Search
// Use a max heap with size k to store Soldier { row, numOfOnes }, rewrite comparator 
// At the end, pop out from max heap and insert row number to result array from the back, return it
// Time: O(mlognk), Space: O(k)

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        int [][] mat = {{1,1,0,0,0}, {1,1,1,1,0}, {1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1}};
        int [] res = new Solution().kWeakestRows(mat, 3);
        for(int i : res) {
            System.out.print(i + " ");
        }
    }
    class Soldier {
        int row;
        int numOfOnes;
        public Soldier(int row, int numOfOnes) {
            this.row = row;
            this.numOfOnes = numOfOnes;
        }
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<Soldier> maxHeap = new PriorityQueue(k, new Comparator<Soldier>(){
            public int compare(Soldier s1, Soldier s2) {
                if(s1.numOfOnes == s2.numOfOnes) {
                    return s1.row < s2.row ? 1 : -1;
                }
                return s1.numOfOnes < s2.numOfOnes ? 1 : -1;
            }
        });

        for(int i = 0; i < mat.length; i++) {
            Soldier s = new Soldier(i, countOnes(mat[i]));

            if(i < k) {
                maxHeap.offer(s);
            }
            else {
                Soldier top = maxHeap.peek();
                if(s.numOfOnes < top.numOfOnes) {
                    maxHeap.poll();
                    maxHeap.offer(s);
                }
            }
        }
        int [] res = new int[k];
        for(int i = k-1; i >= 0; i--) {
            res[i] = maxHeap.poll().row;
        }
        return res;
    }

    public int countOnes(int [] arr) {
        int left = 0;
        int right = arr.length-1;
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(arr[mid] == 1) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        // When left and right are next to each other, check if they are both 1, both 0 or just left is 1
        if(arr[left] == 1 && arr[right] == 1) {
            return right + 1;
        }
        else if(arr[left] == 0 && arr[right] == 0) {
            return 0;
        }
        else {
            return left + 1;
        }
    }
}