import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		int[] path = new int[100001];
		
		visited[N] = true;
		q.offer(new int[] {N, 0});
		path[N] = -1;
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int x = info[0], t = info[1];
			
			if (x == K) {
				sb.append(t).append("\n");
				
				List<Integer> result = new ArrayList<>();
				int cur = K;
				while (cur != -1) {
					result.add(cur);
					cur = path[cur];
				}
				
				for (int i = result.size() - 1; i >= 0; i--) {
					sb.append(result.get(i)).append(" ");
				}
				sb.append("\n");
				break;
			}
			
			if (x * 2 < 100001 && !visited[x * 2]) {
				visited[x * 2] = true;
				path[x * 2] = x;
				q.offer(new int[] {x * 2, t + 1});
			}
			if (x + 1 < 100001 && !visited[x + 1]) {
				visited[x + 1] = true;
				path[x + 1] = x;
				q.offer(new int[] {x + 1, t + 1});
			}
			if (x - 1 >= 0 && !visited[x - 1]) {
				visited[x - 1] = true;
				path[x - 1] = x;
				q.offer(new int[] {x - 1, t + 1});
			}
		}
		System.out.print(sb);
	}

}