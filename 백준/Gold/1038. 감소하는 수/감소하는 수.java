import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 감소하는수의 최대값은 9_876_543_210이다.
 * 따라서 0부터 9_876_543_210까지만 검사하면 되는데 10*10 2차원배열을 만들어서 검사하면될거같다.
 * 10^i    0   1   2   3   4   5   6   7   8   9  <- 가장큰자릿수의숫자
 * ----------------------
 *   0 |   1   1   1   1   1   1   1   1   1   1
 *   1 |   0   1   2   3   4   5   6   7   8   9
 *   2 |   0   0   1   3   6  10  15  21  28  36
 *   3 |   0   0   0   1   4  10  20  35  56  84
 *   4 |   0   0   0   0   1   5  15  35  70 126
 *   5 |   0   0   0   0   0   1   6  21  56 126
 *   6 |   0   0   0   0   0   0   1   7  28  84
 *   7 |   0   0   0   0   0   0   0   1   8  36
 *   8 |   0   0   0   0   0   0   0   0   1   9
 *   9 |   0   0   0   0   0   0   0   0   0   1
 * 
 * 각자릿수별로 감소하는수의 개수인데
 * 감소하는수는 총 1023개다.
 * 점화식은 dp[0][j] = 1
 * dp[i][j] = dp[i][j-1] + dp[i-1][j-1]
 * 
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N번째 감소하는수는?

		if (N >= (1<<10)-1) { // 0~9,876,543,210 까지 감소하는수의 총개수는 1023개이므로 1023번째감소하는수부턴 없다
			System.out.println(-1);
			return;
		}
		
		List<String>[][] num = new List[10][10]; // 점화식을 통해 감소하는수 만들기 위한 2차원 리스트
		List<String> list = new ArrayList<>(); // 감소하는수들을 저장할 리스트
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				num[i][j] = new ArrayList<>(); // 각요소에 리스트 초기화
			}
		}
		
		for (int j = 0; j < 10; j++) {
			num[0][j].add(String.valueOf(j)); // 초기값 세팅
			list.add(String.valueOf(j));
		}
		
		// (가장큰자릿수의 수가j인 10^i의 수들중 감소하는 수 생성)
		// i,j 위치의 감소하는 수를 만들기 위해선 i-1,0부터 i-1,j-1위치까지의 수들의 앞에 j를 붙이면 감소하는수가된다.
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < j; k++) {
					for (int l = 0; l < num[i-1][k].size(); l++) {
						StringBuilder reduceNum = new StringBuilder();
						reduceNum.append(j).append(num[i-1][k].get(l));
						num[i][j].add(reduceNum.toString());
						list.add(reduceNum.toString());
					}
				}
			}
		}
		
		// N번째 감소하는 수 출력
		System.out.println(list.get(N));
	}
	
}
