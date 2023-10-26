import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 순열 알고리즘으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, arr[], numbers[];
	static boolean selected[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		selected = new boolean[N];
		numbers = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		combination(0);
		
		System.out.print(sb);
	}

	private static void combination(int cnt) {
		
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			
			selected[i] = true;
			numbers[cnt] = arr[i];
			combination(cnt + 1);
			selected[i] = false;
		}
	}

}