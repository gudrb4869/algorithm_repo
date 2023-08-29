import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * dp로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1보다 크거나 같고 10^6보다 작거나 같은 정수
		
		memo = new int[N + 1];
		System.out.println(recursion(N));
	}

	private static int recursion(int n) {
		if (n == 1) { // 1을 1로만드는 연산의 횟수는 0
			return 0;
		}
		
		if (memo[n] == 0) {
			if (n % 6 == 0) { // 3과 2로 모두 나누어 떨어지는 경우
				memo[n] = Math.min(recursion(n / 3), recursion(n / 2)) + 1; // 3으로나눈것과 2로 나눈것중 작은거 + 1
			} else if (n % 3 == 0) { // 3으로 나누어 떨어지는 경우
				memo[n] = Math.min(recursion(n / 3), recursion(n - 1)) + 1; // 3으로나눈것과 1뺀것중 작은거 + 1
			} else if (n % 2 == 0) { // 2로 나누어 떨어지는 경우
				memo[n] = Math.min(recursion(n / 2), recursion(n - 1)) + 1; // 2로나눈것과 1뺀것중 작은거 + 1
			} else { // 3과 2로 나누어 떨어지지 않는 경우
				memo[n] = recursion(n - 1) + 1; // 1뺀거 + 1
			}
		}
		
		return memo[n]; // 저장된 값 리턴
	}

}