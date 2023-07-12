import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int[][] A = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i][0] = Integer.parseInt(st.nextToken());
			A[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A, new Comparator<int[]>() {
			@Override
			public int compare(int[] S, int[] E) {
				if (S[1] == E[1]) return S[0] - E[0];
				return S[1] - E[1];
			}
		});
		int count = 0;
		int end = -1;
		for (int i = 0; i < N; i++) {
			if (end <= A[i][0]) {
				count++;
				end = A[i][1];
			}
		}
		System.out.println(count);
	}
}