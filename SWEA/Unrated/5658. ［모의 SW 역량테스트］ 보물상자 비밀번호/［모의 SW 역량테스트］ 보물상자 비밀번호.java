import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * <pre>
 * 인덱스 0부터 N/4 - 1까지 부분문자열을 기존문자열 뒤에 추가삽입함.
 * 0부터 N-1까지 시작인덱스를 기준으로 N/4길이만큼의 부분문자열을 숫자로 전환한뒤에 트리셋에 저장(중복된 값을 거른 후, 내림차순으로 저장)
 * K-1번만큼 pollFirst해줘서 1 ~ K-1번째 큰값들을 없앰
 * first로 K번째 값 추출후 결과 출력
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
			}); // 트리셋 정렬조건 선언 
			
			String str = br.readLine(); // 16진수 숫자가 공백없이 주어짐
			str = str.concat(str.substring(0, N / 4 - 1)); // 0부터 N/4-1까지의 부분문자열을 기존문자열 뒤에 삽입후 기존문자열을 바꿔치기함
			
			for (int i = 0; i < N; i++) {
				set.add(strToInt(str.substring(i, i + N / 4))); // 생성가능한 수들을 트리셋에 저장
			}
			
			for (int i = 0; i < K - 1; i++) {
				set.pollFirst(); // 1 ~ K-1번째 수들을 버림
			}
			
			sb.append("#").append(t).append(" ").append(set.first()).append("\n"); // K번째로 큰수 출력
		}
		
		System.out.print(sb);
	}

	private static int strToInt(String str) {
		int value = 0; // 문자열 -> 숫자로 변환한 결과값
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i); // i번째 문자
			value *= 16; // 기존 값 한자리씩 올려줌
			if (c >= '0' && c <= '9') { // 0~9인경우
				value += c - '0';
			} else if (c >= 'A' && c <= 'F') { // A~F인 경우
				value += 10 + c -'A';
			}
		}
		return value;
	}

}