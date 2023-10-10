import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 가장 긴 증가하는 부분 수열 + 이분 탐색을 이용하여
 * O(nlogn)의 시간복잡도로 문제를 해결했다.
 * n이 최대 40000 이기 때문에 O(n^2)의 시간복잡도를 가지는 다이나믹 프로그래밍 방법이용시 시간초과가 날 수 있다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 첫째 줄
		StringTokenizer st = new StringTokenizer(br.readLine()); // 다음 줄
		
		int[] port = new int[n]; // 왼쪽 n개의 포트가 연결되어야하는 포트 번호를 저장할  배열
		
		for (int i = 0; i < n; i++) {
			port[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] LCS = new int[n]; // 최장 증가 수열의 길이를 구하기 위해 사용할 배열
		int idx = 0; // LCS 배열에서 현재 가리키고 있는 포인터(인덱스)
		for (int i = 0; i < n; i++) {
			int k = binarySearch(0, idx - 1, LCS, port[i], idx); // 이분탐색으로 넣을 위치 알아낸다.
			if (k == -1) { // 가장 긴 증가하는 부분 수열 끝에 붙이는 경우
				LCS[idx++] = port[i];
			} else { // 기존 수열에 있는 값과 바꿔치기
				LCS[k] = port[i];
			}
		}
		
		// 최대 연결 개수 출력
		System.out.println(idx);
	}

	/**
	 * 이분 탐색
	 * @param left 탐색 시작 경계
	 * @param right 탐색 끝 경계
	 * @param arr 탐색할 배열
	 * @param target 찾는 값
	 * @param size 탐색할 배열 크기
	 * @return 찾는 값 위치 (-1인경우 찾는값이 탐색할 배열내의 모든값보다 큼을 의미함)
	 */
	private static int binarySearch(int left, int right, int[] arr, int target, int size) {
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] < target) {
				left = mid + 1;
			} else if (arr[mid] > target) {
				right = mid - 1;
			} else {
				return mid;
			}
		}
		
		if (left >= size) return -1; // target이 arr내의 모든값보다 큼
		
		return left; // 바꿔치기할 위치 리턴
	}

}