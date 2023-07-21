import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * 입력받고 난후에
 * 2중 for문으로 각 행과 각열의 룩의 개수 검사
 * 룩이 8개면서 서로 공격하지 않도록 놓으려면 각 행에 룩이 하나씩 있어야하고 각 열에도 룩이 하나씩 있어야함
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			char[][] board = new char[8][8]; // 체스판 정보
			for (int i = 0; i < 8; i++) {
				board[i] = sc.next().toCharArray(); // 체스판 정보 입력
			}
			
			String result = "yes";
			
			for (int i = 0; i < 8; i++) {
				int row = 0, col = 0;
				for (int j = 0; j < 8; j++) {
					if (board[i][j] == 'O')
						row++; // 행에 있는 룩개수
				}
				for (int j = 0; j < 8; j++) {
					if (board[j][i] == 'O')
						col++; // 열에 있는 룩개수
				}
				if (row != 1 || col != 1) { // 룩이 8개면서 서로 공격하지 않게 놓는 경우 각 행 또는 각 열에 룩이 1개 있어야한다.
					result = "no";
					break;
				}
			}
			
			System.out.printf("#%d %s\n", test_case, result); // 결과 출력
		}
	}
}
