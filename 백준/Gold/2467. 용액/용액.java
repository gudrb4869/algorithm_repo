import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 이진 탐색 이용하여 문제 해결
 * i(0 ~ n - 2)번째 용액을 기준으로 [i + 1, n - 1]사이의 용액을 이진탐색으로 범위를좁혀나가며 문제를 해결한다. 
 * i번째 용액을 골랐을때 left는 i + 1, right는 n - 1으로 설정
 * left <= right일동안 while문 수행
 * left와 right의 평균값을 mid에 저장
 * arr[i] + arr[mid]의 특성값 sum이 기존의 특성값보다 작거나 같으면 value와 answer 갱신
 * arr[i] + arr[mid]값이 0보다 크거나 같으면 끝값을 0과 가까운 곳으로 범위를 좁혀야하기 때문에 right를 mid - 1로 설정
 * 0보다 작으면 left를 0과 가까운곳으로 범위를 좁혀야하기 때문에 left를 mid + 1로 설정
 * 이후 답 출력
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int value = Integer.MAX_VALUE;
		int[] answer = new int[2];
		
		for (int i = 0; i < n; i++) {
			int left = i + 1;
			int right = n - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				int sum = Math.abs(arr[i] + arr[mid]);
				
				if (value >= sum) {
					value = sum;
					answer[0] = arr[i];
					answer[1] = arr[mid];
				}
				
				if (arr[i] + arr[mid] >= 0) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}

}
