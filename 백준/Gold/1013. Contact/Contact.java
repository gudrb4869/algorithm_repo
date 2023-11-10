import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * <pre>
 * 정규표현식
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 첫 줄, 테스트 케이스의 개수
		
		String pattern = "(100+1+|01)+";
		
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			sb.append(Pattern.matches(pattern, str) ? "YES\n" : "NO\n");
		}
		
		System.out.print(sb);
	}

}