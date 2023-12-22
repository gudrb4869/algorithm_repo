import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 두 백조가 서로 만날 수 있는지 여부 => Disjoint Set 사용
 * 물과 접촉해있는 얼음이 차례대로 녹는다 => BFS 사용
 * 
 * 얼음이 녹아 물이 된 곳을 Union Find를 통해 연결하고 두 백조가 서로 만날 수 있을 때 걸리는 날을 결과로 출력했다.
 * 2023-12-22(금)
 * </pre>
 */
public class Main {

	static int R, C, parents[], swan[][] = new int[2][2];
	static char lake[][];
	static int dr[] = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int dc[] = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력의 첫째 줄
		
		R = Integer.parseInt(st.nextToken()); // 호수의 행 갯수
		C = Integer.parseInt(st.nextToken()); // 호수의 열 갯수
		
		parents = new int[R * C]; // disjoint set에 사용할 집합의 루트번호 저장할 배열
		lake = new char[R][C]; // 호수의 정보
		
		int cnt = 0, idx = 0;
		for (int i = 0; i < R; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				lake[i][j] = temp[j];
				parents[cnt] = cnt++;
				
				// 두마리 백조의 위치정보 저장
				if (lake[i][j] == 'L') {
					swan[idx][0] = i;
					swan[idx][1] = j;
					idx++;
				}
			}
		}
		
		boolean[][] visited = new boolean[R][C]; // BFS 방문체크배열
		
		Queue<int[]> qWater = new ArrayDeque<>(); // 물
		Queue<int[]> qIce = new ArrayDeque<>(); // 빙판
		
		// 물 공간 disjoint set 생성(유니온 파인드)
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visited[i][j] || lake[i][j] == 'X') continue; // 이미 방문한 곳이거나, 빙판이 있는 곳인 경우 skip
				
				visited[i][j] = true;
				int root = convert(i, j); // disjoint Set의 루트노드번호
				qWater.offer(new int[] {i, j});
				
				while (!qWater.isEmpty()) {
					int[] info = qWater.poll();
					int r = info[0], c = info[1]; // 현정점의 좌표
					
					for (int d = 0; d < 4; d++) { // 현정점 기준 상하좌우 4방향 탐색
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						// 호수의 범위를 벗어났거나, 이미 방문한 정점인 경우 skip
						if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) {
							continue;
						}
						
						visited[nr][nc] = true;
						if (lake[nr][nc] == 'X') {
							qIce.offer(new int[] {nr, nc, 1});
						} else {
							qWater.offer(new int[] {nr, nc});
							union(root, convert(nr, nc));
						}
					}
				}
			}
		}
		
		if (isUnion(convert(swan[0][0], swan[0][1]), convert(swan[1][0], swan[1][1]))) {
			System.out.println(0);
			return;
		}
		
		while (!qIce.isEmpty()) {
			int[] info = qIce.poll();
			int r = info[0], c = info[1], time = info[2];
			lake[r][c] = '.'; // 빙판이 있던 곳을 물로 변환
			
			for (int d = 0; d < 4; d++) { // 4방탐색
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 호수 밖을 벗어났거나, 빙판인 경우 skip
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || lake[nr][nc] == 'X') {
					continue;
				}
				
				union(convert(nr, nc), convert(r, c)); // Union Find
			}
			
			// 두 백조가 서로 만날 수 있는 경우
			if (isUnion(convert(swan[0][0], swan[0][1]), convert(swan[1][0], swan[1][1]))) {
				System.out.println(time); // 걸리는 날 출력
				break;
			}
			
			for (int d = 0; d < 4; d++) { // 4방 탐색(현정점 기준으로 인접 빙판을 큐에 삽입)
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 호수 밖으로 벗어났거나, 빙판이 아니거나, 이미 방문한 지점인 경우 skip
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || lake[nr][nc] != 'X' || visited[nr][nc]) {
					continue;
				}
				
				visited[nr][nc] = true; // 방문 체크
				qIce.offer(new int[] {nr, nc, time + 1}); // 빙판정보 저장하는 큐에 정보 삽입
			}
		}
		
	}
	
	static int find(int x) {
		if (parents[x] == x) return parents[x];
		return parents[x] = find(parents[x]);
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return;
		
		parents[rootB] = rootA;
	}
	
	static boolean isUnion(int a, int b) {
		return find(a) == find(b);
	}
	
	// 2차원 좌표 정보 -> 1차원 값으로 변환
	static int convert(int r, int c) {
		return r * C + c;
	}

}