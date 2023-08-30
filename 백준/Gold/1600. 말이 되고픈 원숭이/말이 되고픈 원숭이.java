import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BFS + DP 문제
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[][] horse = {
			{-2, 1}, {-1, 2}, {1, 2}, {2, 1},
			{2, -1}, {1, -2}, {-1, -2}, {-2, -1}
			}; // 말의 이동방식 8방향
	
	static int[][] monkey = {
			{-1, 0}, {0, 1}, {1, 0}, {0, -1}
			}; // 원숭이의 이동방식 4방향 (상, 우, 하, 좌)
	
	static final int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int K = Integer.parseInt(br.readLine()); // 원숭이가 말처럼 이동할 수 있는 횟수
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백기준으로 분리
		int W = Integer.parseInt(st.nextToken()); // 격자판의 가로길이
		int H = Integer.parseInt(st.nextToken()); // 격자판의 세로길이
		
		int[][] board = new int[H][W]; // H*W 격자판의 정보를 저장할 2차원 배열 생성
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()); // 0=아무것도없는평지, 1=장애물
			}
		}

		// (0, 0) -> (H-1, W-1)까지 가야함
		int[][][] distance = new int[H][W][K + 1]; // 좌표, 말처럼 이동한 횟수에 따라 동작수의 최솟값을 저장할 3차원 배열
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				for (int k = 0; k <= K; k++) {
					distance[i][j][k] = INF; // 무한대로 세팅
				}
			}
		}
		
		// BFS에 사용할 큐 선언
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0, 0, 0}); // 이동횟수, 능력사용횟수, 현재좌표 행, 현재좌표 열
		distance[0][0][0] = 0; // 시작점에서 원숭이의 동작수
		
		int dr = 0, dc = 0, nr = 0, nc = 0;
		while (!q.isEmpty()) { // 너비우선탐색 반복 조건
			int[] info = q.poll();
			int cnt = info[0], k = info[1], r = info[2], c = info[3];
			
			if (cnt > distance[r][c][k]) { // 이미 테이블에 저장된 거리가 더 작은 경우 skip
				continue;
			}
			
			if (k < K) { // 능력다 안쓴경우에만 말처럼 이동해봄
				for (int[] d : horse) { // 말의 동작 8방향
					dr = d[0];
					dc = d[1];
					
					nr = r + dr;
					nc = c + dc;
					
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || board[nr][nc] == 1) continue; // 격자판을 벗어난 경우
					if (distance[nr][nc][k + 1] > distance[r][c][k] + 1) { // k-1번 능력사용한 상태의 이전위치 pr, pc에서 능력을 1회사용하여 r,c로 1칸이동해온 동작수가 더 작은경우
						distance[nr][nc][k + 1] = distance[r][c][k] + 1; // 최솟값으로 갱신
						q.offer(new int[] {distance[nr][nc][k + 1], k + 1, nr, nc});
					}
				}
			}
			for (int[] d : monkey) { // 원숭이의 동작 상,우,하,좌 4방향
				dr = d[0];
				dc = d[1];
				
				nr = r + dr;
				nc = c + dc;
				
				if (nr < 0 || nr >= H || nc < 0 || nc >= W || board[nr][nc] == 1) continue; // 격자판을 벗어난 경우
				
				if (distance[nr][nc][k] > distance[r][c][k] + 1) { // k번 능력사용한상태의 이전위치 pr, pc에서 r, c로 1칸이동해온 동작수가 더 작은경우
					distance[nr][nc][k] = distance[r][c][k] + 1; // 최솟값으로 갱신
					q.offer(new int[] {distance[nr][nc][k], k, nr, nc});
				}
			}
		}
		
		int answer = INF; // 원숭이의 동작수를 저장할 변수
		for (int k = 0; k <= K; k++) { // H-1, W-1 도착점에서 능력을 0회부터 K회 사용한 것 전부다 조회
			if (answer > distance[H - 1][W - 1][k]) { // 현재저장된 값보다 더 작은경우
				answer = distance[H - 1][W - 1][k]; // 최솟값으로 갱신
			}
		}
		
		System.out.println(answer == INF ? -1 : answer); // 무한대인경우=도착점까지 갈수없는경우이므로 -1 출력, 도착점 갈수있는경우 동작수의 최솟값을 출력
	}

}