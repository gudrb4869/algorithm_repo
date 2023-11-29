import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬
 * 이분탐색
 * 투포인터
 * 첫번째 용액과 두번째 용액을 i, j인덱스로 고정시키고
 * 세번째용액의 위치를 이분탐색을 통해 찾는다.
 * arr[i] + arr[j] + arr[mid]가 0보다 크거나 같으면 끝값을 0과 가까운위치로 옮겨야하므로 right를 mid-1로 조정
 * 0보다 작으면 시작값을 0과 가까운 위치로 옮겨야하므로 left를 mid+1로 조정
 * 
 * |arr[i] + arr[j] + arr[mid]|가 현재세용액의특성값의최소값보다 작은경우 현재최솟값으로 갱신해주고
 * 세용액의값을 arr[i], arr[j], arr[mid]로 바꿔준다.
 * 2023-11-29(수)
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 전체 용액의 수(3 ~ 5000)
		
		long[] arr = new long[N];
		long value = Long.MAX_VALUE; // 현재 특성값중 최소값
		long[] answer = new long[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken()); // 용액의 특성값
		}
		
		Arrays.sort(arr); // 용액의 특성값 오름차순정렬
		
		for (int i = 0; i < N - 2; i++) { // i : 첫번째 용액의 인덱스
			for (int j = i + 1; j < N - 1; j++) { // j : 두번째 용액의 인덱스
				int left = j + 1, right = N - 1; // left : 이분탐색시작위치, right: 이분탐색끝위치
				while (left <= right) { // 이분탐색 반복조건
					int mid = (left + right) / 2;
					long sum = Math.abs(arr[i] + arr[j] + arr[mid]);
					
					if (value > sum) {
						answer[0] = arr[i];
						answer[1] = arr[j];
						answer[2] = arr[mid];
						value = sum;
					}
					
					if (arr[i] + arr[j] + arr[mid] >= 0) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
			}
		}
		
		System.out.print(answer[0] + " " + answer[1] + " " + answer[2]);
	}

}