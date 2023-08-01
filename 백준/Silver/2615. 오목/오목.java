import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 1 2 0 0 2 2 2 1 0 0 0 0 0 0 0 0 0 0
0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0



//출력
1
3 2
 */
public class Main {

	private static int n = 19; // 가로줄세로줄 길이
	private static int[][] board = new int[n + 1][n + 1]; // 바둑판 정보
	private static boolean[][][] visited = new boolean[n + 1][n + 1][4]; // 위치의 방향별 방문여부 체크

	private static int[] dr = { 0, 1, 1, 1 }; // 가로방향, 오른쪽아래대각선방향, 세로방향, 원래오른쪽위대각선방향인데 최적하하려고 왼쪽아래대각선으로 했음
	private static int[] dc = { 1, 1, 0, -1 }; // 가로방향, 오른쪽아래대각선방향, 세로방향, 원래오른쪽위대각선방향인데 최적하하려고 왼쪽아래대각선으로 했음

	public static void main(String[] args) throws Exception {
		//////////////////////////////////////////////////////////////
		// 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
		//////////////////////////////////////////////////////////////
//		System.setIn(new FileInputStream("Test5.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 입력값 저장
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {

				if (board[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						if (!visited[i][j][k] && checkStone(i, j, k, board[i][j])) {

							System.out.println(board[i][j]);
							if (k == 3) { // 원래 오른쪽위대각선방향인데 방문체크 최적화하려고 왼쪽아래대각선방향으로 바꿔서풀었으므로 최적화
								System.out.println((i + 4) + " " + (j - 4)); // 오른쪽위바둑알로 되잇던거 다시 가장왼쪽아래 바둑알로 세팅
							} else {
								System.out.println(i + " " + j);
							}
							return;
						}
					}
				}
			}
		}
		System.out.println(0);
	}

	private static boolean checkStone(int i, int j, int k, int color) {
		visited[i][j][k] = true;
		int r = i, c = j;
		int cnt = 1; // 같은색으로 연결된 돌개수

		while ((r += dr[k]) >= 1 && r <= n && (c += dc[k]) >= 1 && c <= n) {

			if (board[r][c] == color) {
				visited[r][c][k] = true;
				cnt++;
			} else {
				break;
			}
		}

		if (cnt == 5) {
			return true;
		}
		return false;
	}

}
