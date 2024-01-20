import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 크레인 대수
		
		int[] crane = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine()); // 박스의 수
		
		int[] box = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(crane);
		Arrays.sort(box);
		
		// 모든 박스를 배로 옮길 수 없는 경우
		if (crane[N - 1] < box[M - 1]) {
			System.out.println(-1);
			return;
		}
		
		boolean[] visited = new boolean[M];
		int answer = 0;
		int count = 0;
		
		while (count < M) {
			
			int i = N - 1;
			for (int j = M - 1; i >= 0 && j >= 0; j--) {
				if (!visited[j] && crane[i] >= box[j]) {
					visited[j] = true;
					i--;
					count++;
				}
			}
			
			answer++;
		}
		
		System.out.println(answer);
		
	}

}