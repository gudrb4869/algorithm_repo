import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 파이프의 길이가 2이기때문에 우선 파이프 우측하단끝점을 dp테이블에 상태에 저장하려고한다.
 * 근데 가로,대각선,세로방향 3가지 상태가 존재하기 때문에 한차원 더만들어서 3차원 dp테이블을 쓰면 문제를 해결할 수 있다.
 * 
 * 현재파이프우측하단의 좌표를 (i,j)라 하고, 가로방향을 0, 대각선방향을 1, 세로방향을 2라고 하면
 * 
 * 가로방향상태이면서 끝점이 i,j인 파이프로 올수 있는 경우는 dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1] 이고,
 * 
 * 대각방향상태이면서 끝점이 i,j인 파이프로 올수 있는 경우는 dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2] 이고,
 * 
 * 세로방향상태이면서 끝점이 i,j인 파이프로 올수 있는 경우는 dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2] 이다.
 * 
 * 동적 테이블에 저장할 가짓수의 범위가 Integer의 범위를 벗어나기 때문에 long 타입으로 만들어줬음
 * </pre>
 * @author 박형규
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 첫째 줄, 집의 크기 (3 ~ 32)
		
		int[][] home = new int[N + 1][N + 1]; // 좌, 상단에 인덱스 범위 예외처리를 간편하게 하기위한 가상의 벽공간을 포함하려고 N+1길이로 만듬
		for (int j = 0; j < N + 1; j++) {
			home[0][j] = 1; // 상단에 가상의 벽 생성
		}
		
		for (int i = 1; i <= N; i++) {
			home[i][0] = 1; // 매 행마다 왼쪽끝에 가상의 벽 생성
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken()); // 각 칸마다 집의 상태 저장
			}
		}
		
		long[][][] dp = new long[N + 1][N + 1][3]; // 동적테이블 생성
		
		dp[1][2][0] = 1; // 파이프 가장우측,가장아래에있는 좌표기준으로 dp 수행
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if ((i == 1 && j == 2)) continue; // 파이프의 처음 상태일땐 패스
				
				if (home[i][j] == 0) { // 가로방향, 세로방향이동시 끝점이 이동후 있는 칸이 0인지만 확인하면 됨
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
					dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
				}
				
				if (home[i][j] == 0 && home[i - 1][j] == 0 && home[i][j - 1] == 0) { // 대각방향 이동시 끝점뿐만아니라 끝점의좌측과위쪽을 같이확인
					dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]); // 파이프의 한쪽 끝을 (N,N) 으로 이동시키는 방법의 수 출력
	}
}