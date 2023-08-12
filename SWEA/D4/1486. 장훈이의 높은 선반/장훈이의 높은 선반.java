import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 */
public class Solution {

	static int n, b, answer, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			subset(0, 0);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void subset(int cnt, int sum) {
		
		if (cnt == n) {
			if (sum >= b) {
				if (answer >= (sum - b)) {
					answer = Math.min(answer, sum - b);
				}
			}
			return;
		}
		
		subset(cnt+1, sum+arr[cnt]);
		subset(cnt+1, sum);
		
	}

}