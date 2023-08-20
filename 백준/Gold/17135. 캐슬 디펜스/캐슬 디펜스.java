import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * 궁수 3명을 배치하려고하는데
 * 궁수 한명은 한번에 적 한명만 공격할 수 있고
 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적
 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격
 * 같은 적이 여러 궁수에게 공격당할 수 있다.
 * 1초가 지나면 적은 한칸 아래로 내려간다.
 * 적이 성이 있는 칸으로 이동시 게임에서 제외됨
 * 두위치의 거리는 |r1-r2| + |c1-c2| 이다.
 * 궁수의 공격범위 D가 주어지고 그 범위내에 적이 있으면 공격이 가능함
 * N*M의 격자가 있을때 궁수는 N+1행의 M칸중 3개의 칸에 배치를 해야함
 * 따라서 M개중 3개를 뽑는 MC3 조합을 이용해 경우의수에 게임결과중 최댓값을 리턴하도록 해보았다.
 * 가장 가까운 적을 찾을때는 bfs를 이용했다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, D, arr[][], archer[], answer;
	static int[] dr = {0, -1, 0}; // 좌,상,우
	static int[] dc = {-1, 0, 1}; // 좌,상,우
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자판 행의 수
		M = Integer.parseInt(st.nextToken()); // 격자판 열의 수
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한 D
		
		arr = new int[N][M]; // N * M 격자
		visited = new boolean[N][M]; // N * M 방문배열
		archer = new int[3]; // 궁수3명의 위치(0~M-1)
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 격자판의  상태 저장
			}
		}
		
		combination(0, 0); // mC3 조합의 경우의 수마다 제거할 수 있는 적의 최대수를 계산
		
		System.out.println(answer); // 결과 출력
	}

	private static void combination(int cnt, int start) {
		
		if (cnt == 3) { // 기저조건

			int[][] board = new int[N][M]; // 격자판 복사해서 저장할 배열
			int killCnt = 0; // 제거한 적의 수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					board[i][j] = arr[i][j]; // 2차원 배열 복제
				}
			}
			
			for (int t = 0; t < N; t++) {
				Set<Integer> s = new HashSet<>();
				
				for (int i = 0; i < 3; i++) {
					int location = bfs(N - 1, archer[i], board); // i+1 번째 궁수기준으로 가장 가까운 적의 위치
					if (location != -1) { // 가장 가까운 적이 공격범위 내에 있을때만
						s.add(location); // 적의 위치 추가
					}
				}
				killCnt += s.size(); // 제거한 적의 수 증가
				for (int location : s) { // 제거한 적의 위치값 0으로 세팅
					int r = location / M;
					int c = location % M;
					board[r][c] = 0;
				}
				downEnemy(board); // 한칸 아래로 내림
			}
			
			answer = Math.max(answer, killCnt); // 가장 많은 적을 제거한 경우로 갱신
			return;
		}
		
		for (int i = start; i < M; i++) {
			archer[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	private static void downEnemy(int[][] board) {
		for (int i = N - 1; i >= 1; i--) {
			for (int j = 0; j < M; j++) {
				board[i][j] = board[i - 1][j];
			}
		}
		
		Arrays.fill(board[0], 0);
	}

	private static int bfs(int startR, int startC, int[][] board) {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);// 방문관리 배열 초기화
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startR, startC, 1}); // 시작위치 바로위칸에서 시작
		visited[startR][startC] = true; // 시작위치 방문체크
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1], d = info[2];
			
			if (board[r][c] == 1) { // 해당위치가 적의 위치이면
				if (d > D) { // 공격범위보다 멀리있는 경우
					return -1;
				}
				return r * M + c; // 공격범위 내에 있는 경우
			}
			
			for (int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
					continue; // 격자판 벗어낫거나 이미 방문한 곳일경우 건너뜀
				}
				
				visited[nr][nc] = true; // 해당 위치 방문 체크
				q.offer(new int[] {nr, nc, d + 1}); // 큐에 삽입
			}
		}
		return -1;
	}

	public static int getDistance(int r1, int c1, int r2, int c2) { // 두 지점간의 거리
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

}