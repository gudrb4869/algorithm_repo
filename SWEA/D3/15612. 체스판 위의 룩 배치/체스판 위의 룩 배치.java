import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * 체스판 각 행에 룩이 있는 열(column)을 저장할 1차원배열 rookR 생성후 -1로 초기화
 * 한줄씩 입력받으면서 O가 있는 열로 갱신해주는데 이때 0보다크거나같으면 같은행에 룩이 있는 경우이므로 "no" 리턴
 * 또 0부터 i - 1까지 행을 검사하면서 같은 열에 있는지도 검사후
 * 조건에 만족하면 rookR에 값 설정
 * 모든 조건 만족시 yes 반환
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
			int[] rookR = new int[8]; // 각 행에 룩이 있는 열 정보
			Arrays.fill(rookR, -1); // -1로 초기화
			int cnt = 0; // 룩의 개수
			for (int i = 0; i < 8; i++) {
				board[i] = sc.next().toCharArray(); // 체스판 정보 입력
				for (int j = 0; j < 8; j++) {
					if (board[i][j] == 'O') {
						cnt++;
					}
				}
			}
			String result = "no";
			if (cnt == 8 && checkBoard(board, rookR)) { // 룩이 8개고 서로 공격할 수 없는 경우
				result = "yes";
			}
			
			System.out.printf("#%d %s\n", test_case, result); // 결과 출력
		}
	}
	
	/**
	 * 
	 * @param board 체스판 정보
	 * @param rookR 각 행에 룩이 있는 열의 위치
	 * @return
	 */
	private static boolean checkBoard(char[][] board, int[] rookR) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == 'O') {
					if (rookR[i] >= 0) { // 같은행에 이미 룩이 있는 경우
						return false;
					}
					for (int k = 0; k < i; k++) {
						if (rookR[k] == j) // 이전 행들 중에 같은 열에 룩이 있는 경우
							return false;
					}
					rookR[i] = j;	
				}
			}
		}
		return true;
	}

}
