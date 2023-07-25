import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 집의 개수 N의 범위가 2 <= N <= 200000이고 공유기의 개수 C의 범위가 2 <= C <= N 이므로
 * 이진탐색으로 풀면은 시간초과에서 벗어날 수 있다.
 * 우선 집의 좌표를 정렬하고, 공유기사이 최소거리인 1과 공유기사이 최대거리인 arr[n - 1] - arr[0]을 기준으로 이진탐색알고리즘수행
 * 공유기사이 평균거리 기준으로 이전-현재 공유기 거리가 평균거리보다 크거나 같으면 설치공유기대수를 1증가하고 이전 공유기 좌표를 현재 좌표로 갱신
 * 설치한 공유기 개수가 c보다 작으면 거리가 너무 커서 그런거기때문에 최대거리를 평균거리-1로 갱신
 * c보다 크거나 같으면 최소거리를 평균거리+1로 갱신하면서 결과값을 평균거리로 세팅
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 집 개수
		int c = Integer.parseInt(st.nextToken()); // 공유기 개수
		
		int[] arr = new int[n]; // 집의 좌표 저장할 배열
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr); // 좌표 기준으로 정렬
		int low = 1, high = arr[n - 1] - arr[0]; // 공유기 사이 최소거리 1, 최대거리 양끝좌표의 차이로 설정
		int answer = 0;
		
		while (low <= high) { // 이진 탐색
			int mid = (low + high) / 2; // 공유기 사이 최소거리와 최대거리사이의 중간값
			
			int prev = arr[0]; // 이전 공유기 좌표
			int count = 1; // 설치된 공유기 대수
			
			for (int i = 1; i < n; i++) {
				if (arr[i] - prev >= mid) { // 현재 공유기좌표와 이전공유기좌표의 차가 중간값보다 크거나 같으면 
					count++; // 공유기 대수 1증가
					prev = arr[i]; // 이전 공유기 좌표 갱신
				}
			}
			
			if (count < c) { // 설치된 공유기 대수가 공유기의 개수보다 작으면
				high = mid - 1; // 공유기 사이 최대거리를 중간값 - 1로 세팅
			} else { // 설치된 공유기 대수가 공유기의 개수보다 크거나 같으면
				low = mid + 1; // 공유기 사이 최소거리를 중간값 + 1로 세팅
				answer = mid; // 공유기 사이 최대 거리를 중간값으로 세팅
			}
		}
		
		System.out.println(answer);
	}

}
