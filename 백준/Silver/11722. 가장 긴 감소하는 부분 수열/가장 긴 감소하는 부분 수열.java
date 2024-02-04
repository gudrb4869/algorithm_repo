import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			dp[i] = 1; 
			for (int j = 1; j < i; j++) {
				if (A[j] > A[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		int answer = 0;
		
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
		
	}

}