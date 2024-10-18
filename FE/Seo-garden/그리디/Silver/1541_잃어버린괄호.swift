let input = readLine()!.split(separator: "-")
var answer = 0


for i in input[0].split(separator: "+") {       // - 가 나타나기 전까지 더한다.
    answer += Int(i)!
}

for i in input[1...] {                          // - 가 나타난다면
    for j in i.split(separator: "+") {
        answer -= Int(j)!
    }
}

print(answer)
