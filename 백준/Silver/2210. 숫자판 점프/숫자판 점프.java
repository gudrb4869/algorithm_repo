import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static Set<String> s = new HashSet<>(); // 만들 수 있는 수들을 저장할 set
	static String[][] arr = new String[5][5]; // 5*5 숫자판
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 5 * 5 숫자판 입력
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 5; j++) {
				arr[i][j] = st.nextToken();
			}
		}
		
		// 각 칸마다 dfs
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				dfs(i, j, 0, arr[i][j]);
			}
		}
		
		// 만들 수 있는 수들의 개수 출력
		System.out.println(s.size());
	}

	static void dfs(int r, int c, int cnt, String num) {
		
		// 다섯 번 이동 다한 경우(기저 조건)
		if (cnt == 5) {
			
			if (!s.contains(num)) {
				s.add(num);
			}
			return;
		}
		
		// 인접해 있는 네 방향 탐색
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 숫자판 범위 바깥인 경우 skip
			if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
				continue;
			}
			
			dfs(nr, nc, cnt + 1, num + arr[nr][nc]);
		}
	}

}