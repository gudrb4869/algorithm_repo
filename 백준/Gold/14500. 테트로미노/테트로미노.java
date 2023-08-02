import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 테트로미노 도형의 좌표들을 저장하고
 * 종이의 범위에 있는지 체크하고
 * 합을 최대값으로 갱신해준다.
 * @author 박형규
 *
 */
public class Main {
	
	static int n, m; // 종이의 세로, 가로 크기
	static int[][] arr; // 종이
	static int[][] dr = { {0,0,0}, {1,2,3}, {0,1,1},
			{1,2,2}, {0,0,1}, {-1,-2,-2}, {0,0,-1}, 
			{1,2,2}, {0,0,-1}, {-1,-2,-2}, {0,0,1},
			{1,1,2}, {0,1,1}, {1,1,2}, {0,-1,-1},
			{0,1,0}, {-1,0,1}, {0,-1,0}, {1,0,-1} };
	static int[][] dc = { {1,2,3}, {0,0,0}, {1,0,1},
			{0,0,1}, {-1,-2,-2}, {0,0,-1}, {1,2,2},
			{0,0,-1}, {-1,-2,-2}, {0,0,1}, {1,2,2},
			{0,1,1}, {-1,-1,-2}, {0,-1,-1}, {-1,-1,-2},
			{-1,0,1}, {0,-1,0}, {1,0,-1}, {0,1,0} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 종이에 쓰여있는 수 저장
			}
		}
		
		int answer = 0; // 합의 최댓값 저장할 변수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < 19; k++) {
					if (isPossible(i, j, k)) { // 테트로미노가 종이 범위내에 있는지
						answer = Math.max(answer, sum(i, j, k)); // 최댓값으로 갱신
					}
				}
			}
		}
		System.out.println(answer); // 출력
	}

	static int sum(int i, int j, int k) {
		int sum = arr[i][j]; // 기준점
		for (int l = 0; l < 3; l++) { // 나머지 3개좌표 확인하여 합 저장
			int r = i + dr[k][l];
			int c = j + dc[k][l];
			sum += arr[r][c];
		}
		
		return sum;
	}

	static boolean isPossible(int i, int j, int k) {
		boolean flag = true;
		for (int l = 0; l < 3; l++) {
			int r = i + dr[k][l];
			int c = j + dc[k][l];
			flag = flag && (r >= 0 && r < n && c >= 0 && c < m); // 범위내에있는지 체크
		}
		return flag;
	}

}
