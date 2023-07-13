/**
@author 박형규
<pre>
https://school.programmers.co.kr/learn/courses/30/lessons/17681
비밀지도
지도 n*n
공백(" ")과 벽("#")으로 이루어져 있음
지도1과 지도2를 매핑시켰을때 어느하나라도 벽인경우 전체지도에서도 벽이고 둘다 공백이어야 전체지도에서도 공백임
따라서 or 연산 수행
근데 or연산했을때 arr1과 arr2가 정수배열이므로 앞자리가 0일경우 0들이 지워짐
그러므로 길이 n에 맞게 0을 채워줘야됨
그러고나서 "1"은 "#"로 "0"은 " "로 바꿔주고 문자열 배열에 저장
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