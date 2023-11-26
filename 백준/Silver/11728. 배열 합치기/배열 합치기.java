import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬
 * 2023-11-26(일)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 배열 A의 크기(1 ~ 1000000)
		int M = Integer.parseInt(st.nextToken()); // 배열 B의 크기(1 ~ 1000000)
		
		st = new StringTokenizer(br.readLine());
		
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		int[] B = new int[M];
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0, j = 0;
		while (i < N && j < M) {
			if (A[i] < B[j]) {
				sb.append(A[i++]).append(" ");
			} else {
				sb.append(B[j++]).append(" ");
			}
		}
		
		while (i < N) {
			sb.append(A[i++]).append(" ");
		}
		
		while (j < M) {
			sb.append(B[j++]).append(" ");
		}
		
		System.out.println(sb);
	}

}