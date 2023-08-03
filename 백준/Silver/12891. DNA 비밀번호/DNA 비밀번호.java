import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] a = new int[4];
	static int[] b = new int[4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < P; i++) {
			add(str.charAt(i));
		}
		int s = 0, e = P - 1, count = 0;
		while (true) {
			if (check()) count++;
			
			sub(str.charAt(s++));
			if (++e == S) break;
			add(str.charAt(e));
		}
		System.out.println(count);
	}
	
	public static boolean check() {
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			flag &= b[i] >= a[i];
		}
		return flag;
	}
	
	public static void add(char c) {
		if (c == 'A') {
			b[0] += 1;
		} else if (c == 'C') {
			b[1] += 1;
		} else if (c == 'G') {
			b[2] += 1;
		} else {
			b[3] += 1;
		}
	}
	
	public static void sub(char c) {
		if (c == 'A') {
			b[0] -= 1;
		} else if (c == 'C') {
			b[1] -= 1;
		} else if (c == 'G') {
			b[2] -= 1;
		} else {
			b[3] -= 1;
		}
	}
}