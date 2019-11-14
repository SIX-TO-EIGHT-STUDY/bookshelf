# the-fastest-core-java-9
[가장 빨리 만나는 코어 자바 9] 같이 읽자!

3.4 람다 표현식
람다의 첫 등장!  (String first, String second) -> first.length() - second.length();

3.5
 <https://palpit.tistory.com/675/> "메서드 참조와 생성자 참조"

3.7
1. 람다 표현식의 유효 범위
지역 범위와 이름이 같은 매개변수를 선언 하는 것은 규칙에 어긋난다
EX)
``` java
int first = 0;
Comparatort<String> comp = (first, second) -> first.length() - second.length();

for(String arg: agrs){
new Thread(() -> Systome.out.println(arg)).start();
//arg를 캡처 할수 있다
```
i 변수의 유효 범위가 전체 루프이기 때문에 캡처 X , arg 변수는 반복 할 때마다 생성하기 때문에 캡처 O

3.8 
- 고차함수란
  함수를 인자로 전달 받거나 함수를 결과를 함수로 반환하는 함수
  
 ```java
 pubilc static Comparator<String> compareInDirection(int direction){
  return (x,y) -> direction * x.compareTo(y);
 }
 ```
 compareIndirection(1) -> 오름차순
 compareIndirection(-1) -> 내림차순

4.5
- 리플렉션이란? 
 - 객체를 통해 클래스의 정보륿 분석하는 프로그램
 - 내부를 검사하고 내부 속성을 수정
 - 클래스의 getFields, getmethods, getConstructors 메서드는 각각 해당 클래스가 지원하는 공개 필드,메서드,생성자 배열을 반환한다.
 - 프로그램을 컴파일한 시점에서 이용 할 수 있다 & 클래스 뿐만 아니라 자바 가사 머신이 로드 할 수 있는 모든 코드를 분석 할 수 있다.
 
 사용 예제 ) 클래스를 불변 객체 생성 -> 리플렉션으로 불변 객체 변경 후 사용 등등
 테스트 코드 등에서 private으로만 생성자 생성 -> setAccessible(true) 지정 -> 외부에서 생성자 사용 가능
 참고 URL <https://code-examples.net/ko/q/a255ea/>
 
 객체 생성
  - 인수 없는 생성자 : newInstance()
   ``` java
   Construcotr constr = cl.getConstructor(int.Class)
   Object obj = constr.newInstnace(42);
   ```
   
  - 프록시(Proxy)
   클라이언트가 사용하려고 하는 실체 대상인 것처럼 위장하여 클라이언트 요청을 받아주어 처라히는 대리자 역활
   프록시 단어 자체가 대리인 이라는 의미를 가지고 있음
   
  5 예외처리
   - try / catch 에서 조심해야할 점
    ``` java
    * PrintWriter out = new PrintWriter("output.txt");
    for(String line : lines){
     out.println(line.toLowerCase());
     }
     out.close()
  ```
  에러가 발생 하면 out은 close 가 안된다.
   - chtch 에서 최대한 자세하게 예외처리를 한다.
  
 
