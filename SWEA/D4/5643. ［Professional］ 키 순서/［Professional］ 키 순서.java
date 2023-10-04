import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N이 최대 500이므로 모든 정점간의 최단경로를 구하려고 플로이드 워셜 알고리즘을 사용함.
 * 정점 i를 기준으로 arr[i][j](진출)와 arr[j][i](진입)이 가능한지 개수를 세어보고 그 값이 N-1과 같으면 몇번째인지 알수있음
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
		
		for (int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine()); // 학생들의 수
			int M = Integer.parseInt(br.readLine()); // 두 학생의 키를 비교한 횟수
			
			int[][] arr = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = 987654321; // 무한대로 초기화
				}
			}
			for (int i = 1; i <= N; i++) { // 자기자신으로 가는 최단거리는 0
				arr[i][i] = 0;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken()); // 번호가 a인 학생
				int b = Integer.parseInt(st.nextToken()); // 번호가 b인 학생
				
				arr[a][b] = 1; // 간선있다는 뜻으로 1로 표시
			}
			
			// 플로이드 워셜 알고리즘
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
			
			// 자신의 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 계산
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				int cnt = 0;
				for (int j = 1; j <= N; j++) {
					if (i != j && arr[i][j] != 987654321) { // 자기자신이 아니면서, i에서 j로 갈수있으면 (진출가능)
						cnt++;
					}
					if (i != j && arr[j][i] != 987654321) { // 자기자신이 아니면서, j에서 i로 올수있으면 (진입가능)
						cnt++;
					}
				}
				
				if (cnt == N - 1) { // 진입 + 진출 개수가 N - 1개 => 몇번째인지 알수있음
					answer++;
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n"); // 테스트 케이스 결과
		}
		
		System.out.print(sb); // 출력
	}

}