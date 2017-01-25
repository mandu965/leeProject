package lee.test.spring.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
//@Component("lifeBean")
//@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LifeBeanImpl implements LifeBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, 
									InitializingBean, DisposableBean, BeanClassLoaderAware, 
									 ResourceLoaderAware, ApplicationEventPublisherAware , MessageSourceAware{
	
	private String beanName; //설정파일에서 설정한 빈의 id를 저장할 변수
	private BeanFactory beanFactory; //스프링의 beanFactory 구현체를 저장할 변수
	private String greeting;
	
	private ApplicationContext context;
	
	public LifeBeanImpl(){
		System.out.println("1.인스턴스화(생성자 메소드 호출)");
	}
	
	//interface : BeanNameAware
	//빈 객체가 자기 자신의 이름을 알아야 할 경우에 사용
	//객체가 생설될 때 해당 객체의 id값을 전달(주입)받는 메서드
	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
		System.out.println("2. 빈의 이름 셋팅하기 setBeanName--->"+beanName);
	}
	
	//interface : BeanClassLoaderAware
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("3. setBeanClassLoadere 호출");
		
	}
	
	//interface : BeanFactoryAware
	//객체가 생성될 때 스프링의 BeanFactory를 전달받는 메소드
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory; 
		  System.out.println("4. 빈 팩토리 셋팅하기 setBeanFactory--->" + beanFactory);
		
	}
	
	//interface : ResourceLoaderAware
	public void setResourceLoader(ResourceLoader arg0) {
		System.out.println("5. setResourceLoader() 호출");
	}
	
	//interface : ApplicationEventPublisherAware
	public void setApplicationEventPublisher(ApplicationEventPublisher arg0) {
		System.out.println("6. setApplicationEventPublisher() 호출");
	}
	
	//interface : MessageSourceAware
	public void setMessageSource(MessageSource arg0) {
		System.out.println("7. setMessageSource() 호출");
	}
	
	/*
	 * interface : ApplicationContextAware
	 * 객체가 생성될 때 스프링의 ApplicationContext를 전달받는 메소드
	 * ApplicationContext를 빈(객체)에 전달할 때 사용
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		System.out.println("8. setApplicationContext 실행");
		this.context = context;
		
	}
	
	//9. 초기화 전의 빈에 대한 처리 실행

	@PostConstruct
	public void begin(){
		System.out.println("10. 사용자 초기화 메소드");
	}
	
	//interface : InitializingBean
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("11. 프로퍼티 설정 완료(afterPropertiesSet() 수행)");
	}
	
	//12.초기화 후의 빈에 대한 처리를 실행
	
	public void lifeMethod(){
		System.out.println("13. 비지니스 로직이 수행중입니다.......");
		System.out.println("otherBean 생성 및 함수 실행");
		OtherBean other = (OtherBean)context.getBean("otherBean");
		other.otherMessage(); //otherBean 추가 했을시, 결과. setBeanName실행 -> setApplicationContext 실행 -> life 비지니스 로직 수행 -> other 비지니스 로직 수행
	}
	
	@PreDestroy
	public void end(){
		System.out.println("14. 사용자 소멸 메소드");
	}

	//interface : DisposableBean
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("15. destroy() 수행");
			
	}
	
	

	

	

	

	

	

	

	

}
