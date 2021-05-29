import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String [] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> res = new Solution().topKFrequentHeap(words, 4);

        for(int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if(i != res.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    // Sol 1: Time: O(nlogn), Space: O(n)
    // HashMap <String, Integer> represents <Word, Frequency>
    // Convert hashmap into arrayList and sort it by frequency in descending order, then
    // by alphabetic in ascending order
    // Return the result ArrayList
    public List<String> topKFrequentSort(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap();
        for(String word : words) {
            map.putIfAbsent(word, 0);
            map.put(word, map.get(word) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               if(o1.getValue() == o2.getValue()) {
                   return o1.getKey().compareTo(o2.getKey());
               }
               return o1.getValue() < o2.getValue() ? 1 : -1;
           }
        });

        List<String> res = new ArrayList();
        for(int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }

        return res;
    }

    // Sol 2, Time: O(k + nlogk), Space: O(n)
    // HashMap: <String, Integer> to represent <Word, Frequency>
    // Heap: A minHeap to sort by frequency in ascending order. If frequency are equal, sort by alphabetic
    // order in descending order. Everything in heap is a possible answer
    // For each entry in the HashMap, check if cur entry has greater frequency or it's word
    // has prior alphabetic sequence. If so, pop from heap and insert entry
    // A result array to be filled in backward order since the top of heap has lowest frequency
    public List<String> topKFrequentHeap(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap();
        for(String word : words) {
            map.putIfAbsent(word, 0);
            map.put(word, map.get(word) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>(){
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               if(o1.getValue() == o2.getValue()) {
                   return o2.getKey().compareTo(o1.getKey());
               }
               return o1.getValue() < o2.getValue() ? -1 : 1;
           }
        });

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(minHeap.size() < k) {
                minHeap.offer(entry);
            }
            else {
                Map.Entry<String, Integer> top = minHeap.peek();
                if(entry.getValue() > top.getValue() || (entry.getValue() == top.getValue() && entry.getKey().compareTo(top.getKey()) < 0)) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }

        List<String> res = new ArrayList<>();
        while(k > 0) {
            res.add(minHeap.poll().getKey());
            k--;
        }
        Collections.reverse(res);
        return res;
    }
}
