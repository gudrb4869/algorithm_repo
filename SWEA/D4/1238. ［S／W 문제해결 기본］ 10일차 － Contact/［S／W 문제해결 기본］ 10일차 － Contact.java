import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 방향 그래프의 시작정점을 기준으로 그래프 탐색진행
 * 너비우선 탐색과, 깊이우선 탐색중에서
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static List<Integer>[] graph = new List[101]; // 인접 리스트로 그래프 표현
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		for (int tc = 1; tc <= 10; tc++) { // 10개의 테스트 케이스가 주어짐
			
			for (int i = 1; i <= 100; i++) {
				graph[i] = new ArrayList<>();
			}
			
			int[] distance = new int[101];
			Arrays.fill(distance, -1); // -1로 초기화
			
			st = new StringTokenizer(br.readLine()); // 테스트 케이스의 첫 번째 줄
			int length = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길이
			int start = Integer.parseInt(st.nextToken()); // 시작점
			
			
			st = new StringTokenizer(br.readLine()); // 테스트 케이스의 두 번째 줄
			for (int i = 0; i < length / 2; i++) {
				int from = Integer.parseInt(st.nextToken()); // 
				int to = Integer.parseInt(st.nextToken()); //
				graph[from].add(to); // 인접리스트에 추가
			}
			
			// bfs 그래프 탐색
			Queue<Integer> q = new ArrayDeque<>();
			distance[start] = 0; // 시작점의 거리 0으로 세
			q.offer(start); // 큐에 삽입
			
			while (!q.isEmpty()) { // 큐가 비어있기 전까지 반복
				int current = q.poll(); // 현재 정점
				
				for (int next : graph[current]) { // 현재 정점기준 인접 정점
					if (distance[next] == -1) { // -1일때
						distance[next] = distance[current] + 1; // 현재 정점 이동거리+1로 갱신
						q.offer(next); // 큐에 인접 정점 추가
					}
				}
			}
			
			int answer = start; // 가장 늦게 연락을 받는 사람중 가장 번호가 큰 사람
			int max = 0; // 시작점에서 가장 늦게 연락을 받는 사람까지의 이동거리
			for (int i = 1; i <= 100; i++) {
				if (distance[i] >= max) { // 이동거리가 기존최댓값보다 크거나 같으면
					max = distance[i]; // 이동거리 갱신
					answer = i; // 번호 갱신
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}