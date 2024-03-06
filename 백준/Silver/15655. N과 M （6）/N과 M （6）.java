import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int N, M, A[], R[];
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
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
		
		System.out.print(sb.toString());
	}
	static void combination(int start, int cnt) {
		
		if (cnt == M) {
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb2.append(R[i]).append(" ");
			}
			String result = sb2.toString();
			if (!set.contains(result)) {
				set.add(result);
				sb.append(result).append("\n");
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			R[cnt] = A[i];
			combination(i + 1, cnt + 1);
		}
		
	}

}