import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 거스름돈 C (1 ~500)이 주어질때
 * 25, 10, 5, 1 동전을 가지고 최소 동전의 개수로 거슬러주는 문제
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
			int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int C = Integer.parseInt(br.readLine());
			
			int quarter = 0;
			int dime = 0;
			int nickel = 0;
			int penny = 0;
			
			while (C >= 25) {
				C -= 25;
				quarter++;
			}
			
			while (C >= 10) {
				C -= 10;
				dime++;
			}
			
			while (C >= 5) {
				C -= 5;
				nickel++;
			}
			
			while (C >= 1) {
				C -= 1;
				penny++;
			}
			
			sb.append(quarter).append(" ").append(dime).append(" ").append(nickel).append(" ").append(penny).append("\n");
		}
		
		System.out.print(sb);
	}

}