import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, eggs[][], answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		eggs = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			eggs[i][0] = Integer.parseInt(st.nextToken()); // 내구도
			eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
		}
		
		dfs(0, 0);
		
		System.out.println(answer);
	}

	static void dfs(int cur, int cnt) {
		
		if (cur == N) { // 가장 최근에 든 계란이 가장 오른쪽 계란인 경우
			answer = Math.max(answer, cnt);
			return;
		}
		
		if (eggs[cur][0] <= 0) { // 현재든 계란이 이미 깨진 계란인 경우 내려놓고 오른쪽 한칸 계란으로 넘어감
			dfs(cur + 1, cnt);
			return;
		}
		
		int w1 = eggs[cur][1];
		
		for (int next = 0; next < N; next++) {
			if (cur == next || eggs[next][0] <= 0) continue;
			
			int w2 = eggs[next][1];
			
			eggs[cur][0] -= w2;
			eggs[next][0] -= w1;
			if (eggs[cur][0] <= 0 && eggs[next][0] <= 0) { // 계란 두개가 깨진 경우
				dfs(cur + 1, cnt + 2);
			} else if (eggs[cur][0] > 0 && eggs[next][0] > 0) { // 계란 두개다 안깨진 경우
				dfs(cur + 1, cnt);
			} else { // 계란 한개만 깨진 경우
				dfs(cur + 1, cnt + 1);
			}
			eggs[next][0] += w1;
			eggs[cur][0] += w2;
		}
		
		if (cur == 0 && cnt == 0) { // 가장 왼쪽 계란인 경우
			return;
		}
		
		dfs(cur + 1, cnt); // 가장 왼쪽 계란이 아닌 경우 현재계란으로 다른계란치지 않고 넘어가는 경우까지 고려해줌
	}

}