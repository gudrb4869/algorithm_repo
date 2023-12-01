import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정렬
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		
		int[] arr = new int[10];
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			sb.append(arr[7]).append("\n");
		}
		
		System.out.print(sb);
	}

}