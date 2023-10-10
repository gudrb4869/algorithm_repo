import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 원점에서 각 점의 거리를 x좌표 절대값 + y좌표 절대값 이라고하면은
 * 모든점을 원점으로 이동가능한 경우는 각 점들의 거리가 모두 홀수이거나, 모두 짝수인 경우뿐이다.
 * 
 * 각점의 거리의합이 모두 홀수, 모두짝수인경우
 * 거리가 가장 먼 좌표의 이동횟수가 모든 점을 원점으로 이동시키는 횟수이다.
 * 
 * i번째 움직임에서 무조건 i만큼 점을 이동시켜야하므로
 * 거리가 1,3,6,10,15,... 인경우에는 딱맞아떨어지지만
 * 거리가 (i-1)*i/2 < x < i*(i+1)/2 인 경우에는
 * 거리차 diff = i*(i+1)/2 - x와 i에 따라서 달라진다.
 * diff가 짝수인 경우에는 i만큼걸리고,
 * diff가 홀수인 경우에는 i가 짝수인경우 i+1만큼 걸리고, i가 홀수인경우에는 i+2만큼 걸림  
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
				
				maxDist = Math.max(maxDist, coor[i][0] + coor[i][1]); // 점들 중에서 원점과의 거리가 가장먼걸로 갱신
			}
			
			boolean isPossible = true; // 모든 점을 원점으로 이동시킬 수 있는지 체크할 플래그
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