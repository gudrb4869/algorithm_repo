import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 *     
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //
		StringBuilder sb = new StringBuilder(); // 
		StringTokenizer st;
		
		// ESTJ ESTP ESFJ ESFP
		// ENTJ ENTP ENFJ ENFP
		// ISTJ ISTP ISFJ ISFP
		// INTJ INTP INFJ INFP
		
		int T = Integer.parseInt(br.readLine()); // 
		for (int t = 1; t <= T ; t++) {
			int N = Integer.parseInt(br.readLine()); //
			st = new StringTokenizer(br.readLine());
			
			String[] mbti =  new String[N];
			for (int i = 0; i < N; i++) {
				mbti[i] = st.nextToken();
			}

			if (N > 32) {
				sb.append("0\n");
				continue;
			}

			int answer = 1000;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					for (int k = j + 1; k < N; k++) {
						int dist = 0;
						for (int l = 0; l < 4; l++) {
							if (mbti[i].charAt(l) != mbti[j].charAt(l)) dist++;
							if (mbti[j].charAt(l) != mbti[k].charAt(l)) dist++;
							if (mbti[k].charAt(l) != mbti[i].charAt(l)) dist++;
						}
						answer = Math.min(answer, dist);
					}
				}
			}
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}