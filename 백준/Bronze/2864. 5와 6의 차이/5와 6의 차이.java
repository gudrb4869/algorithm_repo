import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 박형규
 *
 */
public class Main {

	static int min, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		calculate(A);
		calculate(B);
		
		System.out.println(min + " " + max);
	}
	private static void calculate(int x) {
		int i = 0;
		while (x > 0) {
			int rem = x % 10;
			
			if (rem == 5) {
				min += rem * Math.pow(10, i);
				max += (rem + 1) * Math.pow(10, i);
			} else if (rem == 6) {
				min += (rem - 1) * Math.pow(10, i);
				max += rem * Math.pow(10, i);
			} else {
				min += rem * Math.pow(10, i);
				max += rem * Math.pow(10, i);
			}
			
			x /= 10;
			i++;
		}
	}

}