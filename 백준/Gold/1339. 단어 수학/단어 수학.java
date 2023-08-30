import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * N개의 단어, 각 단어는 알파벳 대문자로만 이루어져 있음
 * 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제
 * 같은 알파벳은 같은 숫자로 바꿔야 함
 * 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안댐
 * 
 * 그리디 알고리즘으로 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[26];
		
		Set<Integer> s = new HashSet<>(); // 모든 단어에 포함되어 있는 알파벳의 개수
		for (int i = 0; i < N; i++) { // N개의 단어에 대해 탐색
			char[] word = br.readLine().toCharArray(); // i번째 단어
			for (int j = 0; j < word.length; j++) { // i번째 단어의 각 문자 탐색
				s.add(word[j] - 'A'); // 집합에 추가
				arr[word[j] - 'A'] += (int)Math.pow(10, word.length - j - 1); // 해당 알파벳의 인덱스에 10의 제곱승만큼 누적
			}
		}
		
		Arrays.sort(arr); // 오름차순 정렬
		
		int sum = 0; // 주어진 단어의 합
		int num = 9; // 현재 바꿀 수
		for (int i = 25; i > 25 - s.size(); i--) { // 끝인덱스부터 집합의 개수만큼 반복
			sum += arr[i] * num--; // 현재 값에 현재 바꿀 숫자만큼 더해서 누적. 현재 바꿀 수 1감소시킴
		}
		
		System.out.println(sum); // 결과 출력
	}
}