import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 페르마의 소정리, 분할 정복을 이용해서 문제를 풀었다.
 * 
 * nCk = n! / k!(n-k)!
 * A = n!, B = k!(n-k)! 이라한다면
 * nCk = A * B^-1 이다.
 * 
 * 페르마의 소정리를 이용한다면
 * A * B^-1 ≡ A * B^(p-2) (mod p) 의 식이 성립한다.
 * 
 * A*B^-1 % p = A*B^(p-2) % p 이므로 분수형태없이 바로 nCk % p 계산이 가능하다.
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		final int P = 1000000007;

		StringTokenizer st = new StringTokenizer(br.readLine());
			
		int N = Integer.parseInt(st.nextToken()); // 1 ~ 4000000
		int K = Integer.parseInt(st.nextToken()); // 0 ~ N
			
			long A = 1; // n!
			for (int i = 1; i <= N; i++) { // n!
				A = (A * i) % P;
			}
			long B = 1; // k!(n-k)!
			for (int i = 1; i <= K; i++) { // k!
				B = (B * i) % P;
			}
			for (int i = 1; i <= N - K; i++) { // (n-k)!
				B = (B * i) % P;
			}
			
			int exponent = P - 2; // 지수
			long B2 = 1; // B^(p-2) 값
			
			// 분할 정복으로 B^(P-2) 계산
			while (exponent > 0) {
				if ((exponent & 1) == 1) { // 현재 지수가 홀수인경우
					B2 = (B2 * B) % P;
				}
				
				B = (B * B) % P;
				exponent >>= 1; // 오른쪽 시프트 -> 2로나눈 정수값
			}
			
		System.out.print((A * B2) % P);
	}

}