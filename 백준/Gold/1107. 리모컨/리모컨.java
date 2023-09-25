import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 박형규
 *
 */
public class Main {

	static int[] dp = new int[1000001];
	static boolean[] buttons = new boolean[10]; // 숫자버튼0~9 사용가능 여부
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 수빈이가 이동하려고 하는 채널(0~500000)
		int M = sc.nextInt(); // 고장난 버튼의 개수(0~10)
		
		Arrays.fill(dp, 987654321);
		Arrays.fill(buttons, true);
		dp[100] = 0;
		for (int i = 0; i < M; i++) {
			buttons[sc.nextInt()] = false;
		}
		
		if (N == 100) {
			System.out.println(0);
			return;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});
		pq.offer(new int[] {0, 100});
		
		for (int i = 0; i <= 1000000; i++) {
			if (isPossible(i) && i != 100) {
				dp[i] = getCount(i);
				pq.offer(new int[] {getCount(i), i});
			}
		}
		
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int cnt = info[0], cur = info[1];
			
			if (cur == N) {
				break;
			}
			
			if (dp[cur] < cnt) {
				continue;
			}
			
			if (cur - 1 >= 0 && dp[cur - 1] > dp[cur] + 1) {
				dp[cur - 1] = dp[cur] + 1;
				pq.offer(new int[] {dp[cur - 1], cur - 1});
			}
			if (cur + 1 <= 500000 && dp[cur + 1] > dp[cur] + 1) {
				dp[cur + 1] = dp[cur] + 1;
				pq.offer(new int[] {dp[cur + 1], cur + 1});
			}
		}
		
		System.out.println(dp[N]);
	}

	private static int getCount(int num) {
		int cnt = 0;
		do {
			cnt++;
			num /= 10;
		} while (num > 0);
		return cnt;
	}

	private static boolean isPossible(int num) {
		do {
			if (!buttons[num % 10]) return false;
			num /= 10;
		} while (num > 0);
		return true;
	}

}