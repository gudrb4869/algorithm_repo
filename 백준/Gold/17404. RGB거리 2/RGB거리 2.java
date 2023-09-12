import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다이나믹 프로그래밍 문제
 * 
 * 점화식을 세워서 풀었다.
 * </pre>
 * @author 박형규
 */
public class Main {
	
	static int N, home[][], dp[][][]; // 집의 수, 집을 빨강,초록,파랑으로 칠하는 비용을 저장할 2차원 배열, dp 테이블
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 집의 수
		
		home = new int[N + 1][3]; // 각 집을 빨강, 초록, 파랑으로 칠하는 비용을 저장할 2차원 배열 선언
		dp = new int[N + 1][3][2]; // dp 테이블
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			home[i][0] = Integer.parseInt(st.nextToken()); // i번째 집을 빨강으로 칠하는 비용
			home[i][1] = Integer.parseInt(st.nextToken()); // i번째 집을 초록으로 칠하는 비용
			home[i][2] = Integer.parseInt(st.nextToken()); // i번째 집을 파랑으로 칠하는 비용
		}
		
		// dp 테이블 초기값 설정
		//  a,  a   b,  b   c,  c
		// ba, ca  cb, ab  ac, bc
		for (int i = 1; i <= 2; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					dp[i][j][k] = dp[i - 1][(i + j + k - 1) % 3][k] + home[i][j];
				}
			}
		}
		
		// 3번째집부터 N번째 집까지의 점화식
		for (int i = 3; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					dp[i][j][k] = dp[i - 1][(j + 2 - k) % 3][(k + 1) % 2] + home[i][j]; // 해당 위치의 기본값 세팅
					
					if (i > 3) { // 집의 수가 4개 이상인 경우에만 수행
						dp[i][j][k] = Math.min(dp[i][j][k], Math.min(dp[i - 2][j][k], dp[i - 2][(j + 2 - k) % 3][(k + 1) % 2]) + home[i - 1][(1 + j + k) % 3] + home[i][j]);
					}
				}
			}
		}
		
		int answer = dp[N][0][0]; // 모든 집을 칠하는 비용의 최솟값
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 2; k++) {
				answer = Math.min(answer, dp[N][j][k]); // 최솟값으로 갱신 
			}
		}
		System.out.println(answer); // 결과 출력
	}
	
}