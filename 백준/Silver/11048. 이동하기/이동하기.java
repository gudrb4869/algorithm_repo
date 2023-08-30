import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 우, 우하, 하로만 이동 가능
 * dp로 품
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 미로의 크기 N, M이 공백을 기준으로 분리되어 입력
		
		int N = Integer.parseInt(st.nextToken()); // 미로의 세로크기
		int M = Integer.parseInt(st.nextToken()); // 미로의 가로크기
		
		int[][] arr = new int[N + 1][M + 1]; // 미로의 정보 저장할 2차원 배열
		int[][] dp = new int[N + 1][M + 1]; // DP 테이블
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 미로의 각 칸에 놓여져 있는 사탕의 개수
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 1,1에서 시작하여 현재위치까지의 사탕 최대의 개수는 좌상, 좌, 상 중 가장많은 사탕을 가져올수 있는 경로의 최댓값 + 현재 위치의 사탕의 개수
				dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1])) + arr[i][j];
			}
		}
		
		System.out.println(dp[N][M]); // 1,1에서 시작하여 N,M으로 이동할 때 가져올 수 있는 사탕 개수의 최댓값
	}

}