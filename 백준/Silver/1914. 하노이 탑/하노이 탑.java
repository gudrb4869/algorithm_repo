import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 하노이탑 점화식 An = 2An-1 + 1 => An = 2^n - 1
		String K = BigInteger.ONE.shiftLeft(N).subtract(BigInteger.ONE).toString(10);
		System.out.println(K);
		if (N <= 20) {
			hanoi(N,1,2,3);
			System.out.print(sb);
		}
	}

	static void hanoi(int n, int start, int mid, int end) {
		
		if (n > 1) {
			hanoi(n - 1, start, end, mid);
		}
		sb.append(start).append(" ").append(end).append("\n");
		if (n > 1) {
			hanoi(n - 1, mid, start, end);
		}
		
	}

}