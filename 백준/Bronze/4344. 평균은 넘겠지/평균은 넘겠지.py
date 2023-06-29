c = int(input())
for _ in range(c):
    n, *student = map(int, input().split())
    count = 0
    avg = sum(student) / n
    for s in student:
        if avg < s:
            count += 1
    print("%0.3f%%"%round(count * 100 / n, 3))