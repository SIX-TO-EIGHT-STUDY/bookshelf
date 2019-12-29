### 8. 자바 기본

#### 객체 이용하기
#### 객체의 일부 메서드가 오버라이드되었을 때 어떻게 사용되는지 설명하라.
* equals (Object other) 메서드
    * 두 객체의 참조가 논리적으로 같은지 확인하는 데 사용한다.
    * 실행하면 메모리 상의 객체 위치를 비교한다. 두 개의 객체가 같은 메모리 위체이 있으면 실제로 같은 객체라는 의미다.

* hashCode 메서드
    * 두 개의 같은 객체가 반드시 같은 값을 반환해야 한다는 규칙이 있다.
    * 그 반대의 경우는 참이 아니다. 두 개의 객체가 같은 hashCode 메서드를 반환하더라도 반드시 같은 객체인 것은 아니다.
    * hashCode 메서드가 int 값을 반환한다는 것을 기억해라. hashCode 메서드가 반환하는 값이 다를 때 객체가 다르다는 점을 보장하려면 
    특정 인스턴스의 단일성은 최대 2^32 개 까지만 존재할 수 있다.

* hashCode 메서드나 equals 메서드에서 이러한 관계성이 있는 이유는 java.util.HashMap 같은 컬렉션 클래스에 구현되어 사용되고 있는 것을 보면 알 수 있다.
* 그리고 hashCode 메서드의 반환 값은 테이블에서 어떤 인덱스인지를 결정하는 데 사용된다.

* hashCode 와 equals 메서드의 관계에서 지켜야 하는 규칙
    * hashCode 나 equals 메서드를 오버라이딩할 때 반드시 둘 다 함께 오버라이드해야 한다.
    * 같은 객체가 반드시 hashCode 매서드의 같은 값을 반환하더라도 그 역은 참이 아니다.
    
#### 자바 배열
#### 자바에서는 배열을 어떻게 표현하는가?
* 자바 배열에서 기억해야하는 중요한 사항은 배열을 객체로 취급한다는 것이다.

#### String 이용하기
#### String 은 메모리에 어떻게 저장되는가?
* 자바 1.7 에서 String 객체로 표현되는 값이 char 타입의 배열이라는 것을 직접 확인할 수 있다.
* String 객체 대부분은 원시 타입처럼 취급한다. 그러므로 String 리터럴을 생성할 때 new 키워드를 이용할 필요가 없다.

#### String 객체의 값을 변경할 수 있는가?
* String 클래스에서는 String 객체의 값을 변경하는 것처럼 보이는 모든 메서드가 실제로는 String 인스턴스를 반환한다.
* String 의 값은 절대 변하지 않으며 바꿀 수 없다.

#### 인터닝이란 무엇인가?
* 클래스가 JVM 에 로드되면 모든 리터럴이 상수 풀에 위치하게 된다. 그리고 String 리터럴의 모든 밥복은 풀 안의 같은 상수를 참조해서 이루어지는데, 이를 String 인터닝(interning) 이라고 한다.
* [참고_상수풀](https://ict-nroo.tistory.com/18) 

#### 제네릭 이해하기
#### 컬렉션 API 에서 제네릭을 어떻게 사용하는지 설명하라.
* 컬렉션 클래스에서 제네릭을 사용했을 때 컴파일러는 특정 타입만 포함될 수 있게 컬렉션을 제한한다.

#### 타입의 변화는 제네릭에 어떻게 영향을 미치는가?
```java
class A {}
class B extends A {} 
```
* B는 A의 하위 타입이다. 하지만 List<B> 는 List<A>의 하위 타입이 아니다.
* 제네릭 타입을 다룰 때는 때때로 클래스의 하위 타입을 받아들여야 하는 경우도 있다.
* 따라서 pushAllA의 메서드 시그니처는 A 클래스와 A의 모든 클래스에서 실행될 수 있도록 명시적으로 변경되어야 한다.
```java
// 기존
public static GenericStack<A> pushAllA(final List<A> listOfA) {}
// 변경
public static GenericStack<A> pushAllA(final List<? extends A> listOfA) {}
```
#### 오토박싱과 언박싱 이해하기
#### NullPointerException 이 발생했을 때 원시타입에 접근할 수 있는가?
* 아니오. 컴파일러가 Integer 타입을 int 타입으로 변경할 때는 null 값이 아니라고 생각한다.
* 그래서 null 이 되면 즉시 NullPointerException 이라는 예외가 발생한다.

#### 예외 처리하기
#### 자바의 예외 처리 구조를 이루는 주요 클래스를 설명하라.
* 모든 클래스는 Throwable 클래스를 확장해서 예외 처리를 할 수 있다.
* Throwable 는 Error 와 Exception 이라는 두 개의 하위 클래스를 갖는데, 필요한 곳에서 Exception 클래스를 확인하고 수정하는 것은 대개 프로그래머의 책임이다.
* Error 는 OutOfMemoryError 나 NoClassDefFoundError 클래스처럼 프로그래머 스스로 복구할 수 있는 것은 아니다.
* 예외는 runtime exception 이거나 checked exception 두 가지로 구분된다.

#### 연쇄 예외란 무엇인가?
* 새 예외의 생성자에는 이전 예외에 대한 참조를 추가한다.
* 따라서 연쇄 예외에서 Stack trace 를 확인하면 Application console 에 Caused by 라는 행으로 시작하는 전체 경로를 출력한다. 
이 메시지에서 가리키는 것은 새로운 예외나 다시 처리하기 전에 래핑된 원 예외다.

#### 표준 자바 라이브러리 사용하기
#### 왜 private 인 필드가 변하지 않도록 하기 위해 final 키워드를 선언해야 하는가?
* 접근자 메서드가 없는 final 지시자로 선언한 클래스가 있고 모든 필드가 private 경우, 프레임 워크를 만들 것이 아니라면 private 필드의 값을 변경할 이유가 없다.
```java
public final class BookRecord {
    private static final String finalAuthor = "finalAuthor";
    private String author = "author";
    private static final String bookTitle = "final bookTitle";

    public static String getFinalAuthor() {
        return finalAuthor;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
```
```java
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import org.junit.Test;

public class BookRecordTest {
    @Test
    public void mutateBookRecordState() throws NoSuchFieldException, IllegalAccessException{
        final BookRecord bookRecord = new BookRecord();
        final Field finalAuthor = bookRecord.getClass().getDeclaredField("finalAuthor");
        final Field author = bookRecord.getClass().getDeclaredField("author");

        author.setAccessible(true);
        author.set(bookRecord, "set author");

        assertEquals("set author", bookRecord.getAuthor());
        
        //Error - Can not set static final java.lang.String field
        finalAuthor.setAccessible(true);
        finalAuthor.set(bookRecord, "set author");
    }
}
```

#### Hashtable 클래스가 이미 존재하는데 왜 HashMap 클래스를 추가하는가?
* Hashtable 클래스는 동기화할 수 있으며 병렬 처리에 효율적이다. 단, 어떤 단일 스레드 작업이든 오버헤드 때문에 성능이 상당히 저하된다. 
* HashMap 은 동기화할 수 없다.
* 병렬 환경에서의 Map 인터페이스를 사용하는 곳에는 ConcurrentHashMap (java 5 에서 추가됨) 클래스를 사용하는게 좋다.