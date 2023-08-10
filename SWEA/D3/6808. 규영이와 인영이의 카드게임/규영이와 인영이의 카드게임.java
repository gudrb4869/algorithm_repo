import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) { // 케이스마다 반복
			
			boolean[] isGyuyoung = new boolean[19]; // 규영이카드=true, 인영이카드false
			int[] gyuyoung = new int[9]; // 규영이가 낼 카드의 고정된 순서
			int[] inyoung = new int[9]; // 인영이가 낼 카드의 순서
			int win=0, lose=0; // 규영이가 게임을 이기는 경우의 수, 게임을 지는 경우의 수
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) { // 규영이가 받은 9장의 카드에 적힌 수
				gyuyoung[i] = Integer.parseInt(st.nextToken());
				isGyuyoung[gyuyoung[i]] = true; // 규영이가 가진 카드 true로세팅
			}
			
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!isGyuyoung[i]) {
					inyoung[idx++] = i;
				}
			}
			
			Arrays.sort(inyoung);
			do {
				int gScore = 0, iScore = 0; // 규영이점수, 인영이점수
				for (int i = 0; i < 9; i++) {
					int score = gyuyoung[i] + inyoung[i];
					if (gyuyoung[i] > inyoung[i]) {
						gScore += score;
					} else if (gyuyoung[i] < inyoung[i]) {
						iScore += score;
					}
				}
				if (gScore > iScore) {
					win++;
				} else if (gScore < iScore) {
					lose++;
				}
				
			} while (nextPermutation(inyoung));			
			
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
			
		}
		System.out.print(sb);
	}

	private static boolean nextPermutation(int[] p) { // p : 다음 순열을 원하는 기존 순열의 배열
		// 맨뒤쪽부터 탐색하며 꼭대기 찾기
		int N = p.length;
		int i = N-1;
		while (i>0 && p[i-1] >= p[i]) {
			i--;
		}
		
		if (i == 0) { // 다음 순열이 없음(가장 큰 순열의 형태)
			return false;
		}

		// 꼭대기 직전(i-1)위치에 교환할 한단계 큰 수 뒤쪽부터 찾기
		int j = N-1;
		while (p[i-1] >= p[j]) {
			j--;
		}
		
		// 꼭대기 직전(i-1) 위치의 수와 한단계 큰 수를 교환하기
		swap(p, i-1, j);
		
		// 꼭대기자리부터 맨뒤까지의 수를 오름차순의 형태로 바꿈
		int k = N-1;
		while (i<k) {
			swap(p, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

}
