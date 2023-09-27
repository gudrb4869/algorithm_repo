import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 플로이드 워샬 알고리즘으로 풀었다.
 * 0이면 무한대로 설정하고 1일때만 1을 넣어줌
 * 자기자신으로 가는 가중치는 0으로 만들어주었다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 수
		
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 사람 수
			
			int[][] D = new int[N][N]; // 인접행렬
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					D[i][j] = Integer.parseInt(st.nextToken()) == 1 ? 1 : 987654321; // 값 세팅
				}
			}
			
			for (int i = 0; i < N; i++) {
				D[i][i] = 0; // 자기 자신으로 가는 가중치는 0
			}
			
			// 플로이드-워샬 알고리즘
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			
			int answer = 987654321; // CC값 중 최솟값
			for (int i = 0; i < N; i++) {
				int cc = 0; // i번째 사람의 CC값
				for (int j = 0; j < N; j++) {
					cc += D[i][j]; // CC값 누적
				}
				answer = Math.min(answer, cc); // 최솟값으로 업데이트
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n"); // 각 테스트 케이스 수행결과
		}
		
		System.out.print(sb); // 모든 테스트 케이스 결과 한꺼번에 출력
	}

}