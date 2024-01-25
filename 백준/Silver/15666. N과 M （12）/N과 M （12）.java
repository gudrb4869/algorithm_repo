import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, numbers[], selected[];
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		selected = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		dfs(0, 0);
		
		System.out.print(sb);
		
	}

	static void dfs(int start, int cnt) {
		
		if (cnt == M) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < M; i++) {
				s.append(selected[i]).append(" ");
			}
			s.append("\n");
			String sequence = s.toString();
			if (!set.contains(sequence)) {
				set.add(sequence);
				sb.append(sequence);
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			selected[cnt] = numbers[i];
			dfs(i, cnt + 1);
		}
		
	}

}