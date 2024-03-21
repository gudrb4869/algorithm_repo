import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사람의 수
		int N = Integer.parseInt(br.readLine());
		
		int[][] A = new int[N][N];
		
		// 무한대로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				A[i][j] = 100;
			}
		}
		
		// 자기자신은 0으로 가중치 초기화
		for (int i = 0; i < N; i++) {
			A[i][i] = 0;
		}
		
		for (int i = 0; i < N; i++) {
			char[] T = br.readLine().toCharArray();
			
			// 친구인 경우 가중치 1로 설정
			for (int j = 0; j < N; j++) {
				if (T[j] == 'Y') {
					A[i][j] = 1;
				}
			}
		}
		
		// 플로이드워셜
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					A[i][j] = Math.min(A[i][j], A[i][k] + A[k][j]);
				}
			}
		}
		
		// 가장 유명한 사람의 2-친구의 수
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			// i의 2-친구의 수
			int count = 0;
			for (int j = 0; j < N; j++) {
				// 자기 자신은 제외
				if (i == j) continue;
				// 2-친구인 경우
				if (A[i][j] <= 2) count++;
			}
			
			answer = Math.max(answer, count);
		}
		
		System.out.println(answer);
	}

}