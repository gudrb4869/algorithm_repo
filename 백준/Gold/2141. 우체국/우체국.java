import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 마을번호, 마을인구수를 N개의 줄에 걸쳐 입력을 받을때
 * 우선 2차원배열에 정보들을 저장해놓는다.
 * 그러고나서 마을번호를 기준으로 오름차순 정렬을 해주었다.
 * 
 * 그러고나서 순차적으로 마을인구수를 current 변수에 누적하여 더해주는데
 * 누적값이 전체마을인구수의 절반을 넘거나 같아지는지점이 우체국을 세워야하는 곳이 된다.
 * 
 * 이때 전체마을인구수가 홀수인 경우 마을인구수의 절반은 ceil(total/2)이다.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 마을의 개수
		long town[][] = new long[N][2];

		long total = 0; // 수직선상 모든 마을 인구수의 합
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			town[i][0] = Long.parseLong(st.nextToken());
			town[i][1] = Long.parseLong(st.nextToken());
			total += town[i][1]; // 인구수 누적
		}
		
		Arrays.sort(town, new Comparator<long[]>() {
			@Override
			public int compare(long[] a, long[] b) {
				return (int) (a[0] - b[0]);
			}
		});
		
		long current = 0;
		long half = (long) Math.ceil((1.0 * total / 2));
		for (int i = 0; i < N; i++) {
			current += town[i][1];
			if (current >= half) {
				System.out.println(town[i][0]);
				break;
			}
		}
	}

}