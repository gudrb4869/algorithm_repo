import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬, 이분탐색
 * 2023-12-03(일)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // A의 수
			int M = Integer.parseInt(st.nextToken()); // B의 수
			
			long[] A = new long[N]; // A의 크기 각각 저장할 배열
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Long.parseLong(st.nextToken());
			}
			
			Arrays.sort(A); // A 오름차순 정렬
			
			long[] B = new long[M]; // B의 크기 각각 저장할 배열
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Long.parseLong(st.nextToken());
			}
			
			Arrays.sort(B); // B 오름차순 정렬
			
			int answer = 0; // A가 B보다 큰 쌍의 개수
			
			// 시간복잡도 A순차탐색 + B이분탐색 => O(nlogn)
			for (int i = 0; i < N; i++) {
				int left = 0, right = M - 1;
				while (left <= right) {
					int mid = (left + right) / 2;
					
					if (A[i] > B[mid]) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
				answer += left;
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}