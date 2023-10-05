import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색을 사용, 잃는 루피의 값이 작은 좌표부터 사방탐색을 하기 위해 우선순위큐를 이용했다.
 * boolean의 방문 체크 배열 대신에, 각 좌표마다 잃는 루피의 최솟값을 저장하도록 했음.
 * 사방탐색 하면서 우선순위 큐에 다음 좌표를 넣는 경우는 현재좌표에서 다음좌표로 갔을때 잃는 루피가 기존에 저장되어있던 값보다 작은 경우 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		int T = 1; // 테스트 케이스 번호
		
		StringTokenizer st;
		while (true) {
			int N = Integer.parseInt(br.readLine()); // 각 테스트 케이스의 첫째 줄, 동굴의 크기를 나타내는 정수 (2 ~ 125)
			if (N == 0) { // 0인 입력이 주어지면 전체 입력이 종료
				break;
			}
			
			int[][] arr = new int[N][N]; // 동굴의 각 칸에 있는 도둑루피의 크기를 저장할 2차원 배열
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 동굴의 각 칸에 도둑 루피의 크기 저장
				}
			}
			
			// 너비 우선 탐색
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return a[0] - b[0]; // 잃는 루피가 작은 것부터 추출
				}
			}); // 우선 순위 큐
			
			int[][] result = new int[N][N]; // 해당 칸을 지나갈때 잃는 k루피의 최솟값
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result[i][j] = 100000000; // 무한대로 초기화
				}
			}
			result[0][0] = arr[0][0]; // 링크의 출발지점이  (0, 0)이므로 츨발할때부터 이곳에 위치한 도둑루피의 크기만큼 잃게됨
			pq.offer(new int[] {result[0][0], 0, 0}); // 우선순위 큐에 0,0 좌표 삽입
			
			int cost, r, c, info[], nr, nc;
			
			// 반복
			while (!pq.isEmpty()) {
				info = pq.poll(); // 좌표 정보 꺼내옴
				cost = info[0]; r = info[1]; c = info[2]; // 잃는 루피, 현재 행, 현재 열
				
				if (r == N - 1 && c == N - 1) { // 동굴의 출구에 도달한 경우
					break; // 더이상 너비 우선탐색 진행하지 않아도 됨
				}
				
				if (result[r][c] < cost) { // 기존에 저장된 루피값이 더 작거나 같은 경우
					continue; // 수행하지 않음
				}
				
				for (int d = 0; d < 4; d++) { // 상하좌우 사방탐색
					nr = r + dr[d]; // 다음 행
					nc = c + dc[d]; // 다음 열
					
					// 다음 좌표가 동굴을 벗어났거나, 현재 좌표에서 잃는 루피의 최솟값 + 다음좌표의 잃는 루피값이 다음 좌표에서 잃는 루피의 최솟값보다 크거나 같은경우 더이상 수행하지 않음
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || result[r][c] + arr[nr][nc] >= result[nr][nc]) {
						continue;
					}
					
					result[nr][nc] = result[r][c] + arr[nr][nc]; // 다음 좌표에서 잃는 루피의 최솟값을 갱신
					pq.offer(new int[] {result[nr][nc], nr, nc}); // 큐에 다음 좌표 삽입
				}
			}
			
			// 테스트 케이스 결과를 형식에 맞춰서 출력
			sb.append("Problem ").append(T++).append(": ").append(result[N - 1][N - 1]).append("\n");
		}
		
		System.out.print(sb);
	}

}