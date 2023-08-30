import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개의 서쪽 사이트를 M개의 동쪽 사이트에  다리로 연결하는데
 * 다리는 서로 겹칠 수 없고 한 사이트에는 최대 한개의 다리만 연결 가능하다.
 * 이때 다리를 지을 수 있는 경우의 수를 구하는 문제
 * 
 * 서쪽 사이트 i개, 동쪽 사이트 j개일때 i개의 다리로 연결할 수 있는 경우의 수 D[i][j]
 * =>
 * 1) i번째 서쪽사이트를 j번째 동쪽사이트에 연결한 경우
 * = 서쪽 사이트 i-1개, 동쪽 사이트 j-1개일때 i-1개의 다리로 연결할 수 있는 경우의 수 D[i-1][j-1]
 * 2) i번째 서쪽사이트를 j번째 동쪽사이트에 연결안한 경우
 * = 서쪽 사이트 i개, 동쪽 사이트 j-1개일때 i개의 다리로 연결할 수 있는 경우의 수 D[i][j-1]
 * 
 * 점화식을 세우면 D[i][j] = D[i-1][j-1] + D[i][j-1] 이다.
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine());
			// 0 < N <= M < 30
			int N = Integer.parseInt(st.nextToken()); // 강의 서쪽에 있는 사이트의 개수
			int M = Integer.parseInt(st.nextToken()); // 강의 동쪽에 있는 사이트의 개수
			
			int[][] dp = new int[N + 1][M + 1]; // DP 테이블
			for (int j = 1; j <= M; j++) {
				dp[1][j] = j; // 서쪽사이트가 1개이고 동쪽사이트가 j개일때 경우의 수는 점화식으로 도출이 안되므로 직접 초기화해줌
			}
			
			// 점화식
			for (int i = 2; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
				}
			}
			
			sb.append(dp[N][M]).append("\n"); // 테스트케이스 경우의 수 스트링빌더에 삽입
		}
		
		System.out.print(sb); // 모든 테스트 케이스 결과 한꺼번에 출력
	}

}