import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 순열을 이용하여 나올 수 있는 모든 순서를 얻는다.
 * 최대값으로 갱신하여 답을 구한다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	static int N, answer;
	static int[] A, numbers;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 3 ~ 8
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 배열 A에 들어있는 정수
		
		A = new int[N];
		numbers = new int[N];
		selected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0);
		
		System.out.println(answer);
	}

	private static void permutation(int cnt) {
		
		if (cnt == N) {
			int value = 0;
			for (int i = 0; i < N - 1; i++) {
				value += Math.abs(numbers[i] - numbers[i + 1]);
			}
			answer = Math.max(answer, value);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!selected[i]) {
				selected[i] = true;
				numbers[cnt] = A[i];
				permutation(cnt + 1);
				selected[i] = false;
			}
		}
	}

}