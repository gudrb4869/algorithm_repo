import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 분할정복을 이용해 풀엇다
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, arr[][], white, blue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 종이의 한변의 길이
		
		arr = new int[N][N]; // N*N크기의 종이
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 각 칸에 들어갈 색깔의 정보
			}
		}
		
		divideAndConquor(0, 0, N); // 분할 정복 수행
		
		System.out.println(white); // 하얀색종이의 개수
		System.out.println(blue); // 파란색종이의 개수
	}

	/**
	 * 
	 * @param r 시작위치 행(왼쪽상단)
	 * @param c 시작위치 열(왼쪽상단)
	 * @param size 현재 살펴보고있는 종이의 가로세로길이
	 */
	private static void divideAndConquor(int r, int c, int size) {
		
		int sum = 0; // 흰종이인지 파란종이인지 섞여있는지 판별할 변수
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				sum += arr[i][j]; // 흰색은0, 파란색은1이므로 더해봄
			}
		}
		
		if (sum == 0) { // 다 더했는데 0이면 모두 하얀색
			white++;
		} else if (sum == size * size) { // 다 더했는데 종이의 넓이랑 같으면 모두 파란색
			blue++;
		} else { // 섞여있는 상태면 4등분해서 분할정복 수행
			int half = size / 2;
			divideAndConquor(r, c, half);
			divideAndConquor(r, c + half, half);
			divideAndConquor(r + half, c, half);
			divideAndConquor(r + half, c + half, half);
		}
	}

}
