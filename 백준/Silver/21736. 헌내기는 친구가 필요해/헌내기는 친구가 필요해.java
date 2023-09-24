import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그래프 탐색 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째줄
		
		int N = Integer.parseInt(st.nextToken()); // 캠퍼스의 세로 길이
		int M = Integer.parseInt(st.nextToken()); // 캠퍼스의 가로 길이
		
		char[][] arr = new char[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = tmp[j];
				if (arr[i][j] == 'I') { // 현 위치가 도연이가 있는 곳인 경우
					q.offer(new int[] {i, j}); // 도연이가 있는 곳의 좌표 큐에 삽입
					visited[i][j] = true; // 도연이의 위치 방문 체크
				}
			}
		}
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 캠퍼스 밖이거나, 벽이거나, 방문지점인 경우 skip
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == 'X' || visited[nr][nc]) {
					continue;
				}
				
				visited[nr][nc] = true; // 방문 체크
				q.offer(new int[] {nr, nc}); // 큐에 다음 정점의 좌표 삽입
				if (arr[nr][nc] == 'P') { // 사람이 있는 지점인 경우
					answer++; // 1증가
				}
			}
		}
		
		if (answer == 0) System.out.println("TT"); // 아무도 만나지 못한 경우
		else System.out.println(answer); // 도연이가 만날 수 있는 사람의 수 출력
	}

}