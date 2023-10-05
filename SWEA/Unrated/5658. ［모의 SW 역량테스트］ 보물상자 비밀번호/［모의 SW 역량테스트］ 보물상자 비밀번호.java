import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력스트림
		
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 숫자의 개수 
			int K = Integer.parseInt(st.nextToken()); // 크기 순서
			
			TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer a, Integer b) {
					return b - a;
				}
			});
			
			String str = br.readLine();
			str = str.concat(str.substring(0, N / 4 - 1));
			
			for (int i = 0; i < N; i++) {
				set.add(strToInt(str.substring(i, i + N / 4)));
			}
			
			for (int i = 0; i < K - 1; i++) {
				set.pollFirst();
			}
			
			sb.append("#").append(t).append(" ").append(set.first()).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int strToInt(String str) {
		int value = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			value *= 16;
			if (c >= '0' && c <= '9') {
				value += c - '0';
			} else if (c >= 'A' && c <= 'F') {
				value += 10 + c -'A';
			}
		}
		return value;
	}

}