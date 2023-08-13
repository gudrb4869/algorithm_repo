import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 1001*1001 색종이 순서 저장할 배열 만들고
 * 좌표랑 너비높이 주어질때마다 색종이 인덱스로 갱신
 * 보이는 넓이 출력
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 색종이 개수
		int[][] arr = new int[1001][1001]; // 1001 *1001 격자
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 왼쪽아래x좌표
			int y = Integer.parseInt(st.nextToken()); // 왼쪽아래y좌표
			int width = Integer.parseInt(st.nextToken()); // 너비
			int height = Integer.parseInt(st.nextToken()); // 높이
			for (int r = x; r < x + width; r++) {
				for (int c = y; c < y + height; c++) {
					arr[r][c] = i; // i번째 색종이로 현위치 지정
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			int area = 0;
			
			for (int r = 0; r < 1001; r++) {
				for (int c = 0; c < 1001; c++) {
					if (arr[r][c] == i) {
						area++; // i번째 색종이 넓이 계산
					}
				}
			}
			
			sb.append(area).append("\n");
		}
		System.out.print(sb); // 출력
	}

}