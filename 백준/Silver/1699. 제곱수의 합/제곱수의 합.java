import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 입력
		
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 100000); // dp 테이블 초기화
		
		for (int i = 1; i <= N; i++) {
			int k = (int) Math.sqrt(i);
			
			if (k * k == i) { // i가 자연수의 제곱수인 경우 1개 항으로 표현가능
				dp[i] = 1;
			} else {
				for (int j = 1; j <= k; j++) { // 제곱수들의 합 중에서 최소 개수 찾아서 갱신해줌
					dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
				}
			}
		}
		
		System.out.println(dp[N]); // 출력
	}

}