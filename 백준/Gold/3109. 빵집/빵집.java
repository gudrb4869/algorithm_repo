import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * dfs로 풀엇다
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int R, C; // 빵집의 행, 열
	static char[][] arr; // 빵집의 모습
	static boolean[][] visited; // 각 위치 방문 여부
	static int[] dr = {-1,0,1}; // 오른쪽위, 오른쪽, 오른쪽 아래
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 격자 행
		C = Integer.parseInt(st.nextToken()); // 격자 열
		
		arr = new char[R][C]; // R*C 크기로 초기화
		visited = new boolean[R][C]; // 방문 여부 저장할 2차원 배열
		
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray(); // .=빈칸 x=건물
		}
		
		int answer = 0; // 놓을 수 있는 파이프라인의 최대 개수
		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) {
				answer++;
			}
//			answer += dfs(i, 0); // 첫번째열 각 행마다 dfs탐색
		}
		System.out.println(answer); // 출력
	}

	private static boolean dfs(int r, int c) {
		
		if (c == C - 1) { // 가스관에 도달한 경우
			return true; // 파이프라인을 놓을 수 있으므로 1리턴
		}
		
		for (int k = 0; k < 3; k++) { // 오른쪽위, 오른쪽, 오른쪽 아래 탐색
			int nr = r + dr[k]; // 다음에 살펴볼 행값
			
			if (nr < 0 || nr >= R) { // 빵집을 벗어난 경우 skip
				continue;
			}
			
			if (arr[nr][c + 1] != 'x' && !visited[nr][c + 1]) { // 다음위치가 벽이 아니면서 아직 방문하지 않은 곳이면
				visited[nr][c + 1] = true; // 방문체크해주고
				boolean flag = dfs(nr, c + 1); // 다음위치 탐색
				if (flag) {
					return flag;
				}
			}
		}
		return false; // 3방향 모두 갈수 없는 곳이면 파이프라인을 놓을 수 없으므로 0 리턴
	}

}
/*
5 9
.xxxxxxx.
..xxxx.x.
...xxx.x.
.xx.x.xx.
...x.....

5 5
...x.
.xxx.
.xxx.
.xxx.
.....

5 5
.xxx.
..xx.
.x.x.
.xx..
.xxx.

5 5
.xxx.
..xx.
.x.x.
.xx..
...x.
 */