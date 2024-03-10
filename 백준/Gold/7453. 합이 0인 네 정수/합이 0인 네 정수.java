import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 배열의 크기 (1 <= N <= 4000)
		int N = Integer.parseInt(br.readLine());
		
		long[] A = new long[N];
		long[] B = new long[N];
		long[] C = new long[N];
		long[] D = new long[N];
		
		long[] S1 = new long[N * N];
		long[] S2 = new long[N * N];
		long answer = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			A[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			D[i] = Long.parseLong(st.nextToken());
		}
		
		// A, B 배열의 부분합 배열
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S1[i * N + j] = A[i] + B[j];
			}
		}
		
		// C, D 배열의 부분합 배열
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S2[i * N + j] = C[i] + D[j];
			}
		}
		
		// 정렬
		Arrays.sort(S1);
		Arrays.sort(S2);
		
		// 투 포인터
		int left = 0, right = N * N - 1;
		while (left < N * N - 1 && right >= 0) {
			long sum = S1[left] + S2[right];
			if (sum == 0) {
				long cnt1 = 1;
				long cnt2 = 1;
				
				while (left < N * N - 1 && S1[left] == S1[++left]) {
					cnt1++;
				}
				
				while (right > 0 && S2[right] == S2[--right]) {
					cnt2++;
				}
				
				answer += (cnt1 * cnt2);
			} else if (sum > 0) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(answer);
		
	}

}