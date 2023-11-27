import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 맵
 * 정렬
 * 2023-11-27(월)
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine()); // 오늘 하루 동안 팔린 책의 개수
		
		for (int i = 0; i < N; i++) {
			String subject = br.readLine();
			
			map.put(subject, map.getOrDefault(subject, 0) + 1);
		}
		
		List<String> keySet = new ArrayList<>(map.keySet());
		
		keySet.sort(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if (map.get(b) == map.get(a)) {
					return a.compareTo(b);
				}
				return map.get(b).compareTo(map.get(a));
			}
		});
		
		System.out.println(keySet.get(0));
		
	}

}