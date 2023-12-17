import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 어느 회원의 점수는 1점 => 다른 모든 회원과 친구 관계
 * 어느 회원의 점수가 2점 => 다른 모든 회원과 친구 관계이거나, 친구의 친구
 * 어느 회원의 점수가 3점 => 다른 모든 회원과 친구 관계이거나, 친구의 친구, 친구의 친구의 친구
 * 
 * 플로이드 워셜 알고리즘을 이용하여  회원들간의 친구 관계를 만들었다.
 * 그 다음 각 회원마다 가장 큰 값이 그 회원의 점수가 되므로 P 배열에 점수를 저장했다.
 * 점수중에서 최솟값을 찾고, 최솟값에 해당하는 회원의 수와 회원의 번호들을 출력했다.
 * 2023-12-17(일)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 회원의 수
		
		int[][] D = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				D[i][j] = 100000; // 무한대로 초기화
			}
		}
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			if (A == -1 && B == -1) { // 마지막 줄인 경우 break
				break;
			}
			
			D[A][B] = D[B][A] = 1; // 친구 관계 형성
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
				}
			}
		}
		
		int[] P = new int[N + 1]; // 회원의 점수를 저장할 배열
		Arrays.fill(P, 100000); // 무한대로 초기화
		int minScore = 100000;
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			int score = 0;
			for (int j = 1; j <= N; j++) {
				score = Math.max(score, D[i][j]);
			}
			P[i] = score; // i번회원의 점수
			minScore = Math.min(minScore, P[i]); // 회장 후보의 점수 갱신
		}
		
		for (int i = 1; i <= N; i++) {
			if (minScore == P[i]) cnt++; // 회장 후보의 수 누적
		}
		
		sb.append(minScore).append(" ").append(cnt).append("\n"); // 첫번째줄 출력결과
		
		for (int i = 1; i <= N; i++) {
			if (minScore == P[i]) sb.append(i).append(" "); // 두번째줄 출력결과
		}
		sb.append("\n");
		
		System.out.print(sb);
	}
	
}