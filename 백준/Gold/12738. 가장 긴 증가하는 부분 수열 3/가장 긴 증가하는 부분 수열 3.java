import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int LIS[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기 (1 ~ 1000000)
		
		LIS = new int[N];
		int idx = 0;
		StringTokenizer st = new StringTokenizer(br.readLine()); // 수열 A를 이루고 있는 Ai들
		for (int i = 0; i < N; i++) {
			int target = Integer.parseInt(st.nextToken());
			int key = binarySearch(0, idx - 1, target, idx);
			if (key == -1) {
				LIS[idx++] = target;
			} else {
				LIS[key] = target;
			}
		}
		System.out.println(idx);
	}

	static int binarySearch(int left, int right, int target, int length) {
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (LIS[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (left < length) return left;
		return -1;
	}

}