import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * F(n) = F(n-1-n%v) + G(n)
 * G(n) = n/v*(n%v+1) + F(n%v)
 * 
 * F(9) = 1*F(9) = 45
 * F(99) = 20*F(9)
 * F(999) = 300*F(9)
 * F(9999) = 4000*F(9)
 * 
 * F(333) = 3*F(99) + (1+2)*100 + 3*34 (백의자리)
 * 		+ 3*F(9) + (1+2)*10 + 3*4 (십의자리)
 * 		+ F(3) (일의자리)
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			
			long answer = calculate(B) - calculate(A - 1);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	// F(n) 계산 알고리즘
	private static long calculate(long num) {
		if (num < 0) return 0;
		
		long sum = 0;
		long exponent = (long) Math.pow(10, (long)Math.log10(num));
		
		while(exponent > 0) {
			long div = num / exponent;
			long mod = num % exponent;
			
			long k = (long) Math.log10(exponent);
			long F9 = 45 * k * exponent / 10;

			long v1 = div * F9;
			long v2 = (div - 1) * div * exponent / 2;
			long v3 = div * (mod + 1);
			sum += v1 + v2 + v3;
			
			num %= exponent;
			exponent /= 10;
		}

		return sum;
	}

}