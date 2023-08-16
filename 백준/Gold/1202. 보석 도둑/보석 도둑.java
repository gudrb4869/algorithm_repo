import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개의 보석존재, 각보석은 무게 Mi, 가격 Vi 가지고 있음
 * K개의 가방 존재 각 가방에 담을수 있는 최대무게 Ci
 * 가방에는 한개의 보석만 넣을 수 있음!
 * 따라서 가방은 무게기준으로 오름차순정렬하고 i번째가방에 넣을수 있는 보석들을 우선순위큐에 넣은다음
 * 가격이 제일높은 보석을 꺼내주는 방식으로 문제를 풀었다.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 보석의 개수
		int K = Integer.parseInt(st.nextToken()); // 가방의 개수
		
		int[][] jem = new int[N][2]; // 보석의 무게,가격을 담을 2차원 배열
		int[] bag = new int[K]; // 가방의 무게를 담을 1차원 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jem[i][0] = Integer.parseInt(st.nextToken()); // 보석의 무게
			jem[i][1] = Integer.parseInt(st.nextToken()); // 보석의 가격
		}
		Arrays.sort(jem, new Comparator<int[]>() { // 보석의 무게기준 오름차순 정렬, 무게같으면 가격기준 오름차순 정렬
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) return a[1] - b[1];
				return a[0] - b[0];
			}
		});
		
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine()); // 가방의 무게
		}
		Arrays.sort(bag); // 가방무게 오름차순정렬
		
		long answer = 0; // 훔칠 수 있는 보석 가격의 합
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) { // 보석의 가격높은순, 무게낮은순으로 먼저 나오도록함
				if (b[1] == a[1]) return a[0] - b[0];
				return b[1] - a[1];
			}
		}); // 보석의 정보를 담을 우선순위 큐
		
		int index = 0; // 살펴보고자 하는 보석의 인덱스
		for (int i = 0; i < K; i++) { // 가방을 기준으로 탐색
			
			while (index < N && jem[index][0] <= bag[i]) { // 인덱스가 범위를 넘지 않았고, 현재인덱스의 보석의 무게가 가방의 무게보다 작거나 같으면
				pq.offer(jem[index]); // 우선순위큐에 넣고
				index++; // 인덱스 1증가
			}
			
			if (!pq.isEmpty()) { // 우선순위 큐에 데이터가 들어있다면
				answer += pq.poll()[1]; // 가장 가격이 높은 보석 더해줌
			}
			
		}
		
		System.out.println(answer); // 보석가격의 합의 최댓값 출력
	}
}