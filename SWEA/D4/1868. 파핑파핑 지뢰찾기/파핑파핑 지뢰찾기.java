import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <pre>
 * 지뢰있는곳: *
 * 지뢰없는곳(미클릭): .
 * 지뢰없는곳(클릭): c
 * 지뢰없는칸을 클릭시 최대8방향 칸도 자동으로 숫자표시해줌
 * 8방향에 모두 지뢰가 없는 칸 클릭시 연쇄적으로 1방향이라도 지뢰가 있는 칸까지 연쇄적으로 클릭한 효과가 난다.
 * 지뢰가 없는 모든 칸에 숫자를 표시하기 위해 필요한 최소 클릭 횟수?
 * 
 * 1) 2차원 정수배열에 지뢰가 있는 곳은 9로 초기화해주고 나머지 공간은 0으로 놔둠
 * 2) 지뢰주변 8방향을 1씩 카운팅하여 지뢰가 있는 개수를 표시해준다.
 * 3) 표를순회하며 아직방문체크안한0인칸 발견시 주변 0인칸은 계속 확장하고 1이상 8이하인 칸에서 멈추는 방식으로 bfs 탐색하면서 방문체크해주고 클릭횟수 1카운트한다.
 * 4) 다시한번 표를순회하며 1이상 8이하인 칸이면서 방문체크안된 칸에 대해 개수를 세어준다.
 * 5) 답을 출력한다.
 * </pre>
 * @author 박형규
 */
public class Solution {

	static int[] dr = {-1,-1,0,1,1,1,0,-1}; // 상,우상,우,우하,하,좌하,좌,좌상
	static int[] dc = {0,1,1,1,0,-1,-1,-1}; // 상,우상,우,우하,하,좌하,좌,좌상
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) { // 테스트 케이스마다 수행
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N]; // N*N 표 초기화
			
			for (int r = 0; r < N; r++) {
				char[] temp = br.readLine().toCharArray();
				for (int c = 0; c < N; c++) {
					if (temp[c] == '*') {
						arr[r][c] = 9; // 지뢰가 있는 위치 9로 세팅(9이상의 수=지뢰)
					}
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (arr[r][c] >= 9) { // 지뢰가 있는 위치인 경우
						for (int k = 0; k < 8; k++) { // 지뢰가 있는 곳기준 8방향 1씩 증가시킴
							int nr = r + dr[k];
							int nc = c + dc[k];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) { // 표의범위를 벗어난경우 skip
								continue;
							}
							
							arr[nr][nc]++;
						}
					}
				}
			}
			
			boolean[][] visited = new boolean[N][N]; // 방문 체크 배열
			int answer = 0;
			
			// 최소 클릭으로 지뢰가 없는 모든 칸에 숫자를 표시하려면 0이 있는 공간을 우선적으로 클릭해야함.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 0 && !visited[i][j]) { // 0인칸이고 아직 방문체크가 되지 않은곳이라면 
						Queue<int[]> q = new ArrayDeque<>();
						answer++;
						visited[i][j] = true;
						q.offer(new int[] {i, j});
						
						while (!q.isEmpty()) {
							int[] info = q.poll();
							int r = info[0], c = info[1];
							
							if (arr[r][c] != 0) {
								continue;
							}
							
							for (int k = 0; k < 8; k++) {
								int nr = r + dr[k];
								int nc = c + dc[k];
								
								if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
									continue; // 표를 벗어난 경우
								}
								
								if (arr[i][j] < 9 && !visited[nr][nc]) {
									visited[nr][nc] = true;
									q.offer(new int[] {nr, nc});
								}
							}
						}
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > 0 && arr[i][j] < 9 && !visited[i][j]) {
						answer++;
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}