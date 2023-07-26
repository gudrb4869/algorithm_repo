import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 이진 탐색 이용하여 문제 해결
 * i번째 용액을 골랐을 때 [i + 1, n - 1]범위 내에서 나머지 용액 한개를 혼합 특성값이 가장 0에 가까운 위치를 이진탐색으로 찾아낸다.  
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
		
		int[] arr = new int[n]; // n개의 용액의 특성값 저장할 배열
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int value = Integer.MAX_VALUE; // 두용액을 혼합한 특성값을 무한대값으로 초기화
		int[] answer = new int[2]; // 정답 출력하기 위해 사용할 배열
		
		for (int i = 0; i < n; i++) { // 두용액중 하나를 i번째용액으로 선택 
			int left = i + 1; // 이진탐색 시작위치
			int right = n - 1; // 이진탐색 끝위치
			while (left <= right) { // 이진탐색 수행조건
				int mid = (left + right) / 2; // 이진탐색 중간위치
				int sum = Math.abs(arr[i] + arr[mid]); // 혼합한 두용액의 현재특성값 
				
				if (value >= sum) { // 현재특성값이 기존특성값보다 작거나같으면 값 갱신
					value = sum;
					answer[0] = arr[i];
					answer[1] = arr[mid];
				}
				
				if (arr[i] + arr[mid] >= 0) { // 현재특성값이 0보다크거나같으면 끝위치를 0과 가깝게 해야하므로 끝위치를 중간위치-1로 갱신
					right = mid - 1;
				} else { // 현재특성값이 0보다 작으면 시작위치를 0과 가깝게 해야하므로 시작위치를 중간위치+1로 갱신
					left = mid + 1;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}

}
