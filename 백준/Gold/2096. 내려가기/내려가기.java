import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 다이나믹 프로그래밍과 슬라이딩 윈도우로 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄
		int[] dpMin = new int[3];
		int[] dpMax = new int[3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int Z = Integer.parseInt(st.nextToken());
			
			if (i == 0) {
				dpMin[0] = dpMax[0] = X;
				dpMin[1] = dpMax[1] = Y;
				dpMin[2] = dpMax[2] = Z;
			} else {
				int dpMin0 = dpMin[0], dpMin2 = dpMin[2];
				dpMin[0] = Math.min(dpMin[0], dpMin[1]) + X;
				dpMin[2] = Math.min(dpMin[1], dpMin[2]) + Z;
				dpMin[1] = Math.min(dpMin[1], Math.min(dpMin0, dpMin2)) + Y;

				int dpMax0 = dpMax[0], dpMax2 = dpMax[2];
				dpMax[0] = Math.max(dpMax[0], dpMax[1]) + X;
				dpMax[2] = Math.max(dpMax[1], dpMax[2]) + Z;
				dpMax[1] = Math.max(dpMax[1], Math.max(dpMax0, dpMax2)) + Y;
			}
		}
		
		sb.append(Math.max(dpMax[0], Math.max(dpMax[1], dpMax[2]))).append(" ").append(Math.min(dpMin[0], Math.min(dpMin[1], dpMin[2]))).append("\n");
		
		System.out.print(sb);
	}

}