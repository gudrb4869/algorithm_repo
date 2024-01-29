import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int sheep, wolf; // 아침까지 살아있는 양, 늑대의 수
	static int R, C, s, w;
	static char arr[][];
	static boolean visited[][];
	static int dr[] = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int dc[] = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visited[i][j]) continue;
				
				if (arr[i][j] == 'v' || arr[i][j] == 'o') { // 늑대이거나 양
					s = w = 0;
					dfs(i, j);
					
					if (s > w) { // 영역 안의 양의 수가 늑대의 수보다 많은 경우 
						sheep += s;
					} else { // 그렇지 않은 경우
						wolf += w;
					}
				}
			}
		}
		
		System.out.println(sheep + " " + wolf);
		
	}

	static void dfs(int r, int c) {
		
		visited[r][c] = true;
		
		if (arr[r][c] == 'v') w++;
		else if (arr[r][c] == 'o') s++;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || arr[nr][nc] == '#') {
				continue;
			}
			
			dfs(nr, nc);
		}
		
	}

}