import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 테스트케이스가 최대 10개이고, 수열의 길이가 최대 1000이므로 O(N^2)의 시간복잡도를 가지는
 * DP를 이용한 LIS 알고리즘을 사용해도 풀수 있다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 수열의 길이
			
			st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[N]; // 수열
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); // 수열의 구성요소 저장
			}
			
			int[] dp = new int[N]; // 동적 테이블
			
			// DP를 이용한 LIS 계산
			int answer = 0;
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				answer = Math.max(answer, dp[i]);
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n"); // 테스트 케이스 수행 결과 삽입
		}
		
		System.out.print(sb); // 테스트 케이스 결과들 출력
	}

}