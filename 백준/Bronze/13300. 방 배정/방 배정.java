import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 한방에는 남학생은 남학생끼리, 여학생은 여학생끼리, 같은학년끼리 배정해야함 한방에 한명만 배정하는 것도 가능
 * 모든 학생을 배정하기 위해 필요한 방의 최소 개수는?
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 학생 수
		int k = Integer.parseInt(st.nextToken()); // 한방에 배정할 수 있는 최대 인원수
		
		int[][] student = new int[2][7]; // 성별과 학년에 따라 인원수 저장
		
		for (int i = 0; i < n; i++) { // n명의 학생 각각의 성별, 학생의정보
			st = new StringTokenizer(br.readLine());
			
			int s  = Integer.parseInt(st.nextToken()); // 성별 0=여,1=남
			int y = Integer.parseInt(st.nextToken()); // 학년
			
			student[s][y]++;
		}
		
		int answer = 0;
		for (int i = 0; i < 2; i++) { // 성별
			for (int j = 1; j <= 6; j++) { // 학년
				if (student[i][j] > 0) {
					answer += (student[i][j] - 1) / k + 1;
				}
			}
		}
		System.out.println(answer);
		
	}

}