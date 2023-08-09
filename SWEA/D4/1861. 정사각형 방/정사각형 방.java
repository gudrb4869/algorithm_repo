import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * N*N의 방이 있는데 상하좌우로 이동이 가능함.
 * 대신에 이동하려는 방이 존재해야하고, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야함.
 * 어떤수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구해야한다.
 * 큐를 이용하여 bfs처럼 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		for (int t = 1; t <= T; t++) { // 케이스마다 반복
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n + 2][n + 2]; // 범위 체크하는거 넘어가기 위해 n*n에 한바퀴 0짜리로 둘러놓으려고 (n+2)*(n+2) 크기로 초기화
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 방 번호 저장
				}
			}
			
			int location = arr[1][1]; // 처음 출발해야하는 방번호
			int total = 1; // 최대 이동가능한 방의 개수
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					int cnt = 1;
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, j});
					while (!q.isEmpty()) { // 큐가 비어있지 않을동안 반복
						int[] info = q.poll();
						int r = info[0], c = info[1];
						
						for (int k = 0; k < 4; k++) { // 현위치기준 상, 하, 좌, 우 탐색
							int nr = r + dr[k];
							int nc = c + dc[k];
							
							if (arr[r][c] + 1 == arr[nr][nc]) { // 현위치방번호보다 정확이 1크면
								q.offer(new int[] {nr, nc}); // 다음위치 큐에삽입
								cnt++; // 방개수1증가
							}
						}
					}
					if (cnt == total) { // 현재 위치에서 출발하여 도달가능한 방의개수가 이전에저장해놓은 위치에서 출발하여 도달가능한 방의 개수와 같으면
						location = Math.min(location, arr[i][j]); // 가장 작은 방번호로 갱신
					} else if (cnt > total) { // 현재 위치에서 출발하여 도달가능한 방의개수가 이전에저장해놓은 위치에서 출발하여 도달가능한 방의 개수보다크면
						location = arr[i][j]; // 방번호 갱신
						total = cnt; // 도달가능한 방개수 갱신
					}
				}
			}
			sb.append("#").append(t).append(" ")
			.append(location).append(" ").append(total).append("\n"); // 출력결과 스트링빌더에 저장
		}
		System.out.print(sb); // 스트링빌더 출력
	}

}
