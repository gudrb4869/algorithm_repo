import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개의 과자 봉지가 있고 M이하로 과자 2개를 들고갔을때 두 봉지 무게합의 최대를 구해야한다.
 * 과자무게를 오름차순 정렬한뒤 투포인터를 이용하여 O(N)의 시간복잡도로 문제를 풀었다.
 * </pre>
 * @author 박형규
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int t = 1; t <= tc; t++) { // 케이스 반복
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 과자 봉지의 개수
			int m = Integer.parseInt(st.nextToken()); // 무게 합 제한

			int[] arr = new int[n]; // n개의 과자 봉지의 무게를 저장할 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr); // 오름차순 정렬
			int answer = -1; // 기본값으로 -1 저장
			int left = 0, right = n - 1; // 투포인터 시작,끝점 설정
			while (left < right) { // 반복 조건
				if (arr[left] + arr[right] > m) { // 투포인터가 가리키는 과자봉지 무게합이 m보다 클경우
					right--; // right 포인터 1감소
				} else { // 투포인터가 가리키는 과자봉지 무게합이 m보다 작거나 같은 경우
					answer = Math.max(answer, arr[left] + arr[right]); // 최대값으로 갱신
					left++; // left 포인터 1증가
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n"); // 출력결과 저장
		}
		System.out.print(sb); // 출력
	}

}
