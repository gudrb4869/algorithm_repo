import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 2024-01-01(월)
 * </pre>
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		
		String s = Integer.toBinaryString(X);
		
		int answer = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') answer++;
		}
		
		System.out.println(answer);
		
	}

}