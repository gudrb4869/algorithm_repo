import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 재료의 개수(1~15000)
		int M = Integer.parseInt(br.readLine()); // 값옷을 만드는데 필요한 수(1~10000000)
		
		int[] arr = new int[N]; // N개의 고유 번호 저장할 배열

		StringTokenizer st = new StringTokenizer(br.readLine()); // N개의 재료들이 가진 고유한 번호들이 공백을 사이에 두고 주어짐
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left = 0, right = N - 1, answer = 0;
		while (left < right) {
			if (arr[left] + arr[right] < M) {
				left++;
			} else if (arr[left] + arr[right] > M) {
				right--;
			} else {
				answer++;
				left++;
			}
		}

		System.out.println(answer);
	}

}