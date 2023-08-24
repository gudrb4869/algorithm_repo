import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행
 * 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공
 * 만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공
 * 
 * => 투포인터
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 c
		
		int[] plate = new int[N + k - 1]; // 회전 초밥 벨트
		
		for (int i = 0; i < N; i++) {
			plate[i] = Integer.parseInt(br.readLine()); // 접시 위에 있는 초밥의 번호 저장
		}
		for (int i = N; i < N + k - 1; i++) {
			plate[i] = plate[i % N];
		}
		
		int[] sushi = new int[d + 1]; // 초밥의 종류별 현재 개수 
		
		for (int i = 0; i < k; i++) { // 0번째접시부터 k-1번째 접시까지 k개의 연속된 접시 탐색
			sushi[plate[i]]++; // i번접시에 있는 초밥번호개수 1증가
		}
		
		boolean isIn = false; // 현재 고른 k개의 연속된 접시 내에 쿠폰 번호가 존재하는지 체크할 플래그
		int answer = 0; // 초밥의 가짓수의 최댓값
		int result = 0; // 현재 구간에서 손님이 먹을 수 있는 가짓수

		for (int i = 1; i <= d; i++) { // d가지의 초밥 탐색
			if (sushi[i] > 0) { // 시작점끝점사이에서 i번초밥이 있다면
				if (i == c) { // 쿠폰번호에 있는 초밥과 같다면
					isIn = true; // 플래그 true로 세팅
				}
				result++; // 가짓수 1증가
			}
		}
		if (!isIn) { // 쿠폰번호의 초밥이 해당 구간에 존재하지 않는 경우
			result++; // 가짓수 1증가
		}
		
		answer = result; // 0부터 k-1구간까지의 접시를 연속으로 먹었을때 초밥의 가짓수로 일단 초기화
		
		for (int l = 1; l < N; l++) { // 구간을 한칸씩 뒤로 땡기면서 탐색 (투 포인터)
			
			// 왼쪽 시작점을 뒤로 한칸 땡기기 직전의 접시에 해당하는 초밥이 현구간으로 오면서
			// 0개가 되어 존재하지 않는데, 해당 초밥이 쿠폰번호에 적힌 초밥이 아닐경우
			if (--sushi[plate[l - 1]] == 0 && plate[l - 1] != c) {
				result--; // 가짓수 1감소
			}
			
			int r = l + k - 1; // 현구간의 끝점
			
			// 끝점에 해당하는 접시의 초밥이 기존에 0개였는데 해당초밥이 쿠폰번호와 다른경우
			if (sushi[plate[r]]++ == 0 && plate[r] != c) {
				result++; // 가짓수 1증가
			}
			answer = Math.max(answer, result); // 가짓수 중 최댓값으로 갱신
		}
		
		System.out.println(answer); // 출력
	}

}