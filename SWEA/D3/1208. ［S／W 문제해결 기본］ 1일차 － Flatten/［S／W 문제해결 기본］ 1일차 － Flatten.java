import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 박형규
 *
 */
public class Solution {

	private static int dump;
	private static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			dump = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			arr = new int[100];
			
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(String.format("#%d %d\n", t, recursion(0)));
		}
		System.out.print(sb);
	}

	private static int recursion(int x) {
		int[] t = find();
		int min = t[0], max = t[1];
		if (x == dump) {
			return arr[max] - arr[min];
		}
		arr[max]--;
		arr[min]++;
		return recursion(x + 1);
	}
	
	private static int[] find() {
		int min = 0, max = 0;
		for (int i = 0; i < 100; i++) {
			if (arr[i] < arr[min]) {
				min = i;
			}
			if (arr[i] > arr[max]) {
				max = i;
			}
		}
		return new int[] {min, max};
	}

}
