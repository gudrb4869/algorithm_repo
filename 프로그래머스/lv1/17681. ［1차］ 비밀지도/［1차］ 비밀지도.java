/**
@author 박형규
<pre>
https://school.programmers.co.kr/learn/courses/30/lessons/17681
비밀지도
n*n 크기로 된 지도1과 지도2가 있는데 지도는 공백(" ")과 벽("#")으로 이루어져 있음
지도1과 지도2를 매핑시켰을때 어느하나라도 벽인경우 전체지도에서도 벽이고 둘다 공백이어야 전체지도에서도 공백임
arr1[i]과 arr2[i]을 or 연산하고 2진수로 변환함.
근데 n이 1이상 16이하이므로 2진수의 길이가 16자리까지 나올수 있으므로 Integer말고 Long으로 변환.
그다음 n자리수 맞춰서 앞에 0으로 채워줌
마지막으로 "1"은 "#"로 "0"은 " "로 바꿔주고 문자열 배열에 저장

다른풀이보니까 String.format 메서드에서 인자값 2진수를 굳이 정수로 안바꾸고, 포맷을 %s 문자열로 받아서 풀었다.
숫자로 받을땐 자리수에 맞춰서 앞에 0채울수 있고, 문자열로 받을땐 앞에 공백으로 채울 수 있음
</pre>
*/
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n]; 
        
        for (int i = 0; i < n; i++) {
            String str = String.format("%0" + n + "d", Long.parseLong(Integer.toBinaryString(arr1[i] | arr2[i]))).replace("1", "#").replace("0", " ");
            
            answer[i] = str;
        }
        
        return answer;
    }
}