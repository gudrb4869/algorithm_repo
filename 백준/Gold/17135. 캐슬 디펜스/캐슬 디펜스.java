import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 궁수 3명을 배치하려고하는데
 * 궁수 한명은 한번에 적 한명만 공격할 수 있고
 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적
 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격
 * 같은 적이 여러 궁수에게 공격당할 수 있다.
 * 1초가 지나면 적은 한칸 아래로 내려간다.
 * 적이 성이 있는 칸으로 이동시 게임에서 제외됨
 * 두위치의 거리는 |r1-r2| + |c1-c2| 이다.
 * 궁수의 공격범위 D가 주어지고 그 범위내에 적이 있으면 공격이 가능함
 * N*M의 격자가 있을때 궁수는 N+1행의 M칸중 3개의 칸에 배치를 해야함
 * 따라서 M개중 3개를 뽑는 MC3 조합을 이용해 경우의수에 게임결과중 최댓값을 리턴하도록 해보았다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, M, D, arr[][], archer[], answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자판 행의 수
		M = Integer.parseInt(st.nextToken()); // 격자판 열의 수
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한 D
		
		arr = new int[N][M]; // N * M 격자
		archer = new int[3]; // 궁수3명의 위치(0~M-1)
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 격자판의  상태 저장
			}
		}
		
		combination(0, 0); // mC3 조합의 경우의 수마다 제거할 수 있는 적의 최대수를 계산
		
		System.out.println(answer); // 결과 출력
	}

	private static void combination(int cnt, int start) {
		
		if (cnt == 3) { // 기저조건
			
			int[][] check = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					check[i][j] = -1; // 1~3번째 궁수들이 최초로 해당 적을 공격한 시간
				}
			}
			PriorityQueue<int[]>[] pq = new PriorityQueue[3]; // 세명의 궁수에 대해 적들의 위치를 가까운순으로 꺼낼 우선순위 큐
			
			for (int i = 0; i < 3; i++) {
				int k = i;
				pq[k] = new PriorityQueue<>(new Comparator<int[]>() {
					@Override
					public int compare(int[] a, int[] b) {
						int distA = getDistance(a[0], a[1], N, archer[k]); // 적a와 k번째 궁수의 거리
						int distB = getDistance(b[0], b[1], N, archer[k]); // 적b와 k번째 궁수의 거리
						
						if (distA == distB) { // 가장 가까운 적이 여럿일 경우에는
							return a[1] - b[1]; // 가장 왼쪽에 있는 적부터 공격할 수 있도록 정렬조건 세팅
						}
						return distA - distB; // 거리가 가장 가까운 적부터 나올수있도록 정렬조건 설정
					}
				});
				
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) { // 적의 위치이면
						for (int k = 0; k < 3; k++) {
							pq[k].offer(new int[] {i, j}); // 3개의 우선순위큐에 적의 위치 삽입
						}
					}
				}
			}
			
			for (int time = 0; time < N; time++) { // 0초부터 N-1초까지 게임이 진행됨
				for (int i = 0; i < 3; i++) { // 첫번째 궁수부터 세번째 궁수에 대해 공격할 적 탐색
					while (!pq[i].isEmpty()) { // 반복문 조건
						int[] info = pq[i].poll();
						int r = info[0], c = info[1]; // 적의 초기 위치
						if (r + time >= N || (check[r][c] >= 0 && check[r][c] < time)) { // 성에 도달했거나, 이미 해당적이 이전시간에 공격되어 제거되었다면
							continue; // 무시
						}
						int d = getDistance(N, archer[i], r + time, c); // 적과의 거리
						if (d <= D) { // 궁수의 공격범위내에 있다면
							check[r][c] = time; // 해당 적 공격시간 기록
						} else { // 공격범위밖이라면
							pq[i].offer(info); // 다시 우선순위큐에 삽입
						}
						break;
					}
				}
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j] >= 0) { // 해당 적이 공격당했다면
						result++; // 1증가
					}
				}
			}
			answer = Math.max(answer, result); // 가장 많은 적을 제거한 경우로 갱신
			return;
		}
		
		for (int i = start; i < M; i++) {
			archer[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	public static int getDistance(int r1, int c1, int r2, int c2) { // 두 지점간의 거리
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

}