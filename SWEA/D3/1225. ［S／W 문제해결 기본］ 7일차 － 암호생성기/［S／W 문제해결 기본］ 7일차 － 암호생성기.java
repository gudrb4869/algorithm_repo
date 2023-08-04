import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 큐를 사용함
 * 사이클마다 1,2,3,4,5 씩 빼야하므로 0에서 시작하여 1씩증가하는 변수에 5로나눈나머지에다가 1더한값으로 사이클의 각 단계마다 빼야하는 값 얻어냈다.
 * 현재값에서 감소시킨값이 0보다 작거나 같으면 뒤에 다시넣고 반복문 종료
 * </pre>
 * @author 박형규
 *
 */
public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 10; t++) { // 테스트케이스 10개로 고정
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken()); // 테스트케이스 번호
			
			st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new ArrayDeque<>(); // 큐 선언
			
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken())); // 큐에 초기값 8개 삽입
			}
			
			int num = 0; // 1씩 계속 증가시킬 변수
			
			while (true) {
				int cur = q.poll(); // 현재 값
				int val = num % 5 + 1; // 감소시켜야할 값
				
				if (cur - val <= 0) { // 0보다 작거나 같으면
					q.offer(0); // 다시 뒤에다 넣고
					break; // 종료
				}
				
				q.offer(cur - val); // 감소시킨값을 큐 뒤에다 삽입
				num++; // 1증가
			}
			
			sb.append("#").append(T);
			while (!q.isEmpty()) {
				sb.append(" ").append(q.poll());
			}
			sb.append("\n"); // 출력결과 스트링빌더에 저장
		}
		System.out.println(sb); // 출력
	}
	
}
