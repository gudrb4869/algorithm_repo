import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사람의 수 (N <= 20)
		int N = Integer.parseInt(br.readLine());

		// L[i] : 세준이가 i번 사람에게 인사시 잃는 체력
		int[] L = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		// J[i] : 세준이가 i번 사람에게 인사시 얻는 기쁨
		int[] J = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			J[i] = Integer.parseInt(st.nextToken());
		}
		
		// 냅색 DP 테이블
		// 행: 사람 번호
		// 열: 소모한 체력
		int[][] D = new int[N + 1][100];
		
		// 냅색 알고리즘
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w < 100; w++) {
				if (w - L[i] >= 0) {
					D[i][w] = Math.max(D[i - 1][w], D[i - 1][w - L[i]] + J[i]);
				} else {
					D[i][w] = D[i - 1][w];
				}
			}
		}
		
		// 세준이가 얻을 수 있는 최대 기쁨
		System.out.println(D[N][99]);
	}

}