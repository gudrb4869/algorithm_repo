import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	private static int[][] arr;
	private static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				
				for (int j = 0; j < n; j++) {
					arr[i][j] = s.charAt(j) - '0';
				}
			}
			sb.append(String.format("#%d %d\n", t, recursion(n / 2)));
		}
		System.out.print(sb);
	}

	private static int recursion(int x) {
		if (x == 0) {
			return arr[n/2][n/2];
		}
		
		int sum = 0;
		int r = n / 2 - x, c = n / 2;
		for (int i = 0; i < x; i++) {
			sum += arr[r++][c++];
		}
		for (int i = 0; i < x; i++) {
			sum += arr[r++][c--];
		}
		for (int i = 0; i < x; i++) {
			sum += arr[r--][c--];
		}
		for (int i = 0; i < x; i++) {
			sum += arr[r--][c++];
		}
		
		return sum + recursion(x - 1);
	}

}
