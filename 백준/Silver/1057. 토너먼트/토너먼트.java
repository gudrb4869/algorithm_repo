import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N명이 참가하는 토너먼트에서 순서대로 번호를 배정받을때
 * 김지민과 임한수가 몇라운드에서 만나는지 계산
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 참가자의 수 (2 ~ 100000)
		int A = Integer.parseInt(st.nextToken()) - 1; // 김지민의 번호
		int B = Integer.parseInt(st.nextToken()) - 1; // 임한수의 번호
		
		int cnt = 0;
		while (A != B) {
			A /= 2;
			B /= 2;
			cnt++;
		}
		System.out.println(cnt);
	}

}