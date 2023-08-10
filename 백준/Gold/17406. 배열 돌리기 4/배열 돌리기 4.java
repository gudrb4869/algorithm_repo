import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * K개의 회전 연산의 순서에 따라 N*M크기의 배열A의 값의 최솟값이 달라지므로 순열을 이용한다.
 * 또한 r,c를 기준으로 s범위내에 있는 칸들을 시계방향으로 회전시키는 경우의 수중에 최소값 찾음
 * </pre>
 * @author 박형규
 */
public class Main {

	private static int n,m, k, arr[][], rotationInfo[][], answer = 99999;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 배열 A의 행
		m = Integer.parseInt(st.nextToken()); // 배열 A의 열
		k = Integer.parseInt(st.nextToken()); // 회전 연산의 개수
		
		arr = new int[n][m]; // N*M크기의 배열 A 초기화
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 배열 A에 들어있는 수들 저장
			}
		}
		
		rotationInfo = new int[k][4]; // 회전 연산의 정보 r,c,s,index 를 저장할 배열
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			rotationInfo[i][0] = Integer.parseInt(st.nextToken());
			rotationInfo[i][1] = Integer.parseInt(st.nextToken());
			rotationInfo[i][2] = Integer.parseInt(st.nextToken());
			rotationInfo[i][3] = i; // 회전 연산정보를 nextpermutation 하기위해 인덱스도 같이저장
		}
		
		do {
			rotate(); // 회전 연산 수행 후 배열 A의 최솟값 찾아서 갱신
		} while (np()); // 회전연산의정보 nextPermutation
		
		System.out.println(answer);
	}

	private static boolean np() {
		
		int i = k - 1;
		
		while(i>0 && rotationInfo[i-1][3] >= rotationInfo[i][3]) {
			i--;
		}
		
		if (i==0) {
			return false;
		}
		
		int j = k - 1;
		while (rotationInfo[i-1][3] >= rotationInfo[j][3]) {
			j--;
		}
		
		swap(i-1, j);
		
		int l = k - 1;
		while (i<l) {
			swap(i++, l--);
		}
		
		return true;
	}

	private static void swap(int i, int j) {
		int[] temp = rotationInfo[i];
		rotationInfo[i] = rotationInfo[j];
		rotationInfo[j] = temp;
	}

	private static void rotate() {
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = arr[i][j];
			}
		}
		
		for (int x = 0; x < k; x++) {
			int r = rotationInfo[x][0] - 1;
			int c = rotationInfo[x][1] - 1;
			int s = rotationInfo[x][2];
			
			int minR = r-s, maxR = r+s; // 초기값 세팅
			int minC = c-s, maxC = c+s; // 초기값 세팅
			while(minR<maxR && minC<maxC) {
				int temp = a[minR][minC]; // 시계로 돌려야하는데 이럴경우 한개의 값이 덮어씌워지므로 임시변수에 값 저장해놓음
				for (int i = minR; i < maxR; i++) { // 좌측
					a[i][minC] = a[i + 1][minC];
				}
				for (int j = minC; j < maxC; j++) { // 하단
					a[maxR][j] = a[maxR][j + 1];
				}
				for (int i = maxR; i > minR; i--) { // 우측
					a[i][maxC] = a[i - 1][maxC];
				}
				for (int j = maxC; j > minC; j--) { // 상단
					a[minR][j] = a[minR][j - 1];
				}
				a[minR][minC + 1] = temp;
				
				// 다음 둘레를 회전시키기 위해 시작/끝값 조정
				minR++;
				maxR--;
				minC++;
				maxC--;
			}
		}
		
		
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < m; j++) {
				sum += a[i][j];
			}
			answer = Math.min(answer, sum);
		}
	}

}
