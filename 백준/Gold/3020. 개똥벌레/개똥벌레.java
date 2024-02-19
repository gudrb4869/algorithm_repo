import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 동굴의 길이
		int H = Integer.parseInt(st.nextToken()); // 동굴의 높이
		
		long[] stalagmite = new long[H + 1]; // 석순 
		long[] stalactite = new long[H + 1]; // 종유석
		long[] sum = new long[H + 1]; // 누적합 배열

		for (int i = 1; i <= N; i++) {
			int h = Integer.parseInt(br.readLine()); // 장애물 높이
			if (i % 2 == 1) {
				stalagmite[h]++; // 석순
			} else {
				stalactite[h]++; // 종유석
			}
		}
		
		// 누적합 계산 1
		for (int i = H - 1; i >= 1; i--) {
			stalagmite[i] += stalagmite[i + 1];
			stalactite[i] += stalactite[i + 1];
		}
		
		// 누적합 계산 2
		sum[1] = stalagmite[1];
		sum[H] = stalactite[1];
		
		for (int i = 2; i <= H - 1; i++) {
			sum[i] = stalagmite[i] + stalactite[H - i + 1];
		}
		
		// 정렬
		Arrays.sort(sum);
		
		// 이분 탐색
		long min = sum[1];
		int left = 1, right = H;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (sum[mid] <= min) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(sum[1] + " " + right);
	}

}