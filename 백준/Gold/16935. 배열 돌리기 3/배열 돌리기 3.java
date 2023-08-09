import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 1,2,3,4,5,6연산을 구현하기 위해 result 배열을 만들었고, 각각의 연산결과에 맞게 A배열값들을 result배열에 넣어줬음
 * 그다음 A배열을 result배열로 바꿔치기하여 문제를 풀었음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	private static int N, M, R, arr[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		R = Integer.parseInt(st.nextToken()); // 연산의 수
		
		arr = new int[N][M]; // N*M 크기의 배열 A 초기화
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) { // 연산의 수만큼 반복
			
			int operator = Integer.parseInt(st.nextToken()); // 수행해야 하는 연산
			switch (operator) {
			case 1:
				arr = invertUpDown(); // 상하반전
				break;
			case 2:
				arr = invertLeftRight(); // 좌우반전
				break;
			case 3:
				arr = rotateRight(); // 오른쪽으로 90도회전
				break;
			case 4:
				arr = rotateLeft(); // 왼쪽으로 90도회전
				break;
			case 5:
				arr = rotateRightFourGroup(); // 4개의 그룹으로 나누고 시계방향으로 회전
				break;
			case 6:
				arr = rotateLeftFourGroup(); // 4개의 그룹으로 나누고 반시계방향으로 회전
				break;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" "); // 출력결과 스트링빌더에 저장
			}
			sb.append("\n");
		}
		System.out.print(sb); // 출력
		
	}
	
	/**
	 * -----     -----
	 * |1|2|     |2|3|
	 * -----  -> -----
	 * |4|3|     |1|4|
	 * -----
	 * @return
	 */
	private static int[][] rotateLeftFourGroup() {
		int[][] result = new int[N][M];
		
		for (int i = 0; i < N >> 1; i++) { // (2->1) 2그룹값들을 1그룹이 있던자리로 이동
			for (int j = 0; j < M >> 1; j++) {
				result[i][j] = arr[i][j + (M >> 1)];
				
			}
		}
		
		for (int i = 0; i < N >> 1; i++) { // (3->2) 3그룹값들을 2그룹이 있던자리로 이동
			for (int j = M >> 1; j < M; j++) {
				result[i][j] = arr[i + (N >> 1)][j];
				
			}
		}
		
		for (int i = N >> 1; i < N; i++) { // (4->3) 4그룹값들을 3그룹이 있던자리로 이동
			for (int j = M >> 1; j < M; j++) {
				result[i][j] = arr[i][j - (M >> 1)];
				
			}
		}
		
		for (int i = N >> 1; i < N; i++) { // (1->4) 1그룹값들을 4그룹이 있던자리로 이동
			for (int j = 0; j < M >> 1; j++) {
				result[i][j] = arr[i - (N >> 1)][j];
			}
		}
		
		return result;
	}

	/**
	 * -----     -----
	 * |1|2|     |4|1|
	 * -----  -> -----
	 * |4|3|     |3|2|
	 * -----
	 * @return
	 */
	private static int[][] rotateRightFourGroup() {
		int[][] result = new int[N][M];
		
		for (int i = 0; i < N >> 1; i++) { // (4->1) 4그룹값들을 1그룹이 있던자리로 이동
			for (int j = 0; j < M >> 1; j++) {
				result[i][j] = arr[i + (N >> 1)][j];
			}
		}
		
		for (int i = 0; i < N >> 1; i++) { // (1->2) 1그룹값들을 2그룹이 있던자리로 이동
			for (int j = M >> 1; j < M; j++) {
				result[i][j] = arr[i][j - (M >> 1)];
			}
		}
		
		for (int i = N >> 1; i < N; i++) { // (2->3) 2그룹값들을 3그룹이 있던자리로 이동
			for (int j = M >> 1; j < M; j++) {
				result[i][j] = arr[i - (N >> 1)][j];
			}
		}
		
		for (int i = N >> 1; i < N; i++) { // (3->4) 3그룹값들을 4그룹이 있던자리로 이동
			for (int j = 0; j < M >> 1; j++) {
				result[i][j] = arr[i][j + (M >> 1)];
			}
		}
		
		return result;
	}

	/**
	 * 왼쪽으로 90도 회전
	 * @return 왼쪽으로 90도 회전된 배열
	 */
	private static int[][] rotateLeft() {
		int[][] result = new int[M][N]; // 90도 회전해야하므로  m * n으로 만듬!
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] = arr[j][M - 1 - i];
			}
		}
		int temp = N; // 회전시켰으므로 행, 열값을 swap 시켜줌
		N = M;
		M = temp;
		
		return result;
	}

	/**
	 * 오른쪽으로 90도 회전
	 * @return 오른쪽으로 90도 회전된 배열
	 */
	private static int[][] rotateRight() {
		int[][] result = new int[M][N]; // 90도 회전해야하므로  m * n으로 만듬!
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] = arr[N - 1 - j][i];
			}
		}
		int temp = N; // 회전시켰으므로 행, 열값을 swap 시켜줌
		N = M;
		M = temp;
		
		return result;
	}

	/**
	 * 좌우반전
	 * @return 좌우반전된 배열
	 */
	private static int[][] invertLeftRight() {
		int[][] result = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = arr[i][M - 1 - j];
			}
		}
		
		return result;
	}

	/**
	 * 상하반전
	 * @return 상하반전된 배열
	 */
	private static int[][] invertUpDown() {
		int[][] result = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = arr[N - 1 - i][j];
			}
		}
		
		return result;
	}

}
