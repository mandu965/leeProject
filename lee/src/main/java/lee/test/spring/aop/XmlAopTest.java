package lee.test.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class XmlAopTest {

	public void breforeAOP(JoinPoint joinPoint){
		System.out.println("----------------------XML-----------beforeAOP");
	
		Class clazz = joinPoint.getTarget().getClass();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		String classAndMethod = joinPoint.getSignature().toShortString();
       
		System.out.println("#########clazz######========" + joinPoint.getTarget().getClass());
        System.out.println("########joinPoint.getTarget()#######========" + joinPoint.getTarget());
        System.out.println("########className#######========" + className);
        System.out.println("#########methodName######========" + methodName);
        System.out.println("##########joinPoint.toLongString()#####========" + joinPoint.toLongString());
        System.out.println("##########joinPoint.toShortString()#####========" + joinPoint.toShortString());
        System.out.println("##########classAndMethod#####========" + classAndMethod); 
	}

public Object aroundAOP(ProceedingJoinPoint joinPoint)  throws Throwable{
	System.out.println("----------------------XML----함수진입------aroundAOP");
	long start = System.nanoTime();
	  try {
		  System.out.println("----------------------XML----해당 메소드 실행 전------aroundAOP");
	   Object result = joinPoint.proceed();// 대상객체의 메서드 실행(ProceedingJoinPoint 타입은 대상 객체의 메서드를 호출할 때 사용)
	   return result;
	  } finally {
		  System.out.println("----------------------XML----해당 메소드 실행 후------aroundAOP");
	   long finish = System.nanoTime(); //1/1000000000
	   Signature sig = joinPoint.getSignature(); //메서드의 시그니쳐
	   System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
	     joinPoint.getTarget().getClass().getSimpleName(),
	     sig.getName(), Arrays.toString(joinPoint.getArgs()),
	     (finish - start)/1000000000);
	   
	     System.out.println("##############joinPoint.toLongString()===================" + joinPoint.toLongString()); //대상 메서드 전체 syntax 리턴
	     //execution(public abstract javax.sql.DataSource com.ibatis.sqlmap.client.SqlMapTransactionManager.getDataSource())
	     
	     System.out.println("##############joinPoint.toShortString()===================" + joinPoint.toShortString()); // 대상 메소드명 리턴
	     //execution(SqlMapTransactionManager.getDataSource())
	     
	     System.out.println("##############joinPoint.getTarget()===================" + joinPoint.getTarget()); //대상객체를 리턴
	     //com.ibatis.sqlmap.engine.impl.SqlMapClientImpl@1dc7b3e
	     
	     System.out.println("##############joinPoint.getSignature()===================" + joinPoint.getSignature()); //호출되는 메소드 정보
	     //DataSource com.ibatis.sqlmap.client.SqlMapTransactionManager.getDataSource()
	     
	     System.out.println("##############joinPoint.getTarget().getClass().getSimpleName()===================" + joinPoint.getTarget().getClass().getSimpleName());
	     //SqlMapClientImpl
	  }
	 
}

public void afterAOP(){
	System.out.println("----------------------XML----------afterAOP");
}

//JoinPoint 는 대상 target 객체 정보를 보유한 결합지점
  //retValue 는 target 메서드가 실행한 후 return 값
public void afterReturningAOP(JoinPoint joinPoint, Object retValue){
 	   System.out.println("---------------XML--------------afterReturningAOP");
 	  String targetClass=joinPoint.getTarget().getClass().getName();
 	  String methodName=joinPoint.getSignature().getName();
 	  
 	  System.out.println("########logging!! className="+targetClass+"  methodName="+methodName +" retVal : "+retValue);
 	
    }

	public void afterThrowing(Throwable ex){
    System.out.println(ex.getMessage() + " :############## @AfterThrowing");
    }




}

