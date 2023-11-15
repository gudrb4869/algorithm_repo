import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 브루트포스 알고리즘
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int E = Integer.parseInt(st.nextToken()) - 1; // 지구를 나타내는 수 (1~15)
		int S = Integer.parseInt(st.nextToken()) - 1; // 태양을 나타내는 수 (1~28)
		int M = Integer.parseInt(st.nextToken()) - 1; // 달을 나타내는 수 (1~19)
		
		int e = 0, m = 0, s = 0, count = 0;

		while (!(e == E && m == M && s == S)) {
			e = (e + 1) % 15;
			m = (m + 1) % 19;
			s = (s + 1) % 28;
			count++;
		}
		System.out.println(count + 1);
	}

}