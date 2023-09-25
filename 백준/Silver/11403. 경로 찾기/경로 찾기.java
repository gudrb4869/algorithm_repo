import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 가중치 없는 방향 그래프 G에 대해 정점 i에서 j로 가는 경로가 존재하는지 여부를 구하는 문제
 * 플로이드 워셜로 풀어봤는데 답이 나왔다.
 * 더 좋은 풀이방법이 있는지는 고민해봐야될 거 같다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = 987654321;
				if (Integer.parseInt(st.nextToken()) == 1) {
					arr[i][j] = 1;
				}
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 987654321) {
					sb.append("0 ");
				} else {
					sb.append("1 ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}