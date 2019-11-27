# the-fastest-core-java-9
[가장 빨리 만나는 코어 자바 9] 같이 읽자!

3.4 람다 표현식
람다의 첫 등장!  
```java
(String first, String second) -> first.length() - second.length();
```

3.5
 [메서드 참조와 생성자 참조](https://palpit.tistory.com/675) 

3.7
1. 람다 표현식의 유효 범위
지역 범위와 이름이 같은 매개변수를 선언 하는 것은 규칙에 어긋난다

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
 
 * 사용 예제 ) 클래스를 불변 객체 생성 -> 리플렉션으로 불변 객체 변경 후 사용 등등
 테스트 코드 등에서 private으로만 생성자 생성 -> setAccessible(true) 지정 -> 외부에서 생성자 사용 가능
 * [동적 Java reflection-setAccessible의 영향](https://code-examples.net/ko/q/a255ea)
 
 객체 생성
  - 인수 없는 생성자 : newInstance()
``` java
Construcotr constr = cl.getConstructor(int.Class)
Object obj = constr.newInstnace(42);
```
   
  - 프록시(Proxy)
   클라이언트가 사용하려고 하는 실체 대상인 것처럼 위장하여 클라이언트 요청을 받아주어 처라히는 대리자 역활
   프록시 단어 자체가 대리인 이라는 의미를 가지고 있음
   
5. 예외처리
  - try / catch 에서 조심해야할 점
``` java
PrintWriter out = new PrintWriter("output.txt");
for(String line : lines){
     out.println(line.toLowerCase());
}
out.close()
```
  에러가 발생 하면 out은 close 가 안된다.
   - 따라서, finally 에서 반드시 close 처리를 해야한다.
   - JDK 1.7 버전에서는 이런 번거로움, 그리고 보기 힘든 코드를 지양하기 위해 try with resource 를 사용한다.
   - [try_with_resource](https://dololak.tistory.com/67)
   - catch 에서 최대한 자세하게 예외처리를 한다.
   
####6. 제네릭 프로그래밍
* [제네릭 참고 사이트](https://palpit.tistory.com/665)
* Chapter6 > Generic 폴더에 예제 첨부

* 6-1 제네릭 클래스
    * 타입 매개변수가 한 개 이상 있는 클래스
    ```java
    public class Entry<K,V> {
      private K key;
      private V value;
      
      public Entry(K key, V value) {
          this.key = key;
          this.value = value;
      }
      public K getKey() {
          return key;
      }   
      
      public V getValue() {
          return value;
      }   
  }
  ```
    * 사용
  ```java
    Entry<String, Integer> entry = new Entry<>("Fred", 42);
  ```  
* 6-2 제네릭 메서드
    * 제너릭 클래스가 타입 매개변수를 받는 클래스인 것처럼 **제너릭 메서드**는 타입 매개변수를 받는 메서드다. 
    * 배엘에 있는 요소를 교환하는 메서드 (기본 타입을 제외)
    ```java
       public class Arrays {
          public static <T> void swap(T[] array, int i, int j) {
              T temp = array[i];
              array[i] = array[j];
              array[j] = temp;
          }
       }    
    ```
    * 사용
    ```java
    String[] friends = ...;
    Arrays.swap(friends, 0, 1);
    ```
* 6-3 타입 경계
  * 제네릭 클래스나 제네릭 메서드가 받는 타입 매개변수의 타입을 제한해야 할 때도 있다. 이때, 타입경계(type bound)를 사용한다.  
  ```java
    public static <T extends AutoCloseable> void closeAll(ArrayList<T> elems) throws Exception {
      for (T elem : elems) {
          elem.close();
      }
    }
  ```
  * ArrayList<PrintStream> 은 전달 가능, ArrayList<String> 은 전달 불가능.
  * extends AutoCloseable 은 요소 타입이 AutoCloseable 의 서브타입임을 보장한다.
  
* 6-4 타입 가변성과 와일드 카드
    * ArrayList<Manager> 는 ArrayList<Employee>의 서브타입이 아니다.
    
    6.4.1 서브타입 와일드 카드
        
    ```java
    //이를 해결하기 위해서 서브 타입 와일드 카드를 사용하자.
    public static void printNames(ArrayList<? extends Employee> staff) {
      for (int i = 0; i < staff.size(); i++) {
          Employee e = staff.get(i);
          System.out.println(e.getName());
      }
    }
    ```
    
    6.4.2 슈퍼타입 와일드 카드
    
    ```java
      public interface Predicate<T> {
          boolean test(T arg);
      }   
    ```
    ```java 
      printAll(employee, e -> e.getSalary() > 100000);
  
      Predicate<Object> evenLength = e -> e.toString().length() % 2 == 0;
      printAll(employee, evenLength);
    ```
    * 결국 모든 Employee 는 toString 메서드가 있는 Object 이기 때문에 Object 타입에도 문제가 없어야 한다.
    ```java
      //이를 해결하기 위해서 슈퍼 타입 와일드 카드를 사용하자.
      public static void printAll(Employee[] staff, Predicate<? super Employee> filter) {
          for (Employee e : staff) {
              if(filter.test){
                  System.out.println(e.getName());
              }   
          }     
      }
    ```
    6.4.5 와일드 카드 캡쳐
    ```java
      public static void swap(ArrayList<?> elements, int i, int j) {
          ? temp = elements.get(i); // 작동하지 않는다.
          elements.set(i, elements.get(j));
          elements.set(j, temp);
      }
    ```
    ?를 타입 인수로는 사용할 수 있지만, 타입으로는 사용할 수 없다. 하지만 이문제를 우회해서 해결하는 방법은 있다.
    ```java
      public static void swap(ArrayList<?> elements, int i, int j) {
          swapHelper(elements, i, j);
      }

      private static <T> void swapHelper(ArrayList<T> elements, int i, int j) {
          T temp = elements.get(i);
          elements.set(i, elements.get(j));
          elements.set(j, temp); 
      }
    ```
  
    와일드카드 캡쳐라는 특별한 규칙 덕분에 swapHelper 호출은 유효하다. 컴파일러는 ?가 무엇인지 모르지만, ?는 어떤 타입을 나타내므로
    제네릭 메서드를 호출해도 된다.
    와일드카드 캡처로 얻을 수 있는 이점은 뭘까? API 사용자가 제네릭 매서드 대신 이해하기 쉬운 ArrayList<?>를 볼 수 있다는 이점을 얻을 수 있다.
    
 
* 6.6 제네릭의 제약
    * 기본타입 인수를 사용할 수 없다.
    
        타입 매개변수는 절대로 기본타입이 될 수 없다. ex) ArrayList<int> (X)
    
    * 실행 시간에는 모든 타입이 로(raw) 형태다. ex) raw 형태 List, Set...
    * 타입 변수를 인스턴스화할 수 없다.
    
    
   
       