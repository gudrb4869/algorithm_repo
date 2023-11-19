import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * <pre>
 * 인접한 두 칸 완전탐색 및 구현
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	static int N, answer;
	static char[][] board;
	static int[] dr = {0, 1}; // 우, 하
	static int[] dc = {1, 0}; // 우, 하

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		board = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		for (int r1 = 0; r1 < N; r1++) {
			for (int c1 = 0; c1 < N; c1++) {
				for (int d = 0; d < 2; d++) {
					int r2 = r1 + dr[d];
					int c2 = c1 + dc[d];
					
					if (r2 < 0 || r2 >= N || c2 < 0 || c2 >= N) {
						continue;
					}
					
					char temp = board[r1][c1];
					board[r1][c1] = board[r2][c2];
					board[r2][c2] = temp;
					
					TreeSet<Integer> s = new TreeSet<>();
					
					for (int i = 0; i < N; i++) {
						// 같은 행
						int count = 1;
						for (int j = 1; j < N; j++) {
							if (board[i][j - 1] != board[i][j]) {
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
							if (board[j - 1][i] != board[j][i]) {
								s.add(count);
								count = 1;
							} else {
								count++;
							}
						}
						s.add(count);
					}
					
					answer = Math.max(answer, s.last());
					
					board[r2][c2] = board[r1][c1];
					board[r1][c1] = temp;
				}
			}
		}
		
		System.out.println(answer);
	}

}