import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 * 길이가 N인 수열이 주어짐
 * 수열의 두 수를 묶어서 서로 곱한 후에 더할 수 있는데 수열의 각 수를 적절히 묶어 합이 최대가 되게 해야하는 문제
 * 
 * 2이상의 두 수는 곱하는게 더좋다.
 * 현재 수가0인 상태에서 홀수개의 수가 남아있고 다음수가 음수인 경우에는 곱하는게 더 좋다.(0과 음수를 묶어서 0으로만든다음, 짝수개만큼의 음수들은 서로 묶는게 더이득)
 * 
 * 위 두가지 경우를 제외한 경우에는 더하는게 더 좋다.
 * 
 * => 우선순위큐를 이용해 max-heap으로 값을 삽입 및 추출하면서 그리디 알고리즘으로 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int N = Integer.parseInt(br.readLine()); // 수열의 크기 (1이상 50이하의 자연수)
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});
		
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine())); // -1000이상 1000이하의 정수
		}
		
		int sum = 0;
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			
			// 아직 우선순위큐가 비어있지 않은상태에서
			// 현재수가 0이하의수이고 다음수가 음수면서 남은수가 홀수개이거나, 현재수와 다음수가 2이상의양수인경우 묶어서 곱하는게 더 좋음
			if (!pq.isEmpty() &&
					((cur <= 0 && pq.peek() < 0 && pq.size() % 2 == 1) || (cur > 1 && pq.peek() > 1))) {
				sum += cur * pq.poll();
			} else { // 이외의 경우에는 묶지 않고 더하는게 좋음
				sum += cur;
			}
		}
		
		System.out.println(sum);
	}

}