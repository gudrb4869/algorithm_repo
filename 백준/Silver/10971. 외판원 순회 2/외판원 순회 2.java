import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * DFS + 백트래킹으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, answer = 987654321, W[][];
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시의 수
		
		W = new int[N][N]; // 비용 행렬
		visited = new boolean[N]; // 방문 체크 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken()); // 도시 i에서 도시 j로 가기 위한 비용
			}
		}
		
		visited[0] = true; // 시작점 방문체크
		dfs(0, 0, 1); // 재귀
		System.out.println(answer); // 출력
	}

	/**
	 * 
	 * @param i 현재 방문중인 도시
	 * @param weight 현재 순회 비용
	 * @param cnt 현재 방문한 도시의 개수
	 */
	private static void dfs(int i, int weight, int cnt) {
		
		if (weight >= answer) { // 가지 치기
			return; // 현재 순회 비용이 최소비용보다 크거나 같은경우 더이상 볼 필요없음
		}
		
		if (cnt == N) { // 기저 조건
			if (W[i][0] > 0) { // 마지막 방문 도시에서 시작점으로 갈 수 있는 경우인지 확인
				answer = Math.min(answer, weight + W[i][0]); // 순회에 필요한 최소 비용으로 갱신
			}
			return;
		}
		
		for (int j = 0; j < N; j++) { // 현재 도시 i에서 다음 도시 j 모두 확인
			if (i == j) continue; // skip
			
			if (!visited[j] && W[i][j] > 0) { // 미방문 도시이고 도시 i에서 갈 수 있는 경우
				visited[j] = true; // 도시 j 방문 체크
				dfs(j, weight + W[i][j], cnt + 1); // 재귀 함수 호출
				visited[j] = false; // 도시 j 방문 해제
			}
		}
		
	}

}