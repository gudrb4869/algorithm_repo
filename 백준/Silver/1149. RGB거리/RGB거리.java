import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
 * 집을 칠하는 비용의 최솟값
 * - 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * - N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * - i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * N개의 집을 칠하는 비용은
 * N-1번째 집을 빨강으로 칠했을때 비용, N-1번째 집을 초록으로 칠했을때 비용, N-1번째 집을 파랑으로 칠했을때 비용 세가지에서
 * N번째 집을 초록,파랑중 작은거로 칠했을때, N번째 집을 빨강,파랑중 작은거로 칠햇을때, N번째 집을 빨강,초록중 작은거로 칠했을때 중에서
 * 최소값이다.
 * 
 * 점화식을 세워보면
 * i번째 집을 j번째 색으로 칠했을때 1부터 i번째 집까지의 집을 칠하는 최소 비용
 * D[i][j] = min(D[i - 1][(j + 1) % 3], D[i - 1][(j + 2) % 3]) + Cost[i][j]
 * => i-1번째 집을 (j+1)%3번째 색으로 칠했을때 비용과 i-1번째 집을 (j+2)%3번째 색으로 칠했을때 비용의 최솟값 + i번째집을 j번째 색으로 칠했을 때 비용
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 집의 수
		
		int[][] arr = new int[N + 1][3]; // 각 집을 빨강, 초록, 파랑으로 칠하는 비용
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) { // 빨강, 초록, 파랑
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 3; j++) { // 점화식
				arr[i][j] += Math.min(arr[i - 1][(j + 1) % 3], arr[i - 1][(j + 2) % 3]);
			}
		}
		
		// 모든 집을 칠하는 비용의 최솟값
		int answer = Math.min(arr[N][0], Math.min(arr[N][1], arr[N][2]));
		System.out.println(answer); // 출력
	}

}