import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * next permutation 메서드를 이용해서 풀었다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int N, p[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		N = Integer.parseInt(br.readLine()); // 순열을 이루는 수의 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 주어진 순열
		
		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		if (nextPermutation()) { // 다음에 오는 순열이 있는 경우
			for (int i = 0; i < N; i++) {
				sb.append(p[i]).append(" ");
			}
		} else { // 마지막에 오는 순열인 경우
			sb.append("-1");
		}
		sb.append("\n");
		
		System.out.print(sb);
	}

	private static boolean nextPermutation() {
		
		int i = N - 1;
		
		while (i > 0 && p[i - 1] >= p[i]) i--;
		
		if (i == 0) return false;
		
		int j = N - 1;
		
		while (p[i - 1] >= p[j]) j--;
		
		swap(i - 1, j);
		
		int k = N - 1;
		
		while (i < k) {
			swap(i++, k--);
		}
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

}