import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그래프 탐색 문제, 벽부순횟수를 상태에 저장해야하므로 방문체크배열대신 거리저장할 3차원배열 사용
 * 우선순위큐로 너비우선탐색하면서 최단거리 찾음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력 첫째 줄. N, M, K가 공백을 두고 주어짐
		int N = Integer.parseInt(st.nextToken()); // 행렬의 행 길이
		int M = Integer.parseInt(st.nextToken()); // 행렬의 열 길이
		int K = Integer.parseInt(st.nextToken()); // 부술수 있는 벽의 개수
		
		char[][] arr = new char[N][M]; // N*M 행렬
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray(); // N개의 줄에 M개의 숫자로 주어진 맵을 행렬에 저장
		}
		
		int[][][] distance = new int[N][M][K + 1]; // 벽부순횟수가 0~K회 이므로 K+1크기로 만듦
		int INF = 987654321;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < K + 1; k++) {
					distance[i][j][k] = INF; 
				}
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]; // 비용 오름차순
			}
		});
		distance[0][0][0] = 1; // 출발지점의 최단거리 1. 시작하는칸도 포함함
		pq.offer(new int[] {distance[0][0][0], 0, 0, 0});
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int dist = info[0], r = info[1], c = info[2], k = info[3]; // 소비 경로, 행, 열, 벽부순횟수
			
			if (r == N - 1 && c == M - 1) {
				System.out.println(dist); // (1,1)부터 (N,M)까지 최단거리 출력
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				
				if (arr[nr][nc] == '1' && k < K && distance[nr][nc][k + 1] > distance[r][c][k] + 1) { // 다음 위치가 벽이면서 벽부순횟수가 K미만인경우
					distance[nr][nc][k + 1] = distance[r][c][k] + 1;
					pq.offer(new int[] {distance[nr][nc][k + 1], nr, nc, k + 1});
				} else if (arr[nr][nc] == '0' && distance[nr][nc][k] > distance[r][c][k] + 1) { // 빈칸인 경우
					distance[nr][nc][k] = distance[r][c][k] + 1;
					pq.offer(new int[] {distance[nr][nc][k], nr, nc, k});
				}
			}
		}
		System.out.println(-1); // 불가능한 경우 -1 출력
	}

}