package lee.test.spring.aop; 

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
import org.springframework.stereotype.Service;
 
/*@Service 
@Aspect*/ /*해당 클래스를 공통기능 클래스로 사용할 것이다*/ 
public class AnnotationAopTest {

	@Pointcut("execution(* lee..*Impl.*(..))")
    public void annotationAOP() {
		 //@Pointcut("within(test..*)")
		//@Pointcut("within(test.spring.aop..*)")
		//@Pointcut("bean(customerService)")
    }

   @Before("annotationAOP()")
   public void before(JoinPoint joinPoint){
	   System.out.println("----------------------Annotation-----------beforeAOP");
   }
   @After("annotationAOP()")
   public void after(){
	   System.out.println("----------------------Annotation-----------afterAOP");
   }
  @AfterReturning(pointcut="annotationAOP()",  returning="retValue")
  public void afterReturningAOP(JoinPoint joinPoint, Object retValue){
	   System.out.println("---------------Annotation--------------afterReturningAOP");
	  //String targetClass=joinPoint.getTarget().getClass().getName();
	  //String methodName=joinPoint.getSignature().getName();
	  //System.out.println("########logging!! className="+targetClass+"  methodName="+methodName +" retVal : "+retValue);
	
   }
	@Around("annotationAOP()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
    	 long start = System.nanoTime(); //현재의 나노시간을 반환
    	  try {
    		  System.out.println("---------------Annotation--------------AroundAOP");
    	   Object result = joinPoint.proceed(); // 대상객체의 메서드 실행(ProceedingJoinPoint 타입은 대상 객체의 메서드를 호출할 때 사용)
    	   return result;
    	  } finally {
    		  System.out.println("---------------Annotation--------------AroundAOP");
    	   long finish = System.nanoTime();
    	   Signature sig = joinPoint.getSignature(); //메서드의 시그니쳐
    	   System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
    	     joinPoint.getTarget().getClass().getSimpleName(),
    	     sig.getName(), 
    	     Arrays.toString(joinPoint.getArgs()), //인자목록을 반환
    	     (finish - start));
    	  }
    }
	 
	@AfterThrowing(pointcut="annotationAOP()", throwing="ex") //예외값 지정
    public void after_throwing(Throwable ex){
		System.out.println("---------------Annotation--------------AfterThrowing");
    }
     
} 


