import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 이분 탐색을 활용한 LIS 알고리즘으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int A[], L[], LIS[][]; // LIS의 길이를 저장할 배열 (LIS의 최종 결과값이 아님!!!)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기 (1 ~ 1000000)
		
		A = new int[N];
		L = new int[N]; // i를포함했을때 LIS의 최대 길이
		LIS = new int[N][2]; // LIS의 길이
		
		int idx = 0; // 현재 LIS 수열의 길이
		StringTokenizer st = new StringTokenizer(br.readLine()); // 수열 A를 이루고 있는 Ai들
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken()); // 현재 값 Ai
			L[i] = 1;
			int key = binarySearch(0, idx - 1, A[i], idx, i); // 인덱스 탐색
			if (key == -1) { // 뒤에 삽입
				LIS[idx][0] = A[i];
				LIS[idx][1] = i;
				idx++;
			} else { // 기존값을 Ai로 치환
				LIS[key][0] = A[i];
				LIS[key][1] = i;
			}
		}
		sb.append(idx).append("\n"); // LIS의 길이
		int cur = idx;
		int[] answer = new int[idx];
		for (int i = N - 1; i >= 0; i--) {
			if (L[i] == cur) {
				answer[--cur] = A[i];
			}
			if (cur == 0) break;
		}
		
		for (int i = 0; i < answer.length; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.append("\n");
		System.out.print(sb);
	}

	/**
	 * 이분탐색 알고리즘
	 * @param left 시작 인덱스
	 * @param right 끝 인덱스
	 * @param target 탐색할 값 (Ai)
	 * @param length 현재 LIS의 길이
	 * @return
	 */
	static int binarySearch(int left, int right, int target, int length, int index) {
		
		// 이분탐색 반복 조건
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (LIS[mid][0] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		// 시작 인덱스가 LIS의 길이보다 작은 경우 -> target값을 기존값중에서 대체 가능한 상태
		if (left < length) {
			L[index] = L[LIS[left][1]];
			return left;
		}
		// target값이 이전 모든값들보다 큰 경우
		if (length - 1 >= 0) {
			L[index] = L[LIS[length - 1][1]] + 1;
		}
		return -1;
	}

}