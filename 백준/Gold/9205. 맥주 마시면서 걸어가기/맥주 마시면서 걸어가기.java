import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 50미터마다 맥주 한캔이 필요한데 최대 맥주를 20개밖에 못가지고 다니고, 편의점에 들르면 20개로 다시 채울수있다.
 * 그렇기 때문에 두 지점간의 거리가 1000미터를 넘어가게 되면 이동이 불가능함
 * 따라서 각 정점간의 거리를 저장할때 1000이하인 값만 저장하고 1000 초과시 무한대값으로 설정한다.
 * 그러고나서 플로이드-워샬 알고리즘을 사용하여 상근이네집에서 펜타포트 락 페스티벌까지의 최단경로를 구한다.
 * 최단경로가 존재하면 happy, 무한대면 존재하지 않으므로 sad를 출력
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 맥주를 파는 편의점의  개수
			
			int[][] D = new int[N + 2][N + 2]; // 플로이드-워샬에 사용할 2차원배열
			int[][] info = new int[N + 2][2]; // x,y 좌표정보를 임시로 저장할 배열
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				info[i][0] = Integer.parseInt(st.nextToken());
				info[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 두 좌표 사이의 거리를 저장하는데 1000을 넘어가면 무한대로 저장함
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					int distance = Math.abs(info[i][0] - info[j][0]) + Math.abs(info[i][1] - info[j][1]);
					D[i][j] = distance > 1000 ? 987654321 : distance;
				}
			}
			
			// 플로이드-워샬 알고리즘
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					for (int j = 0; j < N + 2; j++) {
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			
			sb.append(D[0][N + 1] == 987654321 ? "sad\n" : "happy\n");
		}
		
		System.out.print(sb);
	}

}