import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 파이프가 가로로 (i, j-1, i, j)로 놓여져 있는 경우 -> (i-1, j-2, i, j-1)와 (i, j-2, i, j-1) 상태의 파이프를 통해 올수있음
 * 또한 집 arr[i][j-1]와 arr[i][j]가 모두 빈칸이어야 이동 가능
 * 
 * 파이프가 세로로 (i-1, j, i, j)로 놓여져 있는 경우 -> (i-2, j-1, i-1, j)와 (i-2, j, i-1, j) 상태의 파이프를 통해 올수있음
 * 또한 집 arr[i-1][j]와 arr[i][j]가 모두 빈칸이어야 이동 가능
 * 
 * 파이프가 대각으로 (i-1, j-1, i, j)로 놓여져있는 경우 -> (i-2, j-1, i-1, j-1)와 (i-2, j-2, i-1, j-1)와 (i-1, j-2, i-1, j-1) 상태의 파이프를 통해 올수있음
 * 또한 집 arr[i-1][j-1]와 arr[i-1][j], arr[i][j-1], arr[i][j]가 모두 빈칸이어야 이동 가능
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 집의 크기
		
		int[][] arr = new int[N + 1][N + 1]; // (1,1)부터 시작하기 위해 (N+1)*(N+1) 크기로 초기화 
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 집의 상태 저장(0=빈칸, 1=벽)
			}
		}
		
		// 파이프 양끝의 좌표
		int[][][][] dp = new int[N + 1][N + 1][N + 1][N + 1];
		dp[1][1][1][2] = 1; // 초기에 (1,1)과 (1,2)를 차지하고 있고 방향이 가로이므로 경우의수 1로 초기화해줌
		
		// 점화식
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (arr[i][j - 1] != 1 && arr[i][j] != 1 && j - 2 >= 0) {
					dp[i][j - 1][i][j] = dp[i - 1][j - 2][i][j - 1] + dp[i][j - 2][i][j - 1]; // -자
				}
				if (arr[i - 1][j] != 1 && arr[i][j] != 1 && i - 2 >= 0) {
					dp[i - 1][j][i][j] = dp[i - 2][j - 1][i - 1][j] + dp[i - 2][j][i - 1][j]; // |자
				}
				if (arr[i - 1][j - 1] != 1 && arr[i][j] != 1 && arr[i - 1][j] != 1 && arr[i][j - 1] != 1 && i - 2 >= 0 && j - 2 >= 0) {
					dp[i - 1][j - 1][i][j] = dp[i - 2][j - 1][i - 1][j - 1] + dp[i - 2][j - 2][i - 1][j - 1] + dp[i - 1][j - 2][i - 1][j - 1]; // \자
				}
			}
		}
		// 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 모든 경우의 수
		int answer = dp[N - 1][N - 1][N][N] + dp[N - 1][N][N][N] + dp[N][N - 1][N][N];
		
		System.out.println(answer); // 출력
	}

}