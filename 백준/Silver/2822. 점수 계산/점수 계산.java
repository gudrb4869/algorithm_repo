import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 점수 높은순, 점수같다면 문제번호 낮은순으로 정렬되는 힙
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (b[0] == a[0]) return a[1] - b[1]; // 점수 같다면 문제 번호 낮은순
				return b[0] - a[0]; // 점수 높은순
			}
		});
		
		for (int i = 0; i < 8; i++) {
			pq.offer(new int[] {Integer.parseInt(br.readLine()), i + 1});
		}
		
		int sum = 0;
		int[] result = new int[5];
		int idx = 0;
		
		for (int i = 0; i < 5; i++) {
			int[] info = pq.poll();
			int score = info[0], number = info[1];
			sum += score;
			result[idx++] = number;
		}
		
		Arrays.sort(result);
		
		System.out.println(sum);
		for (int num : result) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}