import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * I n -> 정수 n을 Q에 삽입하는 연산
 * D 1 -> Q에서 최댓값을 삭제하는 연산
 * D -1 -> Q에서 최솟값을 삭제하는 연산
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 입력 데이터의 수
		
		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(br.readLine()); // Q에 적용할 연산의 개수
			
			Map<Integer, Integer> map = new HashMap<>();
			
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙

			PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 최소힙
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken(); // 연산을 나타내는 문자(D또는 I)
				int n = Integer.parseInt(st.nextToken()); // 정수
				
				if (cmd.equals("I")) { // 정수 n을 Q에 삽입하는 연산
					maxHeap.offer(n);
					minHeap.offer(n);
					map.put(n, map.getOrDefault(n, 0) + 1); // 해쉬맵에 정수의 현재 개수 1증가하여 저장
				} else {
					if (n == 1) { // Q에서 최댓값을 삭제하는 연산
						while (!maxHeap.isEmpty() && map.get(maxHeap.peek()) == 0) { // 최대힙에서 불필요한 값들 삭제 
							maxHeap.poll();
						}
						if (!maxHeap.isEmpty()) {
							int max = maxHeap.poll();
							map.put(max, map.get(max) - 1);
						}
					} else { // Q에서 최솟값을 삭제하는 연산
						while (!minHeap.isEmpty() && map.get(minHeap.peek()) == 0) { // 최소힙에서 불필요한값 삭제
							minHeap.poll();
						}
						if (!minHeap.isEmpty()) {
							int min = minHeap.poll();
							map.put(min, map.get(min) - 1);
						}
					}
				}
			}
			
			while (!maxHeap.isEmpty() && map.get(maxHeap.peek()) == 0) { // 최대힙에서 불필요한 값들 삭제 
				maxHeap.poll();
			}
			while (!minHeap.isEmpty() && map.get(minHeap.peek()) == 0) { // 최소힙에서 불필요한값 삭제
				minHeap.poll();
			}
			
			if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				sb.append(maxHeap.peek()).append(" ").append(minHeap.peek()).append("\n");
			} else {
				sb.append("EMPTY\n");
			}
		}
		
		System.out.print(sb);
	}

}