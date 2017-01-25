package lee.test.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component("myBeanPostProcessor")
public class MyBeanPostProcessor implements BeanPostProcessor{
	 //빈의 초기화작업 전과 후에대한 셋팅을 해줄수 있다.
	 //자동으로 실행되지는 않고 필요할때 add시켜서 사용한다.
	
	public MyBeanPostProcessor(){
		System.out.println("MyBeanPostProcessor 클래스의 기본생성자 호출");
	}
	
	 public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	  System.out.println("12.초기화 후의 빈에 대한 처리를 실행" + bean + ": " + beanName);
	  return bean;
	 }
	 
	 public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	  System.out.println("9.초기화 전의 빈에 대한 처리를 실행" + bean + ": " + beanName);
	  return bean;
	 }
}
