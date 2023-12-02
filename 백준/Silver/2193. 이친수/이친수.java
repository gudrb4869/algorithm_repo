import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 이친수: 0과 1로만 이루어진 수
 * 이친수 규칙
 * 1) 0으로시작하지않음
 * 2) 1이 두번 연속으로 나타나지 않음
 * 
 * => 규칙찾아보면 피보나치수 점화식과 같다.
 * 
 * 하향식(top-down) 방식의 다이나믹 프로그래밍으로 풀기 위해 memo 배열을 만들어서 이전에 계산한 값들을 재활용함
 * 
 * 2023-12-02(토)
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	static long[] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		memo = new long[N + 1];

		System.out.println(pinaryNumber(N));
	}

	static long pinaryNumber(int n) {
		if (n <= 2) return 1;
		
		if (memo[n] == 0) {
			memo[n] = pinaryNumber(n - 2) + pinaryNumber(n - 1);
		}
		
		return memo[n];
	}

}