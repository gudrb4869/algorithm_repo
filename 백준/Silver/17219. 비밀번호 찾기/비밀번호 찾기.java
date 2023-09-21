import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄
		
		int N = Integer.parseInt(st.nextToken()); // 저장된 사이트 주소의 수
		int M = Integer.parseInt(st.nextToken()); // 비밀번호를 찾으려는 사이트 주소의 수
		
		Map<String, String> m = new HashMap<>(); // 사이트마다 비밀번호를 저장할 맵 선언
		
		for (int i = 0; i < N; i++) { // N개의 줄에 걸쳐 각 줄에 사이트 주소와 비밀번호가 공백으로 구분되어 주어짐
			st = new StringTokenizer(br.readLine());
			String address = st.nextToken();
			String password = st.nextToken();
			m.put(address, password);
		}
		
		for (int i = 0; i < M; i++) { // M개의 줄에 걸쳐 비밀번호를 찾으려는 사이트 주소가 한줄에 하나씩 입력됨
			sb.append(m.get(br.readLine())).append("\n");
		}
		System.out.print(sb); // 출력
	}

}