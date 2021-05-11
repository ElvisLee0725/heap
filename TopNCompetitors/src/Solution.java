// Input
//The input to the function/method consists of five arguments - numCompetitors, an integer representing the number of competitors for the Echo device;
//topNCompetitors, is an integer representing the maximum number of competitors that Amazon wants to identify;
//competitors, a list of strings representing the competitors;
//numReviews, an integer representing the number of reviews from different websites that are identified by the automated webcrawler;
//reviews, a list of string where each element is a string that consists of space-separated words representing user reviews.

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List<String> competitors = Arrays.asList(new String[]{"newshop", "shopnow", "afashion", "fashionbeats", "mymarket", "tcellular"});
        List<String> reviews = Arrays.asList(new String[] {
                "newshop is providing good services in the city; everyone should use newshop",
                "best services by newshop",
                "fashionbeats has great services in the city",
                "I am proud to have fashionbeats",
                "mymarket has awesome services",
                "Thanks Newshop for the quick delivery"});
        List<String> res = new Solution().TopNumCompetitors(6, 2, competitors, 6, reviews);

        for(String r : res) {
            System.out.println(r);
        }
    }
    // Use a HashMap to get <Competitor Name, Frequency>
    // Create an heap to store all entries from last step. The entry has most frequency at top,
    // If the frequency is equal sort by name
    // Pop out topNCompetitors from the heap and store them in a List, then, return it!
    public List<String> TopNumCompetitors(int numCompetitors,
                                          int topNCompetitors,
                                          List<String> competitors,
                                          int numReviews, List<String> reviews) {
        HashMap<String, Integer> map = new HashMap();
        for(String review : reviews) {
            String r = review.toLowerCase();
            for(String competitor : competitors) {
                String c = competitor.toLowerCase();
                if(r.indexOf(c) != -1) {
                    map.putIfAbsent(c, 0);
                    map.put(c, map.get(c) + 1);
                }
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue(new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if(e1.getValue() == e2.getValue()) {
                    return e1.getKey().compareTo(e2.getKey());
                }
                return e1.getValue() < e2.getValue() ? 1 : -1;
            }
        });

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            heap.offer(entry);
        }

        List<String> res = new ArrayList();
        while(!heap.isEmpty() && topNCompetitors > 0) {
            res.add(heap.poll().getKey());
            topNCompetitors--;
        }

        return res;
    }
}
