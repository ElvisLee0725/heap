import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static void main (String[] args) {
        int [][] arrays = {
                {1, 10, 11, 15},
                {2, 4, 9, 14},
                {5, 6, 8, 16},
                {3, 7, 12, 13}
        };
        int [] res = new Solution().sortNArrays(arrays);
        for(int n : res) {
            System.out.print(n + " ");
        }
    }

    class Cell {
        int arrNum;
        int index;
        public Cell(int arrNum, int index) {
            this.arrNum = arrNum;
            this.index = index;
        }
    }
    public int [] sortNArrays(int [][] arrays) {
        PriorityQueue<Cell> minHeap = new PriorityQueue(new Comparator<Cell>(){
            public int compare(Cell c1, Cell c2) {
                return arrays[c1.arrNum][c1.index] < arrays[c2.arrNum][c2.index] ? -1 : 1;
            }
        });

        int totalLen = 0;
        for(int i = 0; i < arrays.length; i++) {
            minHeap.offer(new Cell(i, 0));
            totalLen += arrays[i].length;
        }

        int [] result = new int[totalLen];
        int index = 0;
        while(!minHeap.isEmpty()) {
            Cell cell = minHeap.poll();
            int num = cell.arrNum;
            int idx = cell.index;
            result[index] = arrays[num][idx];
            index++;

            // If the idx has reached the last one, don't add back to heap
            if(idx < arrays[num].length - 1) {
                minHeap.offer(new Cell(num, idx + 1));
            }

        }
        return result;
    }
}
