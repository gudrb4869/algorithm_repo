import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 2중 for문돌면서 완전 탐색하면 풀이가 가능하다.
 * A와 B의 길이가 최대 50이기때문
 * 2023-11-20 (월)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		char[] A = st.nextToken().toCharArray();
		char[] B = st.nextToken().toCharArray();
		
		int N = A.length;
		int M = B.length;
		
		int answer = 99;
		for (int i = 0; i < M - N + 1; i++) {
			int diff = 0;
			for (int j = 0; j < N; j++) {
				if (A[j] != B[i + j]) {
					diff++;
				}
			}
			answer = Math.min(answer, diff);
		}
		
		System.out.println(answer);
	}

}