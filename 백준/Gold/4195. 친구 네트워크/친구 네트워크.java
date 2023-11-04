import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * 분리 집합(Disjoint Set) 문제. 유니온파인드를 이용하여 문제를 풀었다.
 * 집합의 루트번호를 저장할 배열 parent, 집합내에 노드의 개수를 저장할 배열 network
 * 문자열을 정수형태로 변환하기 위해 해시맵을 이용
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int[] parent = new int[200000];
	static int[] network = new int[200000];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			
			int F = Integer.parseInt(br.readLine()); // 친구 관계의 수
			
			for (int i = 0; i < 200000; i++) {
				parent[i] = i;
				network[i] = 1;
			}
			
			Map<String, Integer> map = new HashMap<>();
			int count = 0;
			
			for (int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String A = st.nextToken(); // 친구 A
				String B = st.nextToken(); // 친구 B
				
				if (map.get(A) == null) {
					map.put(A, count++);
				}
				int a = map.get(A);
				if (map.get(B) == null) {
					map.put(B, count++);
				}
				int b = map.get(B);
				union(a, b);
			}
			
		}
		
		System.out.print(sb);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA != rootB) {
			network[rootA] += network[rootB];
			parent[rootB] = rootA;
		}
		
		sb.append(network[rootA]).append("\n");
		
	}

	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}