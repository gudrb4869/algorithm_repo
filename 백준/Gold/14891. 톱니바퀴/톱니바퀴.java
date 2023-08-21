import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 하나의 자석에 8개의 날이 있다. 각날은 N극 또는 S극을 가짐
 * 현재 위치의 자석 하나를 돌리기 전에 인접한 날의 자성이 서로 반대일 경우 현재 위치의 자석을 돌리는 방향의 역방향으로 계속 회전을 시켜줘야함
 * </pre>
 * @author 박형규
 */
public class Main {

	static int[][] magnet; // 자석 4개의 자성 정보 담을 2차원 배열
	static boolean[] visited; // 자석 4개의 방문 관리
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		magnet = new int[4][8]; // 1번 자석부터 4번 자석까지 8개 날의 자성정보
		
		for (int i = 0; i < 4; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				if (temp[j] == '1') {
					magnet[i][j] = 1; // 자성 정보 저장
				}
			}
		}
		
		int K = Integer.parseInt(br.readLine()); // 자석을 회전 시키는 횟수
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1; // 자석 번호(0~3)
			int dir = Integer.parseInt(st.nextToken()); // 방향(1=시계,-1=반시계)
			
			visited = new boolean[4]; // 방문여부
			
			rotate(num, dir); // 현재 자석 회전
		}
		
		int answer = 0; // 획득 점수
		for (int i = 0; i < 4; i++) {
			answer += magnet[i][0] * (1 << i); // i번째자석의 0번날이 N극일땐 0점 S극일때 1,2,4,8점이어야하므로 비트연산자 사용
		}
		
		sb.append(answer).append("\n"); // 출력결과 저장
		
		System.out.print(sb); // 출력
	}

	private static void rotate(int num, int dir) {
		visited[num] = true; // 현재 자석 방문 체크
		
		int prev = num - 1; // 이전 자석
		if (prev >= 0 && !visited[prev] && (magnet[num][6] ^ 1) == magnet[prev][2]) { // 자석이 있고, 아직 방문안했고, 마주보는 날의 극성이 반대인 경우
			rotate(prev, dir * -1); // 현재 회전방향의 역방향으로 회전
		}
		
		int next = num + 1; // 다음 자석
		if (next < 4 && ! visited[next] && (magnet[num][2] ^ 1) == magnet[next][6]) { // 자석이 있고, 아직 방문안했고, 마주보는 날의 극성이 반대인 경우
			rotate(next, dir * -1); // 현재 회전방향의 역방향으로 회전
		}
		
		if (dir == 1) { // 시계 방향
			int temp = magnet[num][7];
			for (int i = 7; i > 0; i--) {
				magnet[num][i] = magnet[num][i - 1];
			}
			magnet[num][0] = temp;
		} else { // 반시계 방향
			int temp = magnet[num][0];
			for (int i = 0; i < 7; i++) {
				magnet[num][i] = magnet[num][i + 1];
			}
			magnet[num][7] = temp;
		}
	}

}