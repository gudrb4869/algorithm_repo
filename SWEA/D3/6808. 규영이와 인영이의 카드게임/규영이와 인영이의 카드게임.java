import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 인영이가 가진 카드 9개의 숫자를 일단 저장해놓고
 * 9! 모든 경우의수를 구하고 값들비교하면서 승패횟수 카운팅햇다
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	static int[] gCard = new int[9]; // 규영이가 낼 카드의 고정된 순서
	static int[] cards = new int[9]; // 인영이가 낼수 있는 카드
	static int[] iCard = new int[9]; // 인영이가 낼 카드의 순서
	static int win, lose; // 규영이가 게임을 이기는 경우의 수, 게임을 지는 경우의 수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) { // 케이스마다 반복
			
			boolean[] card = new boolean[19]; // 규영이카드=true, 인영이카드false
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) { // 규영이가 받은 9장의 카드에 적힌 수
				gCard[i] = Integer.parseInt(st.nextToken());
				card[gCard[i]] = true; // 규영이가 가진 카드 true로세팅
			}
			
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!card[i]) {
					cards[idx++] = i;
				}
			}
			
			win = lose = 0;
			permutation(0, 0);
			
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
			
		}
		System.out.print(sb);
	}

	private static void permutation(int cnt, int flag) {
		
		if (cnt == 9) {
			int gScore = 0, iScore = 0; // 규영이점수, 인영이점수
			for (int i = 0; i < 9; i++) {
				if (gCard[i] < iCard[i]) {
					iScore += iCard[i] + gCard[i];
				} else if (gCard[i] > iCard[i]) {
					gScore += iCard[i] + gCard[i];
				}
			}
			if (gScore > iScore) {
				win++;
			} else if (gScore < iScore) {
				lose++;
			}
			
			return;
		}

		for (int i = 0; i < 9; i++) {
			if ((flag & 1 << i) != 0)
				continue; // 이미 고른 카드면 skip
			
			iCard[cnt] = cards[i];
			permutation(cnt+1, flag|(1<<i));
		}
		
	}

}
