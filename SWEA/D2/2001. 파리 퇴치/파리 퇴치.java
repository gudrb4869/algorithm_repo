import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * M * M 크기의 넓이만큼에 있는 수들의 합을 구하는 문제이므로 누적합을 이용했다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 수
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 배열의 크기
			int M = Integer.parseInt(st.nextToken()); // 파리채 크기
			int[][] D = new int[N + 1][N + 1]; // 누적합 2차원 배열
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) { // 값 받으면서 누적합 계산
					D[i][j] = D[i - 1][j] + D[i][j - 1] - D[i - 1][j - 1] + Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0; // 파리채로 내리쳣을때 파리의 최대값
			for (int i = M; i <= N; i++) { // 오른쪽 모서리 칸을 기준으로 M에서 시작
				for (int j = M; j <= N; j++) { 
					int result = D[i][j] - D[i - M][j] - D[i][j - M] + D[i - M][j - M];
					answer = Math.max(answer, result);
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

}
