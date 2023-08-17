import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제에서 (x, y) 이므로 문제에 맞게 세팅해주었다.
 * A와 B가 서로 같은 영역의 BC에 겹치거나 서로 다른 BC에 존재할 수 있는 여러가지 경우의수가 존재하는데
 * 사용자가 총 2명이고 BC의 개수가 최대 8개이므로 최대 8P2=56가지밖에 안된다. 따라서 완전탐색으로 문제를 풀어보았다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static int[] dx = {0, 0, 1, 0, -1}; // 이동하지않음, 상, 우, 하, 좌
	static int[] dy = {0, -1, 0, 1, 0}; // 이동하지않음, 상, 우, 하, 좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int t = 1; t <= T; t++) { // 테스트 케이스마다 반복
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 이동 시간
			int A = Integer.parseInt(st.nextToken()); // BC의 개수
			
			List<Integer>[][] arr = new ArrayList[11][11]; // (1,1)부터 (10,10)까지 BC의 목록을 저장할 리스트
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					arr[i][j] = new ArrayList<>();
					arr[i][j].add(0); // 성능 0짜리 가상의 BC 0으로 세팅
				}
			}
			
			int[] infoA = new int[M + 1]; // 0초부터 ~ M초까지 이동정보 저장하기 위한 배열 (0인덱스에는 0으로 기본세팅)
			int[] infoB = new int[M + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				infoA[i] = Integer.parseInt(st.nextToken()); // A의 이동 정보
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				infoB[i] = Integer.parseInt(st.nextToken()); // B의 이동 정보
			}
			
			int[][] BC = new int[A + 1][A + 1]; // A와 B가 BC영역 내에 있을 때 최대 충전량
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); // i번째 BC의 x좌표
				int y = Integer.parseInt(st.nextToken()); // i번째 BC의 y좌표
				int C = Integer.parseInt(st.nextToken()); // i번째 BC의 충전 범위
				int P = Integer.parseInt(st.nextToken()); // i번째 BC의 성능
				
				BC[i][0] = BC[0][i] = P; // i번째 BC의 성능 P로 초기화
				Queue<int[]> q = new ArrayDeque<>(); // 큐
				boolean[][] visited = new boolean[11][11]; // BFS 위한 방문 체크 배열
				q.add(new int[] {x, y, 0}); // BC의 위치와 현재 충전 범위를 넘겨줌
				visited[x][y] = true; // BC의 위치 방문체크
				
				while (!q.isEmpty()) {
					int[] info = q.poll();
					int xx = info[0], yy = info[1], cnt = info[2]; // 현재 x좌표, y좌표, 현재 충전 범위
					arr[xx][yy].add(i); // 현재 위치에 BC의 번호를 추가
					
					for (int k = 1; k <= 4; k++) { // 4방향
						int nx = xx + dx[k];
						int ny = yy + dy[k];
						
						if (nx < 1 || nx > 10 || ny < 1 || ny > 10 || visited[nx][ny] || cnt + 1 > C) {
							continue; // 지도의 범위를 벗어났거나 이미 방문한 지역이거나, 충전 범위를 초과할 경우 더이상 진행하지 않음
						}
						
						visited[nx][ny] = true; // 방문 체크
						q.add(new int[] {nx, ny, cnt + 1}); // 큐에 삽입
					}
				}
			}
			
			// 완탐 (BC가 최대 8개이므로 모든 경우의 수를 다 따져본다)
			for (int i = 1; i <= A; i++) {
				for (int j = 1; j <= A; j++) {
					BC[i][j] = BC[i][0] + BC[j][0]; // i번째 BC와 j번째 BC 성능의 합
					if (i == j) { // 서로 같은 BC내에 있다면
						BC[i][j] /= 2; // 성능을 균등하게 분배해야하므로 성능 반으로 감소
					}
				}
			}
			
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					Collections.sort(arr[i][j]); // 각 위치에 있는 BC번호 오름차순 정렬
				}
			}
			
			int answer = 0; // 충전한 양의 합의 최댓값
			int aX = 1, aY = 1, bX = 10, bY = 10;
			for (int k = 0; k <= M; k++) {
				aX += dx[infoA[k]]; // 사용자 A의 x좌표 이동
				aY += dy[infoA[k]]; // 사용자 A의 y좌표 이동
				bX += dx[infoB[k]]; // 사용자 B의 x좌표 이동
				bY += dy[infoB[k]]; // 사용자 B의 y좌표 이동
				
				int result = 0; // 현재 위치에서 충전량으 최대값 탐색
				for (int i = 0; i < arr[aX][aY].size(); i++) {
					for (int j = 0; j < arr[bX][bY].size(); j++) {
						result = Math.max(result, BC[arr[aX][aY].get(i)][arr[bX][bY].get(j)]);
					}
				}
				answer += result; // A와 B의 현재위치에서 충전량 더함
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n"); // 출력 결과 저장
		}
		System.out.print(sb); // 모든 테스트 케이스 결과 출력
	}

}