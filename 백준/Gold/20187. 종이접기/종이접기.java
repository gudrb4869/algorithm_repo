import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * D - 윗면이 아랫면을 덮도록 접음
 * U - 아랫면이 윗면을 덮도록 접음
 * R - 왼쪽면이 오른쪽면을 덮도록 접읍
 * L - 오른쪽면이 왼쪽면을 덮도록 접음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[] dr = {-1,-1,0,1,1,1,0,-1}; // 상,우상,우,우하,하,좌하,좌,좌상
	static int[] dc = {0,1,1,1,0,-1,-1,-1}; // 상,우상,우,우하,하,좌하,좌,좌상
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력용
		StringBuilder sb = new StringBuilder(); // 출력용
		
		
		int k = Integer.parseInt(br.readLine()); // 손수건의 크기(1~8)
		StringTokenizer st = new StringTokenizer(br.readLine()); // 손수건을 접는 순서를 나타내느 정보
		
		// ---------
		// | 0 | 1 |
		// ---------
		// | 2 | 3 |
		// ---------
		int r = 0, c = 0; // 2*2종이에서 구멍을 뚫은 위치의 행,열
		for (int i = 0; i < k * 2; i++) { // 2k개의 종이를 접는 방법
			char cmd = st.nextToken().charAt(0);// 종이를 접는 방법 D,U,R,L 대문자 알파벳
			if (cmd == 'R') {
				c = 1; // 오른쪽으로 접는 경우 열값 1로 세팅
			} else if (cmd == 'D') {
				r = 1; // 아래로 접는 경우 행값 1로 세팅
			} else if (cmd == 'L') {
				c = 0; // 왼쪽으로 접는 경우 열값 0으로 세팅
			} else if (cmd == 'U') {
				r = 0; // 위로 접는 경우 행값 0으로 세팅 
			}
		}
		
		
		int[][] arr = new int[1 << k][1 << k]; // 2^k * 2^k 격자
		
		int h = Integer.parseInt(br.readLine()); // 구멍 뚫는 위치 (0~3)
		
		int[][] result = new int[2][2]; // 2*2 종이에서 구멍뚫린위치
		result[r][c] = h; // 다접고난후 해당위치에 h위치에 구멍뚫음
		for (int i = 0; i < 8; i++) { // 2*2 격자 기준으로 대각선은 2비트모두 반전, 상하방향은 2^1비트 자리만 반전, 좌우방향은 2^0비트 자리만 반전
			int nr = r + dr[i]; // 다음위치 행
			int nc = c + dc[i]; // 다음위치 열
			
			if (nr < 0 || nr >= 2 || nc < 0 || nc >= 2) { // 다음위치가 2*2 크기를 벗어날 경우 skip
				continue;
			}
			
			if (i % 2 == 1) { // 대각방향
				result[nr][nc] = result[r][c] ^ 3; // 모든비트반전
			} else if (i == 0 || i == 4) { // 상하방향
				result[nr][nc] = result[r][c] ^ 2; // 2^1비트자리만 반전
			} else { // 좌우방향
				result[nr][nc] = result[r][c] ^ 1; // 2^0비트자리만 반전
			}
		}
		
		for (int i = 0; i < 1 << k; i++) {
			for (int j = 0; j < 1 << k; j++) {
				arr[i][j] = result[i % 2][j % 2]; // 각 격자에 뚫린 구멍 위치 저장
			}
		}

		for (int i = 0; i < 1 << k; i++) {
			for (int j = 0; j < 1 << k; j++) {
				sb.append(arr[i][j]).append(" "); // 출력결과 스트링빌더에 삽입
			}
			sb.append("\n"); // 개행 삽입
		}
		System.out.print(sb); // 출력
	}

}
