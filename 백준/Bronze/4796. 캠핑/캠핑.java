import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 그리디 알고리즘
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken()); // 
			int V = Integer.parseInt(st.nextToken()); // 강산이의 휴가 일수
			
			if (L == 0 && P == 0 && V == 0) {
				break;
			}
			
			int div = V / P, mod = V % P;
			int answer = L * div;
			answer += L <= mod ? L : mod;
			
			sb.append("Case ").append(T++).append(": ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}