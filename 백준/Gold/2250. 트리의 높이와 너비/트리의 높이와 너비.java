import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static int N, cnt = 1;
	static List<Integer>[] graph;
	static Map<Integer, TreeSet<Integer>> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 노드의 개수
		N = Integer.parseInt(br.readLine());
		
		// 트리 구성
		graph = new List[N + 1];
		// 루트 노드 확인용
		boolean[] visited = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int node = Integer.parseInt(st.nextToken());
			
			int left = Integer.parseInt(st.nextToken());
			if (left != -1) visited[left] = true;
			int right = Integer.parseInt(st.nextToken());
			if (right != -1) visited[right] = true;
			
			graph[node].add(left);
			graph[node].add(right);
		}
		
		// 루트 노드의 번호
		int root = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				root = i;
				break;
			}
		}
		
		// 중위 순회
		inOrder(root, 1);
		
		// 트리의 높이들
		Set<Integer> keys = map.keySet();
		
		int level = 0, width = 0;
		for (int key : keys) {
			int w = map.get(key).last() - map.get(key).first() + 1;
			if (w > width) {
				level = key;
				width = w;
			}
		}
		
		System.out.println(level + " " + width);
	}
	
	static void inOrder(int node, int depth) {
		if (node == -1)
			return;
		
		inOrder(graph[node].get(0), depth + 1);
		map.computeIfAbsent(depth, k -> new TreeSet<>());
		map.get(depth).add(cnt++);
		inOrder(graph[node].get(1), depth + 1);
	}

}