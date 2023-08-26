import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <pre>
 * dfs로 하면 돌을 최대10만번던지고 깊이가 최대 3만이므로 시간초과가 난다.
 * 따라서 각 열마다 돌을 던졌을 때 갈수 있는 경로를 스택에 저장하여 연산횟수를 줄였다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int R, C;
	static Stack<int[]>[] s;
	static char[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백단위 문자열 분리
		R = Integer.parseInt(st.nextToken()); // 보드의 가로길이
		C = Integer.parseInt(st.nextToken()); // 보드의 세로길이
		
		
		board = new char[R + 1][C]; // 보드의 상태를 저장할 2차원 배열
		
		s = new Stack[C]; // 각 열마다 장애물의 위치와 상태를 저장할 스택
		for (int i = 0; i < C; i++) {
			s[i] = new Stack<>(); // 스택 선언
			s[i].push(new int[] {0, i});
		}
		
		// 보드의 초기상태 저장
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		Arrays.fill(board[R], 'X'); // 보드의 가장 아랫줄 바로아래에 가상의 벽 생성
		
		int N = Integer.parseInt(br.readLine()); // 돌을 던진 횟수
		for (int i = 0; i < N; i++) {
			int c = Integer.parseInt(br.readLine()) - 1; // 돌을 던진 열의 위치
			moveStone(c); // 돌던지기 시작
		}
		
		// 게임이 모두 끝난 후의 상태
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	/**
	 * 
	 * @param col 돌을 떨어뜨리기 시작한 열
	 */
	private static void moveStone(int col) {
		
		while (!s[col].isEmpty()) { // 반복 조건
			int i = s[col].peek()[0], j = s[col].peek()[1]; // 현재 col열에서 돌던졌을때 최종 경로
			
			// 해당 칸에 이미 돌이 있는 경우
			if (board[i][j] != '.') {
				s[col].pop(); // 팝하고 skip
				continue;
			}
			
			// 바로 아래가 빈칸인 경우
			if (board[i + 1][j] == '.') {
				s[col].push(new int[] {i + 1, j});
			}
			// 바로아래가 돌이고 왼쪽으로 이동가능한 경우
			else if (board[i + 1][j] == 'O' && j - 1 >= 0 && board[i][j - 1] == '.' && board[i + 1][j - 1] == '.') {
				s[col].push(new int[] {i + 1, j - 1});
			}
			// 바로 아래가 돌이고 오른쪽으로 이동가능한 경우
			else if (board[i + 1][j] == 'O' && j + 1 < C && board[i][j + 1] == '.' && board[i + 1][j + 1] == '.') {
				s[col].push(new int[] {i + 1, j + 1});
			} 
			// 바로아래가 벽이어서 이동 멈추는 경우, 바로아래가 돌이지만 왼쪽오른쪽 둘다이동불가능한경우
			// board[i + 1][j] == 'X'
			// or
			// board[i + 1][j] == 'O' && board[i][j - 1] != '.' && board[i + 1][j - 1] != '.'
			// && board[i][j + 1] != '.' && board[i + 1][j + 1] != '.'
			else {
				int[] info = s[col].pop();
				board[info[0]][info[1]] = 'O';
				break; // 돌 놓고 중단
			}
		} 
	}
	
}
