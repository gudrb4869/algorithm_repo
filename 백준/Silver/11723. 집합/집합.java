import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		StringTokenizer st; // 공백 기준으로 분리
		
		int M = Integer.parseInt(br.readLine()); // 수행해야 하는 연산의 수(1~3000000)
		Set<Integer> s = new HashSet<>(); // 비어있는 공집합 S
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			if (cmd.equals("add")) { // add x: S에 x추가
				int x = Integer.parseInt(st.nextToken());
				s.add(x);
			} else if (cmd.equals("remove")) { // remove x: S에서 x제거
				int x = Integer.parseInt(st.nextToken());
				s.remove(x);
			} else if (cmd.equals("check")) { // check x: S에 x가 있으면 1, 없으면 0 출력
				int x = Integer.parseInt(st.nextToken());
				sb.append(s.contains(x) ? 1 : 0).append("\n");
			} else if (cmd.equals("toggle")) { // toggle x: S에 x가 있으면 제거, 없으면 추가
				int x = Integer.parseInt(st.nextToken());
				if (s.contains(x)) {
					s.remove(x);
				} else {
					s.add(x);
				}
			} else if (cmd.equals("all")) { // S를 {1,2,...,20} 으로 바꿈
				for (int j = 1; j <= 20; j++) {
					s.add(j);
				}
			} else if (cmd.equals("empty")) { // S를 공집합으로 바꿈
				s.clear();
			}
		}
		
		System.out.print(sb); // 결과 출력
	}

}