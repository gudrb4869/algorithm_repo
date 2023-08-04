import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author 박형규
 *
 */
public class Solution {

	private static Map<Character, Character> map = new HashMap<>();
	private static Map<Character, Integer> map2 = new HashMap<>();
	private static int n;
	private static char[] arr;
	private static Stack<Character>[] s;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		map.put('>', '<');
		
		map2.put('(', 0);
		map2.put(')', 0);
		map2.put('[', 1);
		map2.put(']', 1);
		map2.put('{', 2);
		map2.put('}', 2);
		map2.put('<', 3);
		map2.put('>', 3);
		
		for (int t = 1; t <= 10; t++) {
			n = Integer.parseInt(br.readLine());
			arr = br.readLine().toCharArray();
			
			s = new Stack[4];
			for (int i = 0; i < 4; i++) {
				s[i] = new Stack<>();
			}
			
			sb.append("#").append(t).append(" ").append(isValid()?1:0).append("\n");
			isValid();
		}
		System.out.print(sb);
	}
	private static boolean isValid() {
		for (int i = 0; i < n; i++) {
			if (map.keySet().contains(arr[i])) {
				int idx = map2.get(arr[i]);
				if (s[idx].isEmpty() || !s[idx].peek().equals(map.get(arr[i]))) {
					return false;
				}
				s[idx].pop();
			} else {
				s[map2.get(arr[i])].push(arr[i]);
			}
		}
		
		if (!s[0].isEmpty() || !s[1].isEmpty() || !s[2].isEmpty() || !s[3].isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
}
