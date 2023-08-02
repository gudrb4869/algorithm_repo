def solution(storey):
    answer = 0
    n = storey
    
    while n > 0:
        cur = n % 10
        nxt = (n // 10) % 10
        rem = n // 100
        
        if cur < 5:
            answer += cur
        elif cur > 5:
            answer += 10 - cur
            nxt += 1
        else:
            if nxt < 5:
                answer += cur
            else:
                answer += 10 - cur
                nxt += 1
        
        n = rem * 10 + nxt
        
    return answer