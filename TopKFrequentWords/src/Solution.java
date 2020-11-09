import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String [] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> res = new Solution().topKFrequentSort(words, 4);

        for(int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if(i != res.size() - 1) {
                System.out.print(", ");
            }
        }
    }

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

        List<Map .Entry<String, Integer>> list = new ArrayList(map.entrySet());
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
}
