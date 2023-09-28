import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 공간복잡도를 최적화한 knapsack으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 번째 줄 입력
		
		int N = Integer.parseInt(st.nextToken()); // 민호의 집에 있는 물건의 종류의 수
		int M = Integer.parseInt(st.nextToken()); // 민호가 들 수 있는 가방의 최대 무게
		
		int[] D = new int[M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 물건의 무게
			int C = Integer.parseInt(st.nextToken()); // 물건을 가방에 넣었을 때 올라가는 민호의 만족도
			int K = Integer.parseInt(st.nextToken()); // 물건의 개수
			
			for (int j = 1; K > 0; j <<= 1) {
				int cnt = Math.min(j, K);
				int weight = V * cnt;
				int value = C * cnt;
				K -= cnt;
				for (int w = M; w > 0; w--) {
					if (w >= weight && D[w] < D[w - weight] + value) {
						D[w] = D[w - weight] + value;
					}
				}
			}
		}
		
		System.out.println(D[M]);
		

	}

}