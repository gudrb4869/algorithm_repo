import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * np를 이용해 문제 풀음
 * </pre>
 * @author 박형규
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		
		do {
			for (int i = 0; i < N; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
		} while(nextPermutation(arr));
		
		System.out.print(sb);
	}

	private static boolean nextPermutation(int[] arr) {
		
		int N = arr.length;

		int i = N-1;
		while (i>0 && arr[i-1]>=arr[i]) {
			i--;
		}
		
		if (i==0) {
			return false;
		}
		
		int j = N-1;
		while (arr[i-1]>=arr[j]) {
			j--;
		}
		
		swap(arr, i-1, j);
		
		int k = N-1;
		while (i<k) {
			swap(arr, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}