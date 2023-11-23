import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <pre>
 * 문자열, 정렬문제
 * 2023-11-23(목)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		String S = br.readLine(); // 문자열 S
		
		int N = S.length();
		
		String[] arr = new String[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = S.substring(i);
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append("\n");
		}
		
		System.out.print(sb);
	}

}