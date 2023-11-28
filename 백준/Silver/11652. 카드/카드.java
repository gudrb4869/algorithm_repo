import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 정렬
 * 맵
 * 2023-11-28(화)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 숫자 카드의 개수
		
		Map<Long, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		List<Long> keySet = new ArrayList<>(map.keySet());
		
		
		keySet.sort((a, b) -> {
			if (map.get(a).equals(map.get(b))) {
				return a.compareTo(b);
			}
			return map.get(b).compareTo(map.get(a));
		});
		
		System.out.println(keySet.get(0));
	}

}