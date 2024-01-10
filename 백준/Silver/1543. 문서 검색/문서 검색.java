import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] document = br.readLine().toCharArray(); // 문서
		
		char[] word = br.readLine().toCharArray(); // 검색하고 싶은 단어
		
		int N = document.length;
		int M = word.length;
		
		int answer = 0;
		int i = 0;
		while (i <= N - M) {
			boolean check = true;
			for (int j = 0; j < M; j++) {
				if (document[i + j] != word[j]) {
					check = false;
					break;
				}
			}
			if (check) {
				answer++;
				i += M;
			} else {
				i++;
			}
		}
		
		System.out.println(answer);
	}

}