import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 플로이드 워셜 알고리즘을 사용
 * 리스트 2차원 배열에 최단 경로를 지나는 도시 번호를 저장
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄, 도시의 개수 n
		int M = Integer.parseInt(br.readLine()); // 둘째 줄, 버스의 개수 m
		
		int[][] arr = new int[N + 1][N + 1];
		List<Integer> path[][] = new List[N + 1][N + 1];
		int INF = 987654321; // 무한대값
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = INF; // 무한대값
				path[i][j] = new ArrayList<>(); // 도시 i에서 도시 j로 가는 경로를 저장할 리스트
			}
		}
		
		for (int i = 1; i <= N; i++) {
			arr[i][i] = 0; // 자기 자신으로 가는 최소비용 0으로 초기화
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 셋째 줄 ~ m+2줄 버스의 정보
			
			int A = Integer.parseInt(st.nextToken()); // 버스의 시작 도시 a
			int B = Integer.parseInt(st.nextToken()); // 버스의 도착 도시 b
			int C = Integer.parseInt(st.nextToken()); // 한번 타는데 필요한 비용 c
			arr[A][B] = Math.min(arr[A][B], C); // A에서 B로 가는 비용 최솟값으로 갱신
			if (path[A][B].size() == 0) { // A에서 B로 가는 경로가 저장안되어있는 경우
				path[A][B].add(A);
				path[A][B].add(B);
			}
		}
		
		// 플로이드 워셜 알고리즘 
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (arr[i][j] > arr[i][k] + arr[k][j]) { // 경유지를 거쳐가는게 더 적은 비용이 들경우
						arr[i][j] = arr[i][k] + arr[k][j]; // 경유지 거쳐가도록 갱신
						path[i][j].clear(); // 리스트 클리어
						for (int idx = 0; idx < path[i][k].size(); idx++) {
							path[i][j].add(path[i][k].get(idx)); // 출발지에서 경유지로 가는 경로 저장
						}
						for (int idx = 1; idx < path[k][j].size(); idx++) {
							path[i][j].add(path[k][j].get(idx)); // 경유지 다음지점부터 도착지로 가는 경로 저장
						}
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (arr[i][j] == INF) {
					arr[i][j] = 0; // i에서 j로 갈 수 없는 경우 0을 출력하라는 출력 조건
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(arr[i][j]).append(" "); // 도시 i에서 j로 가는 최소비용 출력
			}
			sb.append("\n");
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 도시 i에서 j로 가는 경로 출력
				if (arr[i][j] == 0) {
					sb.append("0\n");
				} else {
					sb.append(path[i][j].size()).append(" ");
					for (int city : path[i][j]) {
						sb.append(city).append(" ");
					}
					sb.append("\n");
				}
			}
		}
		
		System.out.print(sb);
	}

}