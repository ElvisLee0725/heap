// The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
// if x is even then x = x / 2
// if x is odd then x = 3 * x + 1
// For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
// Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order, if two or more integers have the same power value sort them by ascending order.
// Return the k-th integer in the range [lo, hi] sorted by the power value.
// Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps and that the power of x is will fit in 32 bit signed integer.

// Create a max heap with size k. Rewrite its comparator so that it compares power first, then, if power equals, compare its original number x
// Iterate the range and create object in real time:
// If there are less then k in the heap, add it to heap
// Else, check if cur object's power or value is smaller than the top of heap? If so, pop and insert cur object
// Finally, return the top of heap's x value
// Time: O(nlogk) while n is the range (hi - lo + 1) and k is the size of heap, Space: O(k)

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().getKth(7, 11, 4));
    }

    class PowInt {
        int x;
        int power;
        public PowInt(int x, int power) {
            this.x = x;
            this.power = power;
        }
    }
    public int getKth(int lo, int hi, int k) {
        PriorityQueue<PowInt> maxHeap = new PriorityQueue(k, new Comparator<PowInt>(){
            public int compare(PowInt pi1, PowInt pi2) {
                if(pi1.power == pi2.power) {
                    return pi1.x < pi2.x ? 1 : -1;
                }
                return pi1.power < pi2.power ? 1 : -1;
            }
        });

        int index = 0;
        for(int i = lo; i <= hi; i++) {
            PowInt cur = new PowInt(i, getPower(i));

            if(index < k) {
                maxHeap.offer(cur);
            }
            else {
                PowInt top = maxHeap.peek();
                if(top.power > cur.power || (top.power == cur.power && top.x > cur.x)) {
                    maxHeap.poll();
                    maxHeap.offer(cur);
                }
            }
            index++;
        }
        return maxHeap.peek().x;
    }

    public int getPower(int n) {
        int step = 0;
        while(n > 1) {
            if(n % 2 == 0) {
                n /= 2;
            }
            else {
                n = n * 3 + 1;
            }
            step++;
        }
        return step;
    }
}
