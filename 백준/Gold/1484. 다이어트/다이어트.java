import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int G = Integer.parseInt(br.readLine());
		
		Set<Long> result = new TreeSet<>();
		
		for (long i = 1; i <= 100000; i++) {
			long weight = (long) Math.sqrt(i * i + G);
			if (weight * weight == i * i + G) {
				result.add(weight);
			}
		}
		
		if (result.isEmpty()) {
			System.out.println(-1);
			return;
		}
		
		for (long w : result) {
			sb.append(w).append("\n");
		}
		
		System.out.print(sb);
	}

}