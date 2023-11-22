import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬한 후에 투포인터 써서 문제를 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			int left = 0, right = N - 1;
			
			while (left < right) {
				if (left == i) {
					left++;
					continue;
				}
				
				if (right == i) {
					right--;
					continue;
				}
				
				if (A[i] == A[left] + A[right]) {
					answer++;
					break;
				} else if (A[i] > A[left] + A[right]) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(answer);
	}
	

}