import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * 피보나치 수열의 점화식: F(n+2) = F(n+1) + F(n)
 * 
 * F(n+2) = 시그마(k=0~n)F(k) + 1 = F(0)+F(1)+F(2)+...+F(n) + 1
 * 
 * 피보나치 수열의 a번째 항부터 b번째 항까지의 합: F(b+2) - F(a+1)
 * 
 * {F(n+2) F(n+1)}   {1, 1} ^(n+1)   n>=0
 * {F(n+1) F(n)}   = {1, 0}
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static final int MOD = 1000000000; // 나눌 값
	static Map<Long, long[][]> map = new HashMap<>(); // 메모이제이션 테이블
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄에 a와 b가 공백을 기준으로 주어짐
		
		map.put(1L, new long[][] {{1,1}, {1,0}}); // 행렬 기본 값
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long[][] B = fib(b + 1); // B[0][0] = F(b+2)
		long[][] A = fib(a); // A[0][0] = F(a+1)
		
		long result = (B[0][0] - A[0][0]) % MOD; // 나머지값
		System.out.println(result < 0 ? result + MOD : result); // 나머지가 음수인 경우 MOD만큼더해서 보정
	}

	private static long[][] fib(long n) {
		
		// 메모이제이션 테이블 존재여부 확인후 존재하면 기존 저장된 값 가져다 씀
		if (map.get(n) != null) {
			return map.get(n);
		}
		
		// n이 홀수인 경우 n/2와 n/2 + 1로 분할정복, 짝수인 경우 n/2와 n/2로 분할 정복
		long[][] A = (n & 1) == 1 ? fib((n >> 1) + 1) : fib(n >> 1);
		long[][] B = fib(n >> 1);
		
		// 행렬 계산값
		long[][] result = new long[2][2];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j] = ( result[i][j] + A[i][k] * B[k][j]) % MOD;
				}
			}
		}
		// 메모이제이션 테이블에 저장
		map.put(n, result);
		
		// 행렬 반환
		return result;
	}
}