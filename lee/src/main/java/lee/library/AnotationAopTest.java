package lee.library;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


//@Aspect /*해당 클래스를 공통기능 클래스로 사용할 것이다*/
public class AnotationAopTest {
	
	/*@Pointcut("execution(* *..*Main.*(..)))")*/
	
	/*@Pointcut("execution(public void lee.library.aopMain())")*/
	//@Pointcut("execution(* lee..*Impl.*(..))")
	 /*@Pointcut("within(lee..*)")*/
    /*public void annotationAOP() {
		System.out.println("#################################Annotation pointcut###########");
    }

   
   @Before("annotationAOP()")
   public void before(JoinPoint joinPoint){
	   System.out.println("----------------------Annotation-----------breforeAOP");
   }
   @After("annotationAOP()")
   public void after(){
	   System.out.println("----------------------Annotation-----------afterAOP");
   }
   @AfterReturning("annotationAOP()")
   public void afterReturningAOP(){
	   System.out.println("----------------------Annotation-----------afterReturningAOP");
   }
	@Around("annotationAOP()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
    	 long start = System.nanoTime(); //현재의 나노시간을 반환
    	  try {
    	   Object result = joinPoint.proceed(); // 대상객체의 메서드 실행(ProceedingJoinPoint 타입은 대상 객체의 메서드를 호출할 때 사용)
    	   return result;
    	  } finally {
    	   long finish = System.nanoTime();
    	   Signature sig = joinPoint.getSignature(); //메서드의 시그니쳐
    	   System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
    	     joinPoint.getTarget().getClass().getSimpleName(),
    	     sig.getName(), 
    	     Arrays.toString(joinPoint.getArgs()), //인자목록을 반환
    	     (finish - start));
    	  }
    }
	
	@AfterThrowing(pointcut="logging()", throwing="ex") //예외값 지정
    public void after_throwing(Throwable ex){
    System.out.println(ex.getMessage() + " :############## @AfterThrowing");
    }*/
}
