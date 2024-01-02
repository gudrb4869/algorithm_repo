import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * 중복조합 문제
 * </pre>
 */
public class Main {

	static int N, M, A[], R[];
	static Set<String> s = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		R = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		combination(0, 0);
	}

	static void combination(int start, int cnt) {
		
		if (cnt == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < M; i++) {
				sb.append(R[i]).append(" ");
			}
			sb.append("\n");
			
			String result = sb.toString();
			
			if (!s.contains(result)) {
				System.out.print(result);
				s.add(result);
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			R[cnt] = A[i];
			combination(i, cnt + 1);
		}
	}

}