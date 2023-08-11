import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * N*N 크기의 도시는 빈칸(0), 치킨집(1), 집(2)으로 구성되있음
 * 좌표는 1,1 부터 n,n 까지 되어있음
 * 치킨거리 = 집과 가장 가까운 치킨집 사이의 거리
 * 도시의 치킨거리 = 모든 집의 치킨 거리의 합
 * 임의의 두칸 사이의 거리 (r1,c1)과 (r2,c2) 사이의 거리 = |r1-r2|+|c1-c2|
 * 도시에 있는 치킨집중 최대 M개를 골라 나올수 있는 도시의 치킨거리가 최솟값을 구해야함
 * N이 최대 50이고 M이 최대13이다. 집은 최소1개 최대 2N개까지 있고 치킨집의 개수는 M개이상 13개이하이다.
 * 도시의 치킨집의 개수를 x라 했을때 m개를 고르는 경우의수를 모두 따져보는 완전탐색으로 풀 수 있을 거 같다.  
 * </pre>
 * @author 박형규
 */
public class Main {

	static List<int[]> chicken = new ArrayList<>(); // 치킨집(1)의 좌표를 저장할 리스트
	static List<int[]> home = new ArrayList<>(); // 집(2)들의 좌표를 저장할 리스트
	static int[] numbers;
	static int answer = 987654321; // 도시의 치킨 거리
	static int h, c, n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 도시의 크기
		m = Integer.parseInt(st.nextToken()); // 남길 치킨집의 최대 개수
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int cur = Integer.parseInt(st.nextToken()); // 도시의 정보
				if (cur == 1) { // 집인 경우
					home.add(new int[] {i, j});
				} else if (cur == 2) { // 치킨집인 경우
					chicken.add(new int[] {i, j});
				}
				// 빈칸인 경우 skip
			}
		}
		h = home.size(); // 집의 개수
		c = chicken.size(); // 치킨집의 개수
		
		numbers = new int[c];
		
		combination(0, 0);
	
		System.out.println(answer);
		
	}

	private static void combination(int cnt, int start) {
		
		if (cnt == m) {
			// 치킨거리 계산 로직
			
			int[] distance = new int[h]; // 치킨거리를 저장할 배열 생성
			Arrays.fill(distance, 101); // 치킨거리 무한대값으로 초기화
			
			for (int i = 0; i < h; i++) {
				int[] t1 = home.get(i);
				int r1 = t1[0], c1 = t1[1]; // 집 좌표
				for (int j = 0; j < m; j++) {
					int[] t2 = chicken.get(numbers[j]);
					int r2 = t2[0], c2 = t2[1];
					
					int dist = Math.abs(r1 - r2) + Math.abs(c1 - c2); // 치킨거리
					if (dist < distance[i]) {
						distance[i] = dist; // 치킨거리 갱신
					}
				}
				
			}
			
			int result = 0;
			for (int i = 0; i < h; i++) {
				result += distance[i]; // 치킨집중 r개를 뽑았을때 도시의 치킨거리
			}
			
			answer = Math.min(answer, result); // 도시의 치킨거리의 최솟값를 가장 작은값으로 갱신
			
			return;
		}
		
		for (int i = start; i < c; i++) {
			numbers[cnt] = i; 
			combination(cnt+1, i + 1);
		}
	}

}
