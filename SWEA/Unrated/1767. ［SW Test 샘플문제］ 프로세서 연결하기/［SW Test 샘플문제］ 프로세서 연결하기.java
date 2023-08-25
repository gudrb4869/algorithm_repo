import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 가로 N개, 세로 N개
 * 
 * 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합
 * 
 * 부분집합 이용하여 문제 해결
 * 중간중간에 가지치기도 해줌
 * </pre>
 * @author 박형규
 *
 *
 */
public class Solution {

	static int N, K, answer, size, INF = Integer.MAX_VALUE;
	static int[][] cell;
	static int[] dr = {-1, 0, 1, 0, 0}; // 상, 우, 하, 좌, 제자리
	static int[] dc = {0, 1, 0, -1, 0}; // 상, 우, 하, 좌, 제자리
	static List<int[]> list; // 코어가 있는 위치를 저장할 리스트
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st; // 공백 단위 입력값 분리
		StringBuilder sb = new StringBuilder(); // 출력스트림
		int T = Integer.parseInt(br.readLine()); // 총 테스트 케이스의 개수
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 멕시노스의 가로세로길이
			cell = new int[N][N]; // 멕시노스 각 셀의 상태를 저장할 2차원 배열
			
			answer = INF; // 전선 길이 합의 최솟값
			size = 0; // 연결한 코어의 갯수
			K = 0; // 코어의 개수
			list = new ArrayList<>(); // Core가 있는 위치를 저장할 리스트
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cell[i][j] = Integer.parseInt(st.nextToken()); // 각 셀의 정보 저장
					if (cell[i][j] == 1) { // Core가 있는 위치
						list.add(new int[] {i, j});
						K++; // core의 개수 1증가
					}
				}
			}
			
			subset(0, 0, 0, 0); // 부분 집합
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n"); // 테스트 케이스 결과
		}
		
		System.out.print(sb); // 출력
	}
	
	/**
	 * 
	 * @param cnt 현재 검토할 코어
	 * @param selected 현재까지 연결한 코어의 갯수
	 * @param unselected 현재까지 연결하지 않은 코어의 갯수
	 * @param sum 현재까지 전선의 길이의 합
	 */
	private static void subset(int cnt, int selected, int unselected, int sum) {
		
		if (K - unselected < size) { // 가지치기
			return;
		}
		
		if (cnt == K) { // 기저 조건
			if (selected == size) { // 연결된 코어의 갯수가 기존 저장된 코어의 갯수와 동일한 경우
				answer = Math.min(answer, sum); // 전선의 길이가 더 작은 것으로 갱신
			} else if (selected > size) { // 연결된 코어의 갯수가 기존 저장된 코어의 갯수보다 더 크면
				size = selected; // 코어의 갯수 갱신
				answer = sum; // 전선의 길이의 합 갱신
			}
			return;
		}
		
		int[] info = list.get(cnt); // 현재 검토할 코어의 좌표 정보
		int r = info[0], c = info[1]; // 코어의 행, 코어의 열
		
		if (r == 0 || r == N - 1 || c == 0 || c == N - 1) { // 가장자리에 붙어 잇는 코어일 경우
			subset(cnt + 1, selected + 1, unselected, sum); // 다음 코어로 건너뛰어 진행
			return;
		}
		
		for (int d = 0; d < 5; d++) { // 제자리, 위, 오른쪽, 아래, 왼쪽
			int length = isPossible(r, c, d); // 전선을 가장자리에 연결했을 때 길이
			if (length == -1) { // 연결할 수 없는 경우
				subset(cnt + 1, selected, unselected + 1, sum); // 다음 코어로 건너뛰어 진행
			} else { // 연결 가능한 경우
				for (int k = 1; k <= length; k++) { // 전선이 위치할 영역을 1로  세팅
					cell[r + dr[d] * k][c + dc[d] * k] = 1;
				}
				subset(cnt + 1, selected + 1, unselected, sum + length); // 다음 코어로 진행. 이때 연결한코어갯수1증가하고 전선의길이만큼누적
				for (int k = 1; k <= length; k++) { // 전선이 위치했던 영역을 0으로 세팅
					cell[r + dr[d] * k][c + dc[d] * k] = 0;
				}
			}
		}
		
	}

	/**
	 * 
	 * @param r 코어가 위치해있는 행
	 * @param c 코어가 위치해있는 열
	 * @param d 전선을 진행시킬 방향
	 * @return 전선의 길이( 연결불가능한 경우 -1 리턴)
	 */
	private static int isPossible(int r, int c, int d) {
		
		int count = 0; // 전선의 길이
		while (true) {
			r += dr[d]; // 다음 위치 행
			c += dc[d]; // 다음 위치 열
			
			if (r < 0 || r >= N || c < 0 || c >= N) { // 멕시노스 영역을 벗어난 경우 (전선 연결가능)
				return count; // 전선의 길이 리턴
			}
			
			if (cell[r][c] == 1) { // 코어가 있는 위치이거나 이미 전선이 깔려있는 위치일 경우
				break; // 반복 중단
			}
			
			count++; // 전선의 길이 1증가
		}
		
		return -1; // 연결 불가능한 경우 -1 리턴
	}
	
}