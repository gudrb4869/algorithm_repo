import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * A = n!
 * B = k!(n-k)!
 * p = 1000000007 이라할때
 * 이라할때
 * nCk = A/B = A * B^-1 이다
 * 페르마의 소정리: B ≡ B^p (mod p)
 * A * B^-1 ≡ A * B^(p-2) (mod p)
 * => A * B^-1 mod p = A * B^(p-2) mod p 이므로
 * nCk를 p로 나눈 나머지를 구할 수 있다. 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄 입력
		
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int P = 1000000007; // 1000000007은 소수
		
		long[] fact = new long[N + 1]; // 팩토리얼 값을 P로 나눈 나머지를 저장할 배열
		fact[0] = 1; // 0! = 1
		for (int i = 1; i <= N; i++) {
			fact[i] = (fact[i - 1] * i) % P; // i! = (i-1)! * i, fact[i]에 i!을 P로 나눈 나머지를 저장
		}
		
		long A = fact[N]; // N!
		
		long B = (fact[R] * fact[N - R]) % P; // R!(N-R)!
		
		int exponent = P - 2; // P-2제곱
		long B2 = 1; // B^(P-2)의 계산결과 저장할 변수
		
		// B^(P-2) 계산
		while (exponent > 0) {
			if ((exponent & 1) == 1) { // 1번째 비트값이 1인경우
				B2 = (B2 * B) % P;
			}
			B = (B * B) % P; // B^1 -> B^2 -> B^4 -> ...
			exponent >>= 1; // 오른쪽 시프트 연산
		}
		
		System.out.println((A * B2) % P); // nCr을 P로 나눈 나머지 출력
	}

}