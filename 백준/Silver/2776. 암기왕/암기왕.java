import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 퀵정렬
 * 이분탐색
 * 2023-11-30(목)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] arr = new int[1000000];

		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 수첩 1에 적어 놓은 정수의 개수(1~1000000)
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			quickSort(0, N - 1, arr);
			
			int M = Integer.parseInt(br.readLine()); // 수첩 2에 적어 놓은 정수의 개수(1~1000000)
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < M; i++) {
				int target = Integer.parseInt(st.nextToken());
				sb.append(binarySearch(0, N - 1, arr, target) ? "1\n" : "0\n");
			}
		}
		System.out.print(sb);
	}

	// 이분탐색
	static boolean binarySearch(int left, int right, int[] arr, int target) {
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] == target) {
				return true;
			} else if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return false;
	}

	// 퀵정렬
	static void quickSort(int left, int right, int[] arr) {
		if (left < right) {
			int mid = quick(left, right, arr); // pivot값의 위치
			quickSort(left, mid - 1, arr); // pivot값기준 왼쪽 퀵정렬(pivot값보다 작거나같은값들의모음)
			quickSort(mid + 1, right, arr); // pivot값기준 오른쪽 퀵정렬(pivot값보다 큰값들의 모음)
		}
	}

	// pivot값의 인덱스 탐색후 해당위치값을 pivot으로 swap
	static int quick(int left, int right, int[] arr) {
		int pivot = arr[left];
		int i = left + 1, j = right;
		
		while (i <= j && i <= right && j > left) {
			if (arr[i] > arr[j]) {
				swap(i, j, arr);
			}

			if (arr[i] <= pivot) i++;
			if (arr[j] > pivot) j--;
		}
		
		swap(left, j, arr);
		
		return j;
	}

	// swap 함수
	static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}