import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색을 이용하여 문제 해결
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄 입력
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for (int k = 0; k < K; k++) { // K개의 줄 입력
			st = new StringTokenizer(br.readLine()); // 둘째 줄 ~ K+1번째 줄 입력
			
			int x1 = Integer.parseInt(st.nextToken()); // 왼쪽 아래 꼭짓점 x좌표
			int y1 = Integer.parseInt(st.nextToken()); // 왼쪽 아래 꼭짓점 y좌표
			int x2 = Integer.parseInt(st.nextToken()); // 오른쪽 위 꼭짓점 x좌표
			int y2 = Integer.parseInt(st.nextToken()); // 오른쪽 위 꼭짓점 y좌표
			
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					board[x][y] = 1; // 직사각형이 그려진 영역 1로 표시
				}
			}
		}
		
		List<Integer> list = new ArrayList<>();
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		// 모눈종이 전체 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				// 모눈종이에서 해당 칸이 직사각형이 있는 칸이 아니면서 아직 미방문한 칸인 경우
				if (board[i][j] == 0 && !visited[i][j]) {
					int area = 0; // 영역 넓이
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, j});
					visited[i][j] = true;
					
					// 너비 우선 탐색
					while (!q.isEmpty()) {
						int[] info = q.poll();
						int r = info[0], c = info[1];
						area++;
						
						for (int d = 0; d < 4; d++) { // 사방 탐색
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							// 모눈종이 영역을 벗어났거나, 직사각형 내부이거나, 방문한 영역인경우
							if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 1 || visited[nr][nc]) {
								continue;
							}
							
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc});
						}
					}
					
					list.add(area);
				}
			}
		}
		
		Collections.sort(list);
		
		sb.append(list.size()).append("\n");
		for (int area : list) {
			sb.append(area).append(" ");
		}
		sb.append("\n");
		System.out.print(sb);
	}

}