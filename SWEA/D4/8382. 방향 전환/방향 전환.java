import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 두 x좌표 떨어진 거리 dx = |x1 - x2|
 * 두 y좌표 떨어진 거리 dy = |y1 - y2|
 * 
 * 이동횟수
 * r\c 0  1 2  3 4  5  6
 *  0  0  1 4  5 8  9 ...
 *  1  1  2 3  6 7 10 ...
 *  2  4  3 4  5 8  9 ...
 *  3  5  6 5  6 7 10 ...
 *  4  8  7 8  7 8  9 ...
 *  5  9 10 9 10 9 10 ...
 *  6 ...................
 *  
 * 오른쪽위방향대각선을 차례대로 그었을때 r+c가 같은 것들을 한줄로 놓고보면 규칙성이있다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int dx = Math.abs(x1 - x2); // 세로 거리
			int dy = Math.abs(y1 - y2); // 가로 거리
			int diff = Math.abs(dx - dy); // 가로 세로 거리의 차
			
			int answer = dx + dy + (diff - ((dx + dy) & 1)); // 가로 거리 + 세로 거리 + (차 - (합의 홀짝여부))
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}