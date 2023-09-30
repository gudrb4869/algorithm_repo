import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * nCn/2 조합으로 완전탐색하여 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, S[][], answer = 987654321;
	static boolean isStart[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		N = Integer.parseInt(br.readLine()); // 첫째 줄
		
		S = new int[N][N];
		isStart = new boolean[N]; // 스타트팀으로 뽑았는지 여부
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken()); // 능력치 저장
			}
		}
		
		combination(0, 0); // 조합
		
		System.out.println(answer); // 결과 출력
	}

	static void combination(int cnt, int start) {
		
		if (cnt == N / 2) { // 기저 조건
			int sumStart = 0, sumLink = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (isStart[i] && isStart[j]) {
						sumStart += S[i][j] + S[j][i]; // 스타트팀
					} else if (!isStart[i] && !isStart[j]) {
						sumLink += S[i][j] + S[j][i]; // 링크팀
					}
				}
			}
			answer = Math.min(answer, Math.abs(sumStart - sumLink)); // 최솟값으로 갱신
			return;
		}
		
		for (int i = start; i < N; i++) {
			isStart[i] = true;
			combination(cnt + 1, i + 1);
			isStart[i] = false;
		}
	}
	
	
}