import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 각 수가 최대 10억이하의 음이아닌 정수이므로
 * 각 수를 9번반복한 문자열을 아스키 코드를 기준으로 내림차순 정렬하여 다시 조합하면 될거같다.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		int N = Integer.parseInt(br.readLine()); // 수의 개수 (1 ~ 1000)
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 리스트에 포함된 수(공백으로 구분되어 있고, 10억보다 작거나 같은 음이 아닌 정수)
		
		String[] numbers = new String[N]; // 수들을 저장할 N크기의 문자열 배열
		
		for (int i = 0; i < N; i++) {
			numbers[i] = st.nextToken(); // 수 저장
		}
		
		Arrays.sort(numbers, new Comparator<String>() { // 수를 9번반복한 문자열을 아스키로 내림차순 정렬
			@Override
			public int compare(String a, String b) {
				return b.repeat(9).compareTo(a.repeat(9));
			}
		});
		
		for (int i = 0; i < N; i++) { // N개의 수를 스트링빌더에 삽입
			if (sb.length() == 0 && numbers[i].equals("0")) continue; // 스트링빌더가 비어있는데 0이면 continue
			sb.append(numbers[i]); // 수 스트링빌더에 삽입 
		}
		System.out.println(sb.length() > 0 ? sb.toString() : 0); // 결과 출력(스트링빌더가 비어있다면 0출력, 아니면 sb출력)
	}

}