import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다이나믹 프로그래밍으로 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄
		int[][] arr = new int[N + 1][3];
		
		int[][] dpMin = new int[N + 1][3];
		int[][] dpMax = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			dpMin[i][0] = Math.min(dpMin[i - 1][0], dpMin[i - 1][1]) + arr[i][0];
			dpMin[i][1] = Math.min(dpMin[i - 1][0], Math.min(dpMin[i - 1][1], dpMin[i - 1][2])) + arr[i][1];
			dpMin[i][2] = Math.min(dpMin[i - 1][1], dpMin[i - 1][2]) + arr[i][2];
			
			dpMax[i][0] = Math.max(dpMax[i - 1][0], dpMax[i - 1][1]) + arr[i][0];
			dpMax[i][1] = Math.max(dpMax[i - 1][0], Math.max(dpMax[i - 1][1], dpMax[i - 1][2])) + arr[i][1];
			dpMax[i][2] = Math.max(dpMax[i - 1][1], dpMax[i - 1][2]) + arr[i][2];
		}
		
		int minScore = 9999999;
		int maxScore = 0;
		for (int i = 0; i < 3; i++) {
			minScore = Math.min(minScore, dpMin[N][i]);
			maxScore = Math.max(maxScore, dpMax[N][i]);
		}
		
		sb.append(maxScore).append(" ").append(minScore).append("\n");
		
		System.out.print(sb);
	}

}