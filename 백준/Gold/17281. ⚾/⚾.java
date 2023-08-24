import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 순열, 백트래킹 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, innings[][], answer; // 아인타팀이 얻을 수 있는 최대 점수를 저장할 변수
	static int[] numbers = new int[9]; // 순열
	static boolean[] checked = new boolean[10]; // 선택여부
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 이닝 수(2~50)
		
		innings = new int[N + 1][10]; // 1번타자부터 9번 타자들이 각이닝에서 얻는 결과를 저장할 2차원배열
		for (int i = 1; i <= N; i++) { // 각 이닝에서 타자들이 얻는 결과 저장 (1이닝 ~ N이닝)
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) { // (1번타자 ~ 9번타자)
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0); // 완전 탐색
		
		System.out.println(answer);
	}
	private static void dfs(int cnt) {
		
		if (cnt == 4 && numbers[3] != 1) { // 1번선수가 4번타자가 아닌경우 가지치기
			return;
		}
		
		if (cnt == 9) { // 기저 조건
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < 9; i++) {
				q.offer(numbers[i]);
			}
			
			int inning = 1;
			int point = 0;
			while (inning <= N) {
				
				int out = 0;
				boolean[] base = new boolean[3]; // 1루(0), 2루(1), 3루(2)에 주자 존재여부
				while (true) {
					int cur = q.poll();
					q.offer(cur);
					
					int result = innings[inning][cur];
					if (result == 0) { // 아웃
						out++;
					} else { // 안타~홈런
						for (int i = 2; i >= 0; i--) { // 3루주자부터 2루, 1루주자들 존재여부 검사
							if (base[i]) {
								base[i] = false;
								if (i + result >= 3) {
									point++;
								} else {
									base[i + result] = true;
								}
							}
						}
						if (result == 4) {
							point++;
						} else {
							base[result - 1] = true;
						}
					}
					if (out == 3) {
						break;
					}
				}
				inning++;
			}
			answer = Math.max(answer, point); // 최대 점수로 갱신
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (checked[i]) continue;
			
			numbers[cnt] = i;
			checked[i] = true;
			dfs(cnt + 1);
			checked[i] = false;
		}
	}

}