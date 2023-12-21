import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개의 자연수 중에서 M개를 고른 수열을 사전순으로 출력해야 한다.
 * 대신 중복되는 수열은 한번만 출력해야 함.
 * 
 * => 백트래킹으로 순열 구현한다음, 기저조건에서 순열이 기존까지 출력했던 순열과 일치하는지 검사하고 없으면 출력해서 중복을 없앴다.
 * 2023-12-21(목)
 * </pre>
 */
public class Main {

	static int N, M, A[], numbers[];
	static boolean selected[];
	static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		selected = new boolean[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		recursion(0);
	}

	static void recursion(int cnt) {
		
		if (cnt == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			String result = sb.toString();
			if (!set.contains(result)) {
				System.out.print(result);
				set.add(result);
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			
			numbers[cnt] = A[i];
			selected[i] = true;
			recursion(cnt + 1);
			selected[i] = false;
		}
		
	}

}