import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 테스트케이스 수
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// 날의 수 (2 <= N <= 1000000)
			int N = Integer.parseInt(br.readLine());
			
			// 날 별 주가를 저장할 배열
			int[] A = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				// i번 날의 주가
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			// 최대 이익
			long answer = 0;
			// 현재 주가
			int topPrice = A[N - 1];
			for (int i = N - 2; i >= 0; i--) {
				
				if (A[i] > topPrice) {
					// 현재 주가보다 더 큰 경우 갱신 
					topPrice = A[i];
				} else {
					// 아닌 경우 이익 누적해서 더해줌
					answer += topPrice - A[i];
				}
			}
			
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}

}