import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * dfs + 백트래킹 사용
 * </pre>
 * @author 박형규
 */
public class Main {

	static int[][] board = new int[9][9];
	static boolean[][] row = new boolean[9][10]; // 행 체크
	static boolean[][] col = new boolean[9][10]; // 열 체크
	static boolean[][] square = new boolean[9][10]; // 3*3 크기 보드 체크
	static StringBuilder sb = new StringBuilder(); // 출력스트림
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		for (int i = 0; i < 9; i++) { // 9개줄
			char[] t = br.readLine().toCharArray(); // 9개의 숫자로 보드 입력
			for (int j = 0; j < 9; j++) {
				board[i][j] = t[j] - '0'; // 각 인덱스에 맞게 원소 저장
				if (board[i][j] > 0) {
					row[i][board[i][j]] = true; // i행에 해당 숫자 체크
					col[j][board[i][j]] = true; // j열에 해당 숫자 체크
					square[(i / 3) * 3 + j / 3][board[i][j]] = true; // 3*3보드에 해당 숫자 체크
				}
			}
		}
		
		sudoku(0); // 스도쿠 제작
	}

	private static void sudoku(int cnt) {
		
		if (cnt == 81) { // 기저 조건
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]); // 출력결과 삽입
				}
				sb.append("\n"); // 개행
			}
			System.out.print(sb); // 출력
			System.exit(0); // 강제 종료
			return;
		}
		
		int i = cnt / 9, j = cnt % 9; // 현재 행, 열
		if (board[i][j] != 0) { // 이미 숫자가 있으면
			sudoku(cnt + 1); // 다음단계 진행
			return;
		}
		
		for (int k = 1; k <= 9; k++) { // 1부터 9까지 살펴봄
			if (row[i][k] || col[j][k] || square[(i / 3) * 3 + j / 3][k]) {
				continue; // 이미 같은행 or 같은열 or 3*3 보드에 숫자 k가 존재하는 경우 skip
			}
			
			row[i][k] = true; // i행에 k숫자 체크
			col[j][k] = true; // j열에 k숫자 체크
			square[(i / 3) * 3 + j / 3][k] = true; // 현재 3*3보드에 k숫자 체크
			board[i][j] = k; // i,j에 k로 세팅
			sudoku(cnt + 1); // 다음 단계 진행
			board[i][j] = 0; // i,j에 0으로 복구
			square[(i / 3) * 3 + j / 3][k] = false; // 체크해제
			col[j][k] = false; // 체크 해제
			row[i][k] = false; // 체크 해제
		}
	}

}