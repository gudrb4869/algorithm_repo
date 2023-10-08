import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * N*N 크기의 1차원 배열에 값들을 저장한 다음 정렬시킨다.
 * 인덱스로 접근하여 N번째 큰 수 출력 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 첫째 줄 입력 N(1~1500)
		
		int[] arr = new int[N * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i * N + j] = Integer.parseInt(st.nextToken());
			}
		}
		Arrays.sort(arr);
		System.out.println(arr[N * N - N]);
	}

}