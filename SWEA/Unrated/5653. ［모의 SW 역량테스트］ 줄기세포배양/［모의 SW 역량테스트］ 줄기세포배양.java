import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * bfs와 우선순위큐를 이용하여 문제를 풀었다.
 * </pre>
 * @author 박형규
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 초기 상태에서 줄기 세포가 분포된 영역의 세로 크기
			int M = Integer.parseInt(st.nextToken()); // 초기 상태에서 줄기 세포가 분포된 영역의 가로 크기
			int K = Integer.parseInt(st.nextToken()); // 배양 시간(1~300)
			
			int[][][] arr = new int[K * 2 + N][K * 2 + M][2]; // 배양 용기의 
			boolean[][] visited = new boolean[K * 2 + N][K * 2 + M]; // 배양 용기 해당 영역 방문 관리 배열
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					int startA = a[0] + a[1]; // 세포 a가 활성 상태가 되는 시간
					int startB = b[0] + b[1]; // 세포 b가 활성 상태가 되는 시간
					
					if (startA == startB) { // 활성 상태가 되는 시간이 같으면
						return b[1] - a[1]; // 생명력 수치가 높은 줄기 세포가 먼저 활성화
					}
					
					return startA - startB; // 활성 상태가 되는 시간이 빠른 세포부터 활성화
				}
			});
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[K + i][K + j][0] = 0; // 세포가 처음으로 생긴 시간
					arr[K + i][K + j][1] = Integer.parseInt(st.nextToken()); // 세포의 생명력
					if (arr[K + i][K + j][1] != 0) { // 세포가 존재하는 곳이라면
						pq.offer(new int[] {arr[K+i][K+j][0], arr[K+i][K+j][1], K+i, K+j}); // 우선순위큐에 삽입
						visited[K+i][K+j] = true; // 세포가 있는 위치 방문 체크
					}
				}
			}
			
			int[] dr = {-1,0,1,0}; // 상,우,하,좌
			int[] dc = {0,1,0,-1}; // 상,우,하,좌
			
			for (int currentTime = 1; currentTime < K; currentTime++) { // 1시간후부터 K-1시간까지 세포들의 상태를 변화시킴
				while (!pq.isEmpty() && pq.peek()[0] + pq.peek()[1] <= currentTime) { // 활성상태가 될수있는 세포가 존재한다면
					int[] info = pq.poll();
					int appearTime = info[0], elapsedTime = info[1], r = info[2], c = info[3]; // 세포가 처음으로 생긴시간, 세포의 생명력, 세포의 행, 세포의 열
					
					for (int d = 0; d < 4; d++) { // 상하좌우 4방향 탐색
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nr >= K * 2 + N || nc < 0 || nc >= K * 2 + M || visited[nr][nc]) {
							continue; // 이미 세포가 생겨난 위치이거나 배양 용기 바깥으로 벗어난 경우 진행하지 않음
						}
						
						arr[nr][nc][0] = currentTime + 1; // 해당 영역에 세포가 생겨난 시간 기록
						arr[nr][nc][1] = elapsedTime; // 해당 영역에 세포의 생명력 기록
						visited[nr][nc] = true; // 해당 영역 방문 체크
						pq.offer(new int[] {arr[nr][nc][0], arr[nr][nc][1], nr, nc}); // 우선순위큐에 삽입
					}
				}
			}
			
			int answer = 0;
			for (int i = 0; i < K * 2 + N; i++) {
				for (int j = 0; j < K * 2 + M; j++) {
					if (visited[i][j]) { // 세포가 있거나 있었던 위치라면
						if (arr[i][j][0] + 2 * arr[i][j][1] - 1 >= K) { // 해당 세포가 아직 죽지 않고 남아있다면 
							answer++; // 세포의 개수 1증가
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}