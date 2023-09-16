import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * a=1, b=2, ... z=26이라 하고
 * 문자열의 각 항은 r=31을 인덱스만큼 거듭제곱한값을 곲하여 누적한다.
 * 그러고 M=1,234,567,891로 나눈 나머지인 해시값을 구함
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int L = Integer.parseInt(br.readLine()); // 문자열의 길이
		char[] arr = br.readLine().toCharArray(); // 문자열
		
		long hash = 0;
		int M = 1234567891;
		for (int i = 0; i < L; i++) {
			hash += ((arr[i] - 'a' + 1) * (long) Math.pow(31, i)) % M;
		}
		hash %= M;
		System.out.println(hash);
	}

}