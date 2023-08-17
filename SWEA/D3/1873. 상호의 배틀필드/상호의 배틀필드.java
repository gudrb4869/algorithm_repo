import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * .: 평지(전차진입가능)
 * *: 벽돌로 만들어진 벽
 * #: 강철로 만들어진 벽
 * -: 물(전차진입불가)
 * ^: 위를 바라보는 전차 
 * v: 아래를 바라보는 전차
 * <: 왼쪽을 바라보는 전차
 * >: 오른쪽을 바라보는 전차
 * 
 * U: 전차가바라보는 방향을 ^로바꾸고, 한칸위의칸이 평지면 위로 한칸이동
 * D: 전차가바라보는 방향을 v로바꾸고, 한칸아래의칸이 평지면 아래로 한칸이동
 * L: 전차가바라보는 방향을 <로바꾸고, 한칸왼쪽의칸이 평지면 왼쪽으로 한칸이동
 * R: 전차가바라보는 방향을 >로바꾸고, 한칸오른쪽의칸이 평지면 오른쪽으로 한칸이동
 * S: 전차가 현재 바라보고 있는 방향으로 포탄을 발사
 * 
 * 전차가 이동하려고 하는곳이 맵밖이면 전차는 이동하지 않음
 * 포탄발사시 평지,물위를 이동하며 벽과 충돌하거나 맵밖에 나갈때까지 직진함.
 * 포탄이 벽돌로 만들어진 벽만나면 벽이 평지로 바뀜
 * 포탄이 강철로 만들어진 벽만나면 아무일도 일어나지 않음
 * 포탄이 맵밖으로 나가면 아무일도 일어나지 않음
 * 
 * </pre>
 * @author 박형규
 * 메모리 20,888 KB
 * 시간 125 ms
 *
 */
public class Solution {

	static int[] dr = {-1, 0, 1, 0}; // 상,우,하,좌
	static int[] dc = {0, 1, 0, -1}; // 상,우,하,좌
	static char[] tank = {'^', '>', 'v', '<'}; // 상,우,하,좌
	static Map<Character, Integer> hm = new HashMap<>(); // 전차의 방향, 커맨드 명령어와 인덱스를 매핑할 해시맵
	static char[][] arr; // 게임 맵
	static int r, c, d; // 전차의 위치와 바라보고 있는 방향
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		hm.put('^', 0);
		hm.put('>', 1);
		hm.put('v', 2);
		hm.put('<', 3);
		hm.put('U', 0);
		hm.put('R', 1);
		hm.put('D', 2);
		hm.put('L', 3);
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken()); // 게임 맵의 높이
			int W = Integer.parseInt(st.nextToken()); // 게임 맵의 너비
			
			arr = new char[H][W]; // H * W 크기의 게임 맵
			for (int i = 0; i < H; i++) {
				char[] input = br.readLine().toCharArray(); // 게임 맵의 정보
				for (int j = 0; j < W; j++) {
					arr[i][j] = input[j];
					if (hm.get(arr[i][j]) != null) {
						r = i; // 전차의 위치 행
						c = j; // 전차의 위치 열
						d = hm.get(arr[i][j]); // 전차가 바라보고 있는 방향(0=위,1=오른쪽,2=아래,3=왼쪽)
						arr[i][j] = '.'; // 전차가 있는 위치를 평지로 세팅
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine()); // 사용자가 넣을 입력의 개수
			char[] cmd = br.readLine().toCharArray();
			for (int i = 0; i < N; i++) {
				if (cmd[i] == 'S') { // Shoot
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					while (nr >= 0 && nr < H && nc >= 0 && nc < W) { // 맵 범위에 있는 경우에만 반복
						if (arr[nr][nc] == '#') { // 강철벽 만나면
							break; // 아무일도 일어나지 않고 중단
						} else if (arr[nr][nc] == '*') { // 벽돌벽 만나면
							arr[nr][nc] = '.'; // 평지로 바꿔주고
							break; // 중단
						}
						nr += dr[d]; // 전차가 바라보고 있는 방향으로 포탄을 직진시키기 위해 행값 조정
						nc += dc[d]; // 전차가 바라보고 있는 방향으로 포탄을 직진시키기 위해 열값 조정
					}
				} else if (hm.get(cmd[i]) != null) { // Up, Right, Down, Left
					d = hm.get(cmd[i]); // 전차가 바라보는 방향으로 세팅
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 이동하려는 곳이 게임 맵 범위에 있고 가려는 곳이 평지일 때만 위치 조정
					if (nr >= 0 && nr < H && nc >= 0 && nc < W && arr[nr][nc] == '.') {
						r = nr;
						c = nc;
					}
				}
			}
			
			arr[r][c] = tank[d]; // 탱크가 있는 위치에 바라보고 있는 방향으로 탱크 세팅
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(arr[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

}