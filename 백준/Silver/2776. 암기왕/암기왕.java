import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * 집합
 * 2023-11-30(목)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
		
		for (int t = 0; t < T; t++) {
			
			Set<Integer> s = new HashSet<>();
			int N = Integer.parseInt(br.readLine()); // 수첩 1에 적어 놓은 정수의 개수(1~1000000)
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				s.add(Integer.parseInt(st.nextToken()));
			}
			
			
			int M = Integer.parseInt(br.readLine()); // 수첩 2에 적어 놓은 정수의 개수(1~1000000)
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < M; i++) {
				int target = Integer.parseInt(st.nextToken());
				sb.append(s.contains(target) ? "1\n" : "0\n");
			}
		}
		System.out.print(sb);
	}

}