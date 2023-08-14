import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N*N 체스판에 흑색퀸 K개가 있다. (N:3이상10억이하, K:1이상10만이하)
 * 백색 킹의 위치 R,C
 * K개의 줄에걸쳐 흑색퀸의 위치 Ri,Ci
 * 체크: 킹이 상대 기물에 의해 공격받고 있으면서, 킹을 한 번 움직여 상대의 공격으로부터 벗어날 수 있는 경우
 * 체크메이트: 킹이 상대 기물에 의해 공격받고 있으면서, 킹을 어떻게 한 번 움직이더라도 상대의 공격으로부터 벗어날 수 없는 경우
 * 스테일메이트: 킹이 공격받고 있지는 않지만, 킹을 어떻게 한 번 움직이더라도 상대 기물에 의해 공격을 받게 되는 경우
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 체스판의 크기
		int K = Integer.parseInt(st.nextToken()); // 흑색 퀸의 수
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); // 백색 킹의 위치 행
		int C = Integer.parseInt(st.nextToken()); // 백색 킹의 위치 열
		
		boolean[][] whiteKing = new boolean[3][3]; // 백색 킹의 8방향과 자기자신위치 공격받는지여부 저장할 배열
		
		for (int i = R - 1; i <= R + 1; i++) {
			for (int j = C - 1; j <= C + 1; j++) {
				if (i < 1 || i > N || j < 1 || j > N) { // 체스판의 행열은 1부터 N까지
					whiteKing[i - R + 1][j - C + 1] = true; // 8방향중 체스판범위 벗어난 경우 공격처리
				}
			}
		}
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); // i번째 흑색 퀸의 위치 행
			int c = Integer.parseInt(st.nextToken()); // i번째 흑색 퀸의 위치 열
			
			for (int i = R - 1; i <= R + 1; i++) {
				for (int j = C - 1; j <= C + 1; j++) {
					if (i < 1 || i > N || j < 1 || j > N) {
						continue; // 체스판 범위 벗어난 경우 skip
					}
					
					if (r == i || c == j || Math.abs(r - i) == Math.abs(c - j)) {
						whiteKing[i - R + 1][j - C + 1] = true;
					}
				}
			}
		}
		
		boolean isCheck = whiteKing[1][1];
		boolean isStalemate = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				isStalemate &= whiteKing[i][j];
			}
		}
		
		if (isCheck) {
			if (isStalemate) {
				sb.append("CHECKMATE\n");
			} else {
				sb.append("CHECK\n");
			}
		} else {
			if (isStalemate) {
				sb.append("STALEMATE\n");
			} else {
				sb.append("NONE\n");
			}
		}
		
		System.out.print(sb);
	}

}