import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개의 구역을 두 개의 선거구로 나눠야하는데, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야함
 * 
 * 
 * 모든 경우의수를 다 탐색해보는 방법으로 풀이가 가능
 * 완전탐색, 부분집합, bfs 사용하여 문제 해결
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, arr[], answer = 987654321; // 두 선거구의 인구 차이의 최솟값을 저장할 변수
	static List<Integer>[] graph; // 인접리스트
	static boolean[] isA; // 구역이 선거구 A에 속하는지 체크할 배열
	static boolean[] isB; // 구역이 선거구 B에 속하는지 체크할 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		
		N = Integer.parseInt(br.readLine()); // 구역의 개수 (2~10)
		arr = new int[N]; // 1번구역부터 N번구역까지 인구를 저장할 1차원 배열
		graph = new List[N]; // 1번구역부터 N번구역까지 인접한 구역번호를 저장할 리스트 배열
		isA = new boolean[N]; // 선거구 A에 속하는지에 대한 여부
		isB = new boolean[N]; // 선거구 B에 속하는지에 대한 여부
		Arrays.fill(isB, true); // 초기에 모든 구역이 선거구B에 속한다고 가정하고 부분집합 스타트하기위해 true로 초기화해줌
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 구역의 인구를 공백으로 분리
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 구역의 인구수를 저장
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // i번 구역기준 인접한 구역의 수
			for (int j = 0; j < num; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()) - 1); // 인접한 구역번호를 그래프에 저장
			}
		}
		
		for (int k = 1; k <= N / 2; k++) {
			combination(0, 0, k); // nC1부터 nC(n/2)까지 부분 집합 탐색
		}
		
		if (answer == 987654321) { // 두 선거구로 나눌 수 없는 경우
			System.out.println(-1);
		} else { // 두 선거구로 나눈 인구 차이의 최솟값이 존재함
			System.out.println(answer);
		}
	}

	/**
	 * 조합 메서드
	 * @param cnt 현재까지 선거구 A에 뽑은 구역의 개수
	 * @param start 탐색하기 시작할 구역의 첫 번호
	 * @param k 선거구 A에 뽑을 구역의 개수
	 */
	private static void combination(int cnt, int start, int k) {
		
		if (cnt == k) { // 기저조건
			if (isConnected(isA) && isConnected(isB)) { // 선거구 A에 속한 구역이 모두 연결되어있고 선거구 B에 속한 구역이 모두 연결되어있는 경우 
				answer = Math.min(answer, Math.abs(getSum(isA) - getSum(isB))); // 두 선거구의 인구 차이의 최솟값으로 갱신
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) { // 조합
			isA[i] = true; // i번 구역을 선거구 A로 지정
			isB[i] = false; // i번 구역을 선거구 B에서 해제
			combination(cnt + 1, i + 1, k);
			isA[i] = false; // i번 구역을 선거구 A에서 해제
			isB[i] = true; // i번 구역을 선거구 B로 지정
		}
		
	}

	/**
	 * 선거구의 인구를 반환하는 메서드
	 * @param sector 선거구에 속하는지 저장되있는 배열
	 * @return 선거구의 총 인구
	 */
	private static int getSum(boolean[] sector) { // 선거구의 인구
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (sector[i]) { // i번 구역이 선거구에 속한다면
				sum += arr[i]; // 인구더해줌
			}
		}
		return sum; // 총인구수 반환
	}

	/**
	 * 선거구의 모든 구역들이 연결되어 있는지 체크해주는 메서드
	 * @param sector 선거구에 속해있는지 저장되어있는 배열
	 * @return 선거구내의 모든구역들이 서로 연결되어있다면 true, 아니면 false
	 */
	private static boolean isConnected(boolean[] sector) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N]; // 해당 구역을 방문했는지 체크할 배열
		for (int i = 0; i < N; i++) {
			if (sector[i]) { // i번 구역이 선거구에 속한다면
				q.offer(i); // 큐에 삽입
				visited[i] = true; // i번 구역 방문체크
				break; // 탐색 중단
			}
		}
		
		while (!q.isEmpty()) { // bfs 수행
			int current = q.poll(); // 현재 구역의 번호
			
			for (int next : graph[current]) { // 현재구역기준 인접구역의 번호
				if (sector[next] && !visited[next]) { // 다음구역이 선거구에 속하면서, 아직 미방문한 구역이라면
					visited[next] = true; // 방문체크해주고
					q.offer(next); // 큐에 삽입
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (sector[i] && !visited[i]) { // 선거구에 속하는 구역인데 미방문한 구역이 존재한다면
				return false; // 선거구가 하나로 연결되어있지 않고 분리된 경우라 false 리턴
			}
		}
		
		return true; // 선거구에 속하는 모든 구역이 연결되어 있으므로 true 리턴
	}

}