import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 1번 cctv: 한쪽 방향만 감시가능
 * 2번 cctv: 서로 반대를 바라보는 두방향만 감시가능
 * 3번 cctv: 서로 직각을 이루는 두방향만 감시가능
 * 4번 cctv: 세방향 감시가능
 * 5번 cctv: 네방향 감시가능
 * 
 * 0: 빈칸
 * 1~5: cctv번호
 * 6: 벽
 * #: 감시가능한 영역
 * 
 * 사무실의 크기가 최대 8*8이고, CCTV의 최대개수가 8개이므로 완전탐색 시뮬레이션으로 구현하면 될거같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, answer = 99999; // 사무실의 세로크기, 가로크기, 사각지대의 최소 크기
	static int[][] arr; // 사무실의 각 칸의 정보를 담을 2차원 배열
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	static int[][][] delta = {
			{},
			{ {0}, {1}, {2}, {3} }, // 1번 cctv 경우의 수
			{ {0,2}, {1, 3} }, // 2번 cctv 경우의 수
			{ {0,1}, {1, 2}, {2, 3}, {3, 0} }, // 3번 cctv 경우의 수
			{ {0,1,2}, {1,2,3}, {2,3,0}, {3,0,1} }, // 4번 cctv 경우의 수
			{ {0,1,2,3} } // 5번 cctv 경우의 수
			}; // cctv의 초기방향
	static List<int[]> cctv = new ArrayList<>(); // cctv의 번호를 저장할 리스트
	static int[] numbers; // cctv 종류에 맞는 감시방향을 저장할 배열
	static int size; // cctv의 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 사무실의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 사무실의 가로 크기
		
		arr = new int[N][M]; // 사무실의 각 칸의 정보를 담을 2차원 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {
					cctv.add(new int[] {i, j, arr[i][j]}); // cctv의 위치와 cctv번호 저장
				}
			}
		}
		
		size = cctv.size(); // cctv의 개수
		numbers = new int[size];
		dfs(0); // 중복 순열
		
		System.out.println(answer);
	}

	private static void dfs(int cnt) {
		
		if (cnt == size) {
			// 시뮬레이션 진행
			int[][] result = new int[N][M]; // 사무실 복제
			for (int i = 0; i < N; i++) {
				result[i] = arr[i].clone();
			}
			
			for (int i = 0; i < size; i++) { // cctv의 개수만큼 반복
				int[] info = cctv.get(i);
				int num = info[2]; // i번째 cctv의 번호
				for (int d : delta[num][numbers[i]]) { // i번째 cctv가 감시가능한 방향만큼 반복수행
					int r = info[0], c = info[1]; // i번째 cctv의 위치
					while (true) {
						r += dr[d];
						c += dc[d];
						if (r < 0 || r >= N || c < 0 || c >= M || result[r][c] == 6) { // 사무실을 벗어낫거나, 벽을 마주친경우
							break;
						}
						
						if (result[r][c] >= 1 && result[r][c] <= 5) { // cctv를 통과가능하므로 무시하고 다음 반복 진행
							continue;
						}
						result[r][c] = -1; // 감시영역 #대신 -1로 세팅
					}
				}
			}
			
			int count = 0; // 사각지대의 크기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (result[i][j] == 0) { // 사각지대인 경우
						count++; // 1증가
					}
				}
			}
			
			answer = Math.min(answer, count); // 사각지대의 최소 크기로 갱신
			return;
		}
		
		// cctv번호에 해당하는 감시방향의 개수만큼 순열
		for (int i = 0; i < delta[cctv.get(cnt)[2]].length; i++) {
			numbers[cnt] = i;
			dfs(cnt + 1);
		}
	}

}