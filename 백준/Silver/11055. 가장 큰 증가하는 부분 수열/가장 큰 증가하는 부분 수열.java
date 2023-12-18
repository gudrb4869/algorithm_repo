import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N이 최대 1000이므로 O(N^2) 시간복잡도로 문제 통과가 가능하다.
 * 2중 for문을 통한 다이나믹 프로그래밍으로 문제를 해결했다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기
		
		int[] A = new int[N + 1];
		int[] D = new int[N + 1]; // dp 테이블
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken()); // 수열 A를 이루고 있는 Ai
		}
		
		for (int i = 1; i <= N; i++) {
			D[i] = A[i];
			for (int j = 1; j < i; j++) {
				if (A[j] < A[i]) {
					D[i] = Math.max(D[i], D[j] + A[i]);
				}
			}
		}
		
		int answer = 0; // 수열 A의 합이 가장 큰 증가하는 부분 수열의 합
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, D[i]);
		}
		
		System.out.println(answer);
	}

}