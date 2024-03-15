import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
	
		for (int i = 0; i < N; i++) {
			int up = 1, down = 0, prev = A[i][0];
			boolean isDown = false, isPossible = true;
			
			for (int j = 1; j < N; j++) {
				int cur = A[i][j];
				
				if (prev == cur) {
					if (isDown) {
						if (++down == L) {
							isDown = false;
							down = 0;
						}
					} else
						up++;

				} else if (cur - prev == 1) {
					if (isDown || up < L) {
						isPossible = false;
						break;
					}
					up = 1;
					
				} else if (cur - prev == -1) {
					if (isDown && down < L) {
						isPossible = false;
						break;
					}
					up = 0;
					if (L > 1) {
						isDown = true;
						down = 1;
					}
				} else {
					isPossible = false;
					break;
				}
				prev = cur;
			}
			
			if (isDown && down < L)
				isPossible = false;
			
			if (isPossible)
				answer++;
		}
		
		for (int j = 0; j < N; j++) {
			int up = 1, down = 0, prev = A[0][j];
			boolean isDown = false, isPossible = true;
			
			for (int i = 1; i < N; i++) {
				int cur = A[i][j];
				
				if (prev == cur) {
					if (isDown) {
						if (++down == L) {
							isDown = false;
							down = 0;
						}
					} else
						up++;

				} else if (cur - prev == 1) {
					if (isDown || up < L) {
						isPossible = false;
						break;
					}
					up = 1;
					
				} else if (cur - prev == -1) {
					if (isDown && down < L) {
						isPossible = false;
						break;
					}
					up = 0;
					if (L > 1) {
						isDown = true;
						down = 1;
					}
				} else {
					isPossible = false;
					break;
				}
				prev = cur;
			}
			
			if (isDown && down < L)
				isPossible = false;
			
			if (isPossible)
				answer++;
		}
		
		System.out.println(answer);
	}

}