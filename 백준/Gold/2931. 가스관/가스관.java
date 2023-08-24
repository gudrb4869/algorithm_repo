import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 빈칸: .
 * 블록: | - + 1 2 3 4
 * 모스크바: M(한번씩만 주어짐)
 * 자그레브: Z(한번씩만 주어짐)
 * 
 * 가스의 흐름이 유일한 경우만 입력으로 주어짐, 모스크바와 자그레브가 하나의 블록과 인접해 있는 입력만 주어짐
 * 
 * 25 * 25 짜리 유럽이니까 그냥 완전탐색 브루트포스 알고리즘으로 풀어봤다.
 * </pre>
 * @author 박형규
 */
public class Main {

	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	static int row, column, R, C;
	static char block, arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림 

		StringTokenizer st = new StringTokenizer(br.readLine()); // 라인 공백 기준 분리
		
		R = Integer.parseInt(st.nextToken()); // 유럽의 크기(세로)
		C = Integer.parseInt(st.nextToken()); // 유럽의 크기(가로)
		
		arr = new char[R][C]; // 유럽
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		search();
		
		sb.append(row + 1).append(" ").append(column + 1).append(" ").append(block).append("\n");
		System.out.print(sb); // 출력
	}

	private static void search() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (arr[r][c] != '.') continue;
				
				boolean[] direction = {false, false, false, false}; // 상, 우, 하, 좌
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					
					if (d == 0) { // 상
						if (arr[nr][nc] == '|' || arr[nr][nc] == '+' || arr[nr][nc] == '1' || arr[nr][nc] == '4') {
							direction[0] = true;
						}
					} else if (d == 1) { // 우
						if (arr[nr][nc] == '-' || arr[nr][nc] == '+' || arr[nr][nc] == '3' || arr[nr][nc] == '4') {
							direction[1] = true;
						}
					} else if (d == 2) { // 하
						if (arr[nr][nc] == '|' || arr[nr][nc] == '+' || arr[nr][nc] == '2' || arr[nr][nc] == '3') {
							direction[2] = true;
						}
					} else if (d == 3) { // 좌
						if (arr[nr][nc] == '-' || arr[nr][nc] == '+' || arr[nr][nc] == '1' || arr[nr][nc] == '2') {
							direction[3] = true;
						}
					}
				}
				
				if (direction[0] && direction[1] && direction[2] && direction[3]) {
					row = r;
					column = c;
					block = '+';
					return;
				} else if (direction[0] && direction[2]) {
					row = r;
					column = c;
					block = '|';
					return;
				} else if (direction[1] && direction[3]) {
					row = r;
					column = c;
					block = '-';
					return;
				} else if (direction[1] && direction[2]) {
					row = r;
					column = c;
					block = '1';
					return;
				} else if (direction[0] && direction[1]) {
					row = r;
					column = c;
					block = '2';
					return;
				} else if (direction[0] && direction[3]) {
					row = r;
					column = c;
					block = '3';
					return;
				} else if (direction[2] && direction[3]) {
					row = r;
					column = c;
					block = '4';
					return;
				}
			}
		}
	}

}