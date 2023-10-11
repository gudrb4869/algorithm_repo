import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 페르마의 소정리로 풀었다.
 * nCr = n! / r!(n-r)!
 * 
 * A = n!, B = r!(n-r)! 이라한다면
 * 
 * nCr = A * B^-1 로 표현이  가능하다.
 * 
 * 페르마의 소정리에 의해 A * B^-1 ≡ A * B^(p-2) (mod p) 가 성립한다.
 * 
 * 따라서 nCr % p 의 계산이 가능하다.
 * 
 * B^(p-2)를 계산할때 시간복잡도를 최소화하기위해 로그시간복잡도를 갖도록 알고리즘코드를 구성했다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 1 ~ 20
		long P = 1234567891;
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 1 ~ 1000000
			int R = Integer.parseInt(st.nextToken()); // 0 ~ N
			
			long A = 1; // n! % p
			for (int i = 1; i <= N; i++) {
				A *= i;
				A %= P;
			}
			
			long B = 1; // r!(n-r)! % p
			for (int i = 1; i <= R; i++) {
				B *= i;
				B %= P;
			}
			for (int i = 1; i <= N - R; i++) {
				B *= i;
				B %= P;
			}
			
			long B2 = 1; // B^(p-2) % p
			long exponent = P - 2;
			
			while (exponent > 0) {
				if ((exponent & 1) > 0) {
					B2 *= B;
					B2 %= P;
				}
				
				B *= B;
				B %= P;
				exponent >>= 1;
			}
			
			long answer = A * B2; // A * B^(p-2) % p
			answer %= P;
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}