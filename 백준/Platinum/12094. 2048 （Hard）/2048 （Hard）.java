import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * n*n 보드의 정보 입력받고 answer값을 보드에서 가장 큰 값으로 초기화.
 * dfs와 백트래킹 알고리즘을 통해 유망하지 않은 상태면 dfs를 중단하여 연산 시간 단축
 * 10번 이동한 상태면 answer와 보드의 최대값 비교하여 가장 큰 값으로 갱신
 * 알고리즘 수행후 answer값 출력
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int answer, n; // 결과값, 보드의 크기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int[][] board = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = findMax(board);
		dfs(0, board);
		System.out.println(answer);
	}

	/**
	 * 보드에서 최대값 찾는 메서드
	 * @param board 보드의 상태
	 * @return 보드에서 가장 큰 값
	 */
	private static int findMax(int[][] board) {
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result = Integer.max(result, board[i][j]);
			}
		}
		return result;
	}
	
	/**
	 * dfs 알고리즘
	 * @param now 현재 이동 횟수
	 * @param board 현재 보드의 상태
	 */
	private static void dfs(int now, int[][] board) {
		if (now >= 10) {
			answer = Integer.max(answer, findMax(board));
			return;
		}
		
		for (int k = 0; k < 4; k++) { // 상, 우, 하, 좌 순서
			int[][] copyBoard = move(board, k);
			if (isPromising(copyBoard, board)) {
				dfs(now + 1, copyBoard);
			}
		}
	}
	
	/**
	 * 백트래킹 알고리즘 사용을 위해 현재 상태가 유망한지 체크하는 여부
	 * 이동시키기전의 보드와 이동시킨후의 보드의 상태가 동일하다면 유망하지 않은 상태이므로 false 반환
	 * @param copyBoard 이동시킨후의 보드
	 * @param board 이동시키기전의 보두
	 * @return boolean값 true면 dfs계속진행, false면 유망하지않은 상태이므로 dfs중단
	 */
	private static boolean isPromising(int[][] copyBoard, int[][] board) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != copyBoard[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 보드를 이동시키는 메서드
	 * @param board 이동시키기전 보드의 상태
	 * @param direction 방향. 상=0, 우=1, 하=2, 좌=3
	 * @return 이동시킨후의 보드의 상태
	 */
	private static int[][] move(int[][] board, int direction) {
		int[][] copyBoard = new int[n][n];
		
		if (direction == 0) {
			// 위쪽으로 슬라이드한 경우
			for (int j = 0; j < n; j++) {
				int row = 0;
				int last = 0;
				
				for (int i = 0; i < n; i++) {
					if (board[i][j] > 0) {
						if (last == board[i][j]) {
							copyBoard[row - 1][j] = board[i][j] + last;
							last = 0;
						} else {
							copyBoard[row++][j] = last = board[i][j];
						}
					}
				}
			}
		}
		else if (direction == 1) {
			// 오른쪽으로 슬라이드한 경우
			for (int i = 0; i < n; i++) {
				int col = n - 1;
				int last = 0;
				
				for (int j = n - 1; j >= 0; j--) {
					if (board[i][j] > 0) {
						if (last == board[i][j]) {
							copyBoard[i][col + 1] = board[i][j] + last;
							last = 0;
						} else {
							copyBoard[i][col--] = last = board[i][j];
						}
					}
				}
			}
		}
		else if (direction == 2) {
			// 아래쪽으로 슬라이드한 경우
			for (int j = 0; j < n; j++) {
				int row = n - 1;
				int last = 0;
				
				for (int i = n - 1; i >= 0; i--) {
					if (board[i][j] > 0) {
						if (last == board[i][j]) {
							copyBoard[row + 1][j] = board[i][j] + last;
							last = 0;
						} else {
							copyBoard[row--][j] = last = board[i][j];
						}
					}
				}
			}
		}
		else if (direction == 3) {
			// 왼쪽으로 슬라이드한 경우
			for (int i = 0; i < n; i++) {
				int col = 0;
				int last = 0;
				
				for (int j = 0; j < n; j++) {
					if (board[i][j] > 0) {
						if (last == board[i][j]) {
							copyBoard[i][col - 1] = board[i][j] + last;
							last = 0;
						} else {
							copyBoard[i][col++] = last = board[i][j];
						}
					}
				}
			}
		}
		
		return copyBoard;
	}

}
