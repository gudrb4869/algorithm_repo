import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * <M, N> = <10, 12> => <9, 11>
 * 
 *      0     1     2     3     4     5     6     7     8     9     10     11
 *  0 <0,0> <1,1> <2,2> <3,3> <4,4> <5,5> <6,6> <7,7> <8,8> <9,9> <0,10> <1,11>
 *  1 <2,0> <3,1> <4,2> <5,3> <6,4> <7,5> <8,6> <9,7> <0,8> <1,9> <2,10> <3,11>
 *  2 <4,0> <5,1> <6,2> <7,3> <8,4> <9,5> <0,6> <1,7> <2,8> <3,9> <4,10> <5,11>
 *  3 <6,0> <7,1> <8,2> <9,3> <0,4> <1,5> <2,6> <3,7> <4,8> <5,9> <6,10> <7,11>
 *  4 <8,0> <9,1> <0,2> <1,3> <2,4> <3,5> <4,6> <5,7> <6,8> <7,9> <8,10> <9,11>
 *  
 *  
 *  <M, N> = <4, 7> = > <3, 6>
 *      0     1     2     3     4     5     6
 *  0 <0,0> <1,1> <2,2> <3,3> <0,4> <1,5> <2,6>
 *  1 <3,0> <0,1> <1,2> <2,3> <3,4> <0,5> <1,6>
 *  2 <2,0> <3,1> <0,2> <1,3> <2,4> <3,5> <0,6>
 *  3 <1,0> <2,1> <3,2> <0,3> <1,4> <2,5> <3,6>
 *  
 *  
 *  <M, N> = <7, 4> = > <6, 3>
 *      0     1     2     3
 *  0 <0,0> <1,1> <2,2> <3,3>
 *  1 <4,0> <5,1> <6,2> <0,3>
 *  2 <1,0> <2,1> <3,2> <4,3>
 *  3 <5,0> <6,1> <0,2> <1,3>
 *  4 <2,0> <3,1> <4,2> <5,3>
 *  5 <6,0> <0,1> <1,2> <2,3>
 *  6 <3,0> <4,1> <5,2> <6,3>
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			int GCD = gcd(M, N);
			int LCM = M * N / GCD;
			
			int R = M / GCD;
			int i = 0;
			for(; i < R; i++) {
				int r = (y % M + (N * i) % M) % M;
				if (r == x) {
					sb.append(i * N + y + 1).append("\n");
					break;
				}
			}
			if (i == R) {
				sb.append("-1\n");
			}
		}
		
		System.out.print(sb);
	}

	// 유클리드 알고리즘 최대공약수 계산
	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		return gcd(b, a % b);
	}

}