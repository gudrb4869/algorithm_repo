import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 햄버거재료 N개의 부분집합들을 검사한다. 
 * 그중에 제한칼로리이하이면서 맛점수가 높은것을 찾는방식으로 문제를 해결했다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	private static int N, L, ingredient[][], answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) { // 케이스마다 반복
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			ingredient = new int[N][2]; // 재료마다 (맛에대한 점수, 칼로리) 저장하기 위해 2차원배열생성
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken()); // 재료에대한 민기의 맛에대한점수
				ingredient[i][1] = Integer.parseInt(st.nextToken()); // 재료에대한 민기의 맛에대한칼로리
			}
			
			subset(0, 0, 0);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	/**
	 * 부분집합 메서드
	 * @param cnt 현재 검토할 재료의 인덱스
	 * @param tasteScore 현재까지의 구성한 재료들의 맛에대한점수의합
	 */
	private static void subset(int cnt, int taste, int calorie) {
		
		if (cnt == N) {
			answer = Math.max(answer, taste);
			return;
		}
		
		if (calorie+ingredient[cnt][1] <= L) {
			subset(cnt+1, taste+ingredient[cnt][0], calorie+ingredient[cnt][1]);
		}
		subset(cnt+1, taste, calorie);
		
	}

}
