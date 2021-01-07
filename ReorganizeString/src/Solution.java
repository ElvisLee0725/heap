import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
// If possible, output any possible result.  If not possible, return the empty string.

// * Need to use the character with most repeats first
// Create a HashMap<Character, Integer> to store the <letter, frequency> from S
// Use a max heap, rewrite the comparator so the letter with most frequency at top

// While the maxHeap is not empty, do:
// Check if the top of heap equals to the last char in new string? if so, pop out all char to temp heap. and pop the next char to connect to string, and offer all poped char back to heap
// * If heap is empty before we can find a char to connect, return ""
// Else, just pop from heap to connect to the new string
// Return the new string after the while loop
// Time: O(nlogm) n is the length of S, m is the size of letters, Space: O(n)

public class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().reorganizeString("vvvlo"));
    }
    public String reorganizeString(String S) {
    if(S.length() == 1) {
        return S;
    }
    HashMap<Character, Integer> map = new HashMap();
    for(int i = 0; i < S.length(); i++) {
        map.putIfAbsent(S.charAt(i), 0);
        map.put(S.charAt(i), map.get(S.charAt(i)) + 1);
    }

    PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue(new Comparator<Map.Entry<Character,Integer>>(){
        public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character,Integer> e2) {
            if(e1.getValue() == e2.getValue()) {
                return 0;
            }
            return e1.getValue() < e2.getValue() ? 1 : -1;
        }
    });

    for(Map.Entry<Character, Integer> entry : map.entrySet()) {
        maxHeap.offer(entry);
    }

    Map.Entry<Character, Integer> temp = null;

    StringBuilder sb = new StringBuilder();
    while(!maxHeap.isEmpty()) {
        if(sb.length() > 0 && sb.charAt(sb.length()-1) == maxHeap.peek().getKey()) {
            temp = maxHeap.poll();
            if(maxHeap.isEmpty()) {
                return "";
            }
        }
        sb.append(maxHeap.peek().getKey());
        if(maxHeap.peek().getValue() == 1) {
            maxHeap.poll();
        }
        else {
            maxHeap.peek().setValue(maxHeap.peek().getValue() - 1);
        }
        if(temp != null) {
            maxHeap.offer(temp);
            temp = null;
        }
    }
    return sb.toString();
}
}
