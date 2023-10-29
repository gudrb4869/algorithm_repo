import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 가장 긴 증가하는 부분 수열(LCS)를 이분 탐색을 이용하여 풀면 시간복잡도 O(nlogn)으로 풀이가 가능하다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 첫 줄 입력. 전봇대의 개수(1~100000)
		StringTokenizer st = new StringTokenizer(br.readLine()); // 둘째 줄 입력. N보다 작거나 같은 자연수 N개가 주어짐.
		
		int[] arr = new int[N]; // arr[i]: 길 왼쪽에 i번째 전봇대와 연결된 길 오른편의 전봇대가 몇 번 전봇대인지를 나타냄
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] LCS = new int[N]; // 가장 긴 증가하는 부분 수열의 길이를 저장할 배열(이 배열에 저장된 일련의 값들이 가장 긴 증가하는 부분 수열의 결과가 아님에 유의)
		int idx = 0; // LCS배열에서 현재 가리키고 있는 포인터.
		
		// 가장 긴 증가하는 부분 수열 o(n) + 이분 탐색 o(log n)
		// 시간 복잡도: o(nlogn)
		for (int i = 0; i < N; i++) {
			int result = binarySearch(0, idx, LCS, arr[i], idx);
			if (result == -1) {
				LCS[idx++] = arr[i];
			} else {
				LCS[result] = arr[i];
			}
		}
		
		System.out.println(N - idx); // 최소 잘라내야 하는 전선의 개수
	}

	// 가장 긴 증가하는 부분 수열에서 target을 넣을 위치 이분 탐색
	// left가 size보다 작은 경우 -> target을 중간에 바꿔치기 가능
	// left가 size보다 크거나 같은 경우 -> target이 lcs배열내 모든값들보다 커서 끼워넣을 수없음. 무조건 뒤에 삽입한다는 의미로 -1리턴
	static int binarySearch(int left, int right, int[] LCS, int target, int size) {
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (LCS[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return left >= size ? -1 : left;
	}

}