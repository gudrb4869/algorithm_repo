import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 서로소 집합 문제
 * 유니온 파인드 알고리즘으로 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken()); // 집합의 원소의 개수
			int m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수
			
			parents = new int[n + 1];
			
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int cmd = Integer.parseInt(st.nextToken()); // 0일때 두 집합을 합집합, 1일때 두 집합이 서로 같은집합인지여부
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (cmd == 0) { // 0 일때 합집합 연산
					union(a, b);
				} else { // 1 일때 두 집합이 서로 같으면 1, 다르면 0 출력
					sb.append(find(a) == find(b) ? 1 : 0);
				}
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	private static void union(int a, int b) {
		int aRoot = find(a); // a가 속한 집합의 대표자
		int bRoot = find(b); // b가 속한 집합의 대표자
		
		// 두집합의 대표자가 서로 다르면 bRoot의 대표자를 aRoot로 바꿈
		if (aRoot != bRoot) parents[bRoot] = aRoot;
	}

	private static int find(int x) {
		if (parents[x] == x) return x; // 원소 x의 대표자가 자기자신이면 자기자신 리턴
		return parents[x] = find(parents[x]); // 아니면 부모노드의 대표자 탐색하여 경로압축및 리턴
	}

}