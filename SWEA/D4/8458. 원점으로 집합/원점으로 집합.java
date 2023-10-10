import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		
		for (int t = 1; t <= T; t++) { // 각 테스트 케이스
			
			int N = Integer.parseInt(br.readLine()); // 하나의 정수
			int[][] coor = new int[N][2];
			
			int maxDist = 0; // 원점으로부터 떨어진 거리의 합의 최댓값
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				coor[i][0] = Math.abs(Integer.parseInt(st.nextToken())); // x좌표 절대값
				coor[i][1] = Math.abs(Integer.parseInt(st.nextToken())); // y좌표 절대값
				
				maxDist = Math.max(maxDist, coor[i][0] + coor[i][1]);
			}
			
			boolean isPossible = true;
			for (int i = 0; i < N; i++) {
				if (maxDist % 2 != (coor[i][0] + coor[i][1]) % 2) {
					isPossible = false;
					break;
				}
			}
			
			int answer = -1;
			if (isPossible) {
				int cur = 0, i = 0;
				
				while (true) {
					cur += i;
					if (cur >= maxDist) {
						int diff = cur - maxDist;
						if (diff % 2 == 0) {
							answer = i;
						} else {
							if (i % 2 == 0) {
								answer = i + 1;
							} else {
								answer = i + 2;
							}
						}
						break;
					}
					i++;
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		
		System.out.print(sb);
	}

}