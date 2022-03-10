# java blackjack

블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

## 기능 구현 목록

### 플레이어 등록
- [x] 게임에 참여할 사람의 이름을 입력받는다.
  - [예외처리] 이름은 빈 값을 입력하면 안된다.
  - [예외처리] 이름은 null 을 입력하면 안된다.
  - [예외처리] 이름은 중복되면 안된다.
  - [예외처리] 이름은 `딜러`와 같으면 안된다.
  - [예외처리] 게임에 참여하는 사람은 딜러 포함 26명을 넘길 수 없다.

### 카드 뽑기
- [x] 게임에 참여할 사람의 목록을 입력 받으면 모든 종류의 카드를 생성한다.
- [x] 생성한 카드를 랜덤으로 섞는다.
- [x] 카드를 나누어줄 때에는 랜덤으로 섞은 카드를 뒤에서부터 하나씩 준다.
- [x] 딜러와 참가자들에게 각 2장의 카드를 나눠준다.
- [x] 참가자들 순서대로 카드를 더 뽑을지 확인한다.
- [x] 현재 참가자의 숫자 총 합이 21 아래이며 카드 여분이 있을 경우에 뽑을지 확인한다.
- [x] `y` 를 입력할 경우 카드를 하나 추가한다.
- [x] `n` 을 입력할 경우 다음 참가자로 넘어간다.
  - [예외처리] 이름은 빈 값을 입력하면 안된다.
  - [예외처리] 이름은 null 을 입력하면 안된다.
  - [예외처리] `y`, `n` 이외의 값을 입력하면 안된다.
- [x] 모든 참가자를 확인한 후 딜러의 총 합이 `17` 점을 넘길 때 까지 계속해서 카드를 추가한다.

### 승패 결정
- [ ] 딜러의 턴이 종료되면 가지고 있는 모든 카드와 총 합을 오픈한다.
- [x] 최종 승패를 결정한다.

> 입력 예외 발생 시 `Illegalargumentexception` 을 발생시킨다.

### 실행 결과
  ```
    게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
    pobi,jason
  
    딜러와 pobi, jason에게 2장의 나누었습니다.
    딜러: 3다이아몬드
    pobi카드: 2하트, 8스페이드
    jason카드: 7클로버, K스페이드
  
    pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    y
    pobi카드: 2하트, 8스페이드, A클로버
    pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    n
    jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    n
    jason카드: 7클로버, K스페이드
  
    딜러는 16이하라 한장의 카드를 더 받았습니다.
  
    딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
    pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
    jason카드: 7클로버, K스페이드 - 결과: 17
  
    ## 최종 승패
    딜러: 1승 1패
    pobi: 승
    jason: 패
  ```