import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * <pre>
 * 조합, 구현
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	static int N, answer;
	static int numbers[] = new int[2];
	static char[][] board, copyBoard;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		board = new char[N][N];
		copyBoard = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		// n*nC2 모든 경우의 수를 다 조사해봄
		combination(0, 0);
		
		System.out.println(answer);
	}

	static void combination(int cnt, int start) {
		
		if (cnt == 2) {
			
			int r1 = numbers[0] / N, c1 = numbers[0] % N;
			int r2 = numbers[1] / N, c2 = numbers[1] % N;
			
			// 두 칸이 서로 인접하지 않은 경우
			if (Math.abs(r1 - r2) + Math.abs(c1 - c2) != 1) {
				return;
			}
			
			// 보드 복사본 생성
			for (int i = 0; i < N; i++) {
				copyBoard[i] = board[i].clone();
			}
			char temp = copyBoard[r1][c1];
			copyBoard[r1][c1] = copyBoard[r2][c2];
			copyBoard[r2][c2] = temp;
			
			TreeSet<Integer> s = new TreeSet<>();
			for (int i = 0; i < N; i++) {
				// 같은 행
				int count = 1;
				for (int j = 1; j < N; j++) {
					if (copyBoard[i][j - 1] != copyBoard[i][j]) {
						s.add(count);
						count = 1;
					} else {
						count++;
					}
				}
				s.add(count);
				
				// 같은 열
				count = 1;
				for (int j = 1; j < N; j++) {
					if (copyBoard[j - 1][i] != copyBoard[j][i]) {
						s.add(count);
						count = 1;
					} else {
						count++;
					}
				}
				s.add(count);
			}
			
			answer = Math.max(answer, s.last());
			return;
		}
		
		for (int i = start; i < N * N; i++) {
			numbers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
		
	}

}