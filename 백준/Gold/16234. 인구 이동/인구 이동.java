import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색 + 문제 조건대로 구현해서 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		int N = Integer.parseInt(st.nextToken()); // 땅의 가로세로크기
		int L = Integer.parseInt(st.nextToken()); // 인구이동이 발생하기 위한 최소인원수
		int R = Integer.parseInt(st.nextToken()); // 인구이동이 발생하기 위한 최대인원수
		
		int[][] arr = new int[N][N]; // N*N의 땅. 각각의 땅에 나라의 인구수 저장 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 각 나라의 인구수 저장
			}
		}
		
		int answer = 0; // 인구 이동이 발생한 일수
		int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
		int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
		
		while (true) {
			
			boolean[][] visited = new boolean[N][N]; // 각 나라 방문 여부 확인하기 위한 배열
			
			boolean flag = false; // 인구 이동 발생 여부
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) continue; // 이미 방문한 나라인 경우 건너뜀 
					
					Queue<int[]> q = new ArrayDeque<>(); // 너비 우선 탐색에 사용할 큐
					Queue<int[]> union = new ArrayDeque<>(); // 인구 이동을 위해 사용할 큐
					visited[i][j] = true; // 현재 위치 방문 처리
					q.offer(new int[] {i, j});
					union.offer(new int[] {i, j});
					int sum = 0, cnt = 0; // 연합의 인구수, 연합을 이루고 있는 칸의 개수
					
					while (!q.isEmpty()) {
						int[] info = q.poll();
						int r = info[0], c = info[1];
						
						sum += arr[r][c]; // 연합의 인구수 누적
						cnt++; // 연합을 이루고 있는 칸의 개수 증가
						
						for (int d = 0; d < 4; d++) { // 사방 탐색
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							// 땅의 범위 내에 있고, 미방문 나라인 경우
							if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
								int diff = Math.abs(arr[r][c] - arr[nr][nc]); // 국경선을 공유하는 두 나라의 인구 차이
								if (diff >= L && diff <= R) { // L명 이상 R명 이하인 경우
									flag = true; // 인구이동이 발생함
									visited[nr][nc] = true; // 다음 위치 방문 체크
									q.offer(new int[] {nr, nc});
									union.offer(new int[] {nr, nc});
								}
							}
						}
					}
					
					int result = sum / cnt; // 연합을 이루고 있는 각 칸의 인구수
					while (!union.isEmpty()) { // 연합
						int[] info = union.poll();
						int r = info[0], c = info[1]; // 연합을 이루고 있는 나라의 좌표
						
						arr[r][c] = result; // 인구 이동
					}
				}
			}
			
			
			if (!flag) { // 인구 이동이 발생하지 않은 경우
				break; // 반복 중단
			}
			answer++; // 인구 이동 발생 일수 1증가
		}
		
		System.out.println(answer); // 결과 출력
	}

}