import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 페르마의 소정리를 이용하여 문제를 풀었다.
 * nCk = n! / k!(n-k)!
 * A = n!, B = k!(n-k)! 이라한다면
 * nCk = A * B^-1 이다.
 * 페르마의 소정리를 이용한다면
 * A * B^-1 ≡ A * B^(p-2) (mod p) 의 식이 성립한다.
 * 
 * A*B^-1 % p = A*B^(p-2) % p 이므로 분수형태없이 바로 nCk % p 계산이 가능하다.
 * 하지만 M번 수행하기 때문에 factorial 테이블을 미리 만들어서 갖다썼다. 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		final int MAX_SIZE = 4000000, P = 1000000007;

		long[] fac = new long[MAX_SIZE + 1];
		fac[0] = 1;
		for (int i = 1; i <= MAX_SIZE; i++) {
			fac[i] = (i * fac[i - 1]) % P; // 팩토리얼 값 전처리
		}
		
		int M = Integer.parseInt(br.readLine()); // 이항 계수 nCk를 1000000007로 나눈 나머지를 구할 횟수
		
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 자연수 N (1 ~ 4000000)
			int K = Integer.parseInt(st.nextToken()); // 정수 K (0 ~ N)
			
			long A = fac[N]; // n!
			long B = (fac[K] * fac[N - K]) % P; // k!(n-k)!
			
			int exponent = P - 2; // 지수
			long B2 = 1; // B^(p-2) 값
			
			// 분할 정복으로 B^(P-2) 계산
			while (exponent > 0) {
				if ((exponent & 1) == 1) {
					B2 = (B2 * B) % P;
				}
				
				B = (B * B) % P;
				exponent >>= 1;
			}
			
			sb.append((A * B2) % P).append("\n"); // A*B^(P-2) % P -> nCk % P
		}
		
		
		System.out.print(sb);
	}

}