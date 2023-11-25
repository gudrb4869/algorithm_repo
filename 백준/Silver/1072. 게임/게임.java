import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 이분 탐색
 * 2023-11-25(토)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		
		if (X - Y <= X / 100) {
			System.out.println(-1);
			return;
		}
		
		long Z = (long) (100.0 * Y / X);
		System.out.println(binarySearch(X, Y, Z));
	}

	static long binarySearch(long X, long Y, long Z) {
		long left = 1, right = X;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			
			long result = (long) (100.0 * (Y + mid) / (X + mid));
			if (result == Z) {
				left = mid + 1;
			} else if (result > Z) {
				right = mid - 1;
			}
		}
		
		return left;
	}

}