import java.util.Scanner;

/**
 * <pre>
 * 부분집합을 만들어 문제를 풀었다.
 * 따로 메서드를 만들었는데 현재 인덱스가 가리키고 있는 재료를 검토하기 위해 매개변수로 idx,
 * 재료를 적어도 하나 사용해야 하므로 사용한 재료의 개수를 저장하기 위해 cnt,
 * 현재까지 고른 재료로 만든 신맛과 쓴맛을 저장하기위해 sinmat, sseunmat이라는 매개변수를 넘겨주었다.
 * 종료조건으로 idx가 n이고 cnt가 0보다 클때 신맛과 쓴맛의 차이값이 현재값보다 작으면 갱신
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int n, ingredient[][]; // 재료의 신맛쓴맛을 저장하기 위해 2차원 배열 선언
	private static int answer = 1000000000; // 신맛과 쓴맛은 최소1 최대 10억이므로, 일단 10억으로 초기화함
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); // 재료의 개수
		ingredient = new int[n][2]; // 재료의 개수 * 2 크기만큼 초기화
		
		for (int i = 0; i < n; i++) {
			ingredient[i][0] = sc.nextInt(); // 신맛
			ingredient[i][1] = sc.nextInt(); // 쓴맛
		}
		
		generateSubset(0, 0, 1, 0); // 부분집합 메서드 호출
		System.out.println(answer); // 결과 출력
	}

	private static void generateSubset(int idx, int cnt, int sinmat, int sseunmat) {
		
		if (idx == n) { // 모든 재료에 대한 검토가 끝난 경우
			if (cnt > 0) { // 적어도 하나의 재료를 사용햇을 경우
				answer = Math.min(answer, Math.abs(sinmat - sseunmat)); // 최소값으로 갱신
			}
			return;
		}
		
		// idx에해당하는 재료를 골라서 요리를 한경우
		generateSubset(idx + 1, cnt + 1, sinmat * ingredient[idx][0], sseunmat + ingredient[idx][1]);
		// idx에해당하는 재료를 고르지 않은 경우
		generateSubset(idx + 1, cnt, sinmat, sseunmat);
		
	}

}
