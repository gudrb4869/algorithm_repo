import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 이 문제는 이분탐색으로 풀이시 O(nlogn)의 시간복잡도로 풀이가 가능하다.
 * LIS(Longest Increasing Subsequence) 수열을 놓고 하나씩 채워나가면서 LIS를 유지하기 위한 최적의 위치에 수를 삽입한다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n]; // 수열 A
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> lis = new ArrayList<>(); // 최장 증가 부분 수열을 저장하기 위한 공간
		
		lis.add(arr[0]); // lis 초기값으로 수열A의 첫번째값 삽입
		
		for (int i = 1; i < n; i++) {
			if (lis.get(lis.size() - 1) < arr[i]) {
				lis.add(arr[i]); // 현재값이 lis의 마지막값보다 크면 그냥 삽입
			} else {
				int idx = binarySearch(lis, arr[i], 0, lis.size() - 1); // 현재값이 lis의 마지막값보다 작거나 같으면 삽입할 위치 계산
				lis.set(idx, arr[i]); // idx위치 바꿔치기
			}
		}
		
		System.out.println(lis.size());
	}

	/**
	 * 이진 탐색
	 * @param lis 최장 증가 부분 수열
	 * @param value 현재 값
	 * @param low lis 시작인덱스
	 * @param high lis 끝인덱스
	 * @return 현재 값을 lis 넣을 위치 인덱스
	 */
	private static int binarySearch(List<Integer> lis, int value, int low, int high) {
		while (low <= high) {
			int mid = (low + high) / 2;
			if (value <= lis.get(mid)) { // 현재값과 lis의 중간점 값 비교
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

}
