import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 너비 우선 탐색
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static class Data {
		int value;
		String cmd;
		
		public Data(int value, String cmd) {
			this.value = value;
			this.cmd = cmd;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			Queue<Data> q = new ArrayDeque<>();
			boolean[] visited = new boolean[10000];
			visited[A] = true;
			q.offer(new Data(A, ""));
			
			while (!q.isEmpty()) {
				Data data = q.poll();
				int value = data.value;
				String cmd = data.cmd;
				if (value == B) {
					sb.append(cmd).append("\n");
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					if (i == 0) { // D
						int nextValue = (value * 2) % 10000;
						if (!visited[nextValue]) {
							visited[nextValue] = true;
							q.offer(new Data(nextValue, cmd + "D"));
						}
					} else if (i == 1) { // S
						int nextValue = value - 1 >= 0 ? value - 1 : 9999;
						if (!visited[nextValue]) {
							visited[nextValue] = true;
							q.offer(new Data(nextValue, cmd + "S"));
						}
					} else if (i == 2) { // L
						int div = value / 1000;
						int mod = value % 1000;
						int nextValue = mod * 10 + div;
						if (!visited[nextValue]) {
							visited[nextValue] = true;
							q.offer(new Data(nextValue, cmd + "L"));
						}
					} else if (i == 3) { // R
						int div = value / 10;
						int mod = value % 10;
						int nextValue = mod * 1000 + div;
						if (!visited[nextValue]) {
							visited[nextValue] = true;
							q.offer(new Data(nextValue, cmd + "R"));
						}
					}
				}
			}
		}
		
		System.out.print(sb);
	}

}