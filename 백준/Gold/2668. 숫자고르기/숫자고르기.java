import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N + 1];
		int[] inDegree = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
			inDegree[A[i]]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			int next = A[cur];
			
			if (--inDegree[next] == 0) {
				q.offer(next);
			}
		}
		
		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] != 0)
				count++;
		}
		sb.append(count).append("\n");
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] != 0)
				sb.append(i).append("\n");
		}
		
		System.out.print(sb);
	}
}