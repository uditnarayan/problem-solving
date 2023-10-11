import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        int len = votes[0].length();
        for (String vote : votes) {
            for (int i = 0; i < len; i++) {
                char c = vote.charAt(i);
                map.putIfAbsent(c, new int[len]);
                map.get(c)[i]++;
            }
        }

        List<Character> list = new ArrayList<>(map.keySet());
        list.sort((a,b)-> {
            for(int i = 0; i < len; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a - b;
        });
        StringBuilder sb = new StringBuilder();
        for (char c: list) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] votes = new String[]{"ABC","ACB","ABC","ACB","ACB"};
        String rank = solution.rankTeams(votes);
        assert rank.equals("ACB");
        System.out.println(rank);

        votes = new String[]{"WXYZ","XYZW"};
        rank = solution.rankTeams(votes);
        assert rank.equals("XWYZ");
        System.out.println(rank);

        votes = new String[]{"ABCDEFG"};
        rank = solution.rankTeams(votes);
        assert rank.equals("ABCDEFG");
        System.out.println(rank);
    }
}