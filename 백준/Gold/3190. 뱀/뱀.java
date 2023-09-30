import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <pre>
 * 덱을 사용하여 문제를 해결
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		int N = Integer.parseInt(br.readLine()); // 보드의 크기 (2 ~ 100)
		int K = Integer.parseInt(br.readLine()); // 사과의 개수 (0 ~ 100)
		
		int[][] board = new int[N][N]; // 보드
		
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int R = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken()) - 1;
			
			board[R][C] = 2; // 사과 표시
		}
		
		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수 (1 ~ 100)
		int[] T = new int[L]; // 초 저장
		char[] C = new char[L]; // 방향 저장
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken()); // 게임 시작 시간으로부터 시간
			C[i] = st.nextToken().charAt(0); // L: 왼쪽90도, D: 오른쪽90도
		}
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		Deque<int[]> dq = new ArrayDeque<>(); // 덱
		
		board[0][0] = 1; // 뱀이 있는 곳 1로 표시
		dq.offerFirst(new int[] {0, 0}); // 덱의 앞에 삽입
		int d = 1; // 뱀이 바라보고 있는 방향 = 우
		int time = 0; // 게임 시간
		int idx = 0; // 인덱스
		while (true) {
			time++; // 시간 1초 증가
			int[] head = dq.peekFirst(); // 뱀의 머리
			int r = head[0], c = head[1]; // 뱀의 머리의 좌표
			int nr = r + dr[d], nc = c + dc[d]; // 뱀이 이동한 좌표
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) {
				break; // 보드를 벗어낫거나, 자기 자신과 부딫힌 경우
			}
			
			if (board[nr][nc] != 2) { // 이동한 곳에 사과가 없는 경우
				int[] tail = dq.pollLast(); // 뱀의 꼬리
				int i = tail[0], j = tail[1]; // 뱀의 꼬리의 좌표
				board[i][j] = 0; // 뱀의 꼬리가 있던 곳 비워줌
			}
			board[nr][nc] = 1; // 뱀의 머리를 다음칸으로 시켜줌
			dq.offerFirst(new int[] {nr, nc}); // 덱에 뱀의 머리 앞에 삽입
			if (idx < L && time == T[idx]) { // 현재 뱀의 방향이 바뀐 경우
				if (C[idx] == 'L') { // 뱀일 바라보는 방향 변환시켜줌
					d = (d + 3) % 4;
				} else if (C[idx] == 'D') {
					d = (d + 1) % 4;
				}
				idx++;
			}
		}
		
		System.out.println(time); // 결과 출력
	}

}