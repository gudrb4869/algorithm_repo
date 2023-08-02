import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * <pre>
 * 묶음의 카드의 수가 A, B이면 두묶음을 하나로 합치기 위해선 A +B 번의 비교를 해야함
 * 매우 많은 카드 묶음이 있을 때 최소 비교 횟수를 구하기 위해선 작은 순부터 높은 순으로 묶어가면 된다.
 * 10 10 10 10 의 경우 (10,10) 두쌍을 먼저 묶고난 다음 (20, 20) 을 묶는게 최소 비교 횟수이다.
 * 따라서 항상 작은 크기 2개만 뽑아내기 위해 우선순위큐를 사용하였다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 카드 묶음의 개수
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위큐 선언
		
		for (int i = 0; i < n; i++) {
			pq.add(Integer.parseInt(br.readLine())); // 카드 묶음의 크기 우선순위큐에 저장
		}
		
		int answer = 0; // 최소 비교횟수 저장할 변수
		
		while (pq.size() > 1) { // 우선순위큐에 값이 2개 이상일 경우 반복문 수행
			
			int first = pq.poll(); // 첫번째로 작은 크기의 카드 묶음 추출
			int second = pq.poll(); // 두번째로 작은 크기의 카드 묶음 추출
			
			int result = first + second; // 두 카드의 비교횟수
			answer += result; // 최소비교횟수에 두카드의 비교횟수를 더함
			pq.add(result); // 두 카드의 비교횟수를 다시 우선순위큐에 저장
		}
		System.out.println(answer); // 출력
	}

}
