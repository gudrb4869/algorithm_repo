import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 분할정복을 이용해 문제를 풀었다.
 * N이 최대 15이므로 2^N * 2^N 배열 만들시 메모리가 초과하게 된다.
 * 따라서 배열을 생성하지 않고 문제를 풀엇다
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N, r, c, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		makeArr(0, 0, 0, 1 << N);
		System.out.println(answer); // r행 c열 몇번째 방문인지 출력
	}

	/**
	 * 
	 * @param x 현재 보고있는 범위에서 왼쪽상단 행
	 * @param y 현재 보고있는 범위에서 왼쪽상단 열
	 * @param start 값을 넣기 시작할때 시작할 값
	 * @param size 현재 살펴보고 있는 배열의 가로세로 길이
	 */
	private static void makeArr(int x, int y, int start, int size) {
		
		if (r < x || r >= x + size || c < y || c >= c + size) { // 가지치기
			return;
		}
		
		if (r == x && c == y) { // 기저조건
			answer = start;
			return;
		} 
		
		int half = size/2; // 4분할로 하기 때문에 가로세로길이 반으로 줄어듬
		int quarter = size * size / 4; // 4분할마다 size*size/4만큼 시작점이 증가함
		makeArr(x, y, start, half); // 1구역(좌측상단)
		makeArr(x, y+half, start + quarter, half); // 2구역(우측상단)
		makeArr(x+half, y, start + quarter * 2, half); // 3구역(좌측하단)
		makeArr(x+half, y+half, start + quarter * 3, half); // 4구역(우측하단)
		
	}

}
