import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 수빈이 위치 N(0~100000)
 * 동생의 위치 K(0~100000)
 * 수빈이가 현재 있는 위치X에서 1초동안 X-1, X+1, 2*X로 이동할 수 있음
 * 
 * => bfs로 풀면 될거같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		
		int[] result = new int[100001]; // 수빈이가 온 최소시간을 저장할 배열
		Arrays.fill(result, Integer.MAX_VALUE); // 일단 무한대값으로 초기화
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N); // 수빈이가 있는 위치 큐에 삽입
		result[N] = 0; // 수빈이가 있는 위치 도달시간은 0
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			if (current == K) { // 동생이 있는 위치이면
				break; // 중단
			}
			
			for (int next : new int[]{current * 2, current - 1, current + 1}) {
				if (next >= 0 && next <= 100000 && result[current] + 1 < result[next]) {
					result[next] = result[current] + 1;
					q.offer(next);
				}
			}
		}
		
		System.out.println(result[K]);
	}

}