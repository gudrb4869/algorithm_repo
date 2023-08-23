import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 최소 신장 트리 문제
 * => 크루스칼 알고리즘을 이용하여 문제 풀이함
 * + 유니온 파인드 알고리즘 활용
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static int V, E, parents[], edgeList[][], index;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 전체 테스트 케이스의 수
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken()); // 정점의 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수
			
			makeSet();
			
			edgeList = new int[E][3];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()); // A번 정점
				int B = Integer.parseInt(st.nextToken()); // B번 정점
				int C = Integer.parseInt(st.nextToken()); // 가중치
				
				edgeList[i][0] = A; // A번 정점
				edgeList[i][1] = B; // B번 정점
				edgeList[i][2] = C; // 가중치
			}
			
			Arrays.sort(edgeList, new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return a[2] - b[2]; // 가중치 기준으로 오름차순 정렬
				}
			});
			
			long answer = 0; // 최소 스패닝 트리의 가중치 (정점이 최대 10만개이므로 99999 * 1000000 하면 int범위 벗어날수 있어서 long타입으로 해줌)
			int count = 0; // 현재 트리를 구성하는 간선의 개수
			for (int[] edge : edgeList) {
				if (union(edge[0], edge[1])) {
					answer += edge[2];
					if (++count == V - 1) break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void makeSet() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}

}