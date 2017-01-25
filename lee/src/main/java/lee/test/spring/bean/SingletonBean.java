package lee.test.spring.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SingletonBean {
	public SingletonBean(){};

}
