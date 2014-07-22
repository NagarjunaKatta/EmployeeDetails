import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {

	static ApplicationContext context;
	@Override
	public <A> A getControllerInstance(Class<A> beanname) throws Exception {
		System.out.println("***********************************************");
		return context.getBean(beanname);
	}
	@Override
	public void onStart(Application arg0) {
		System.out.println("-----------------------------------------------");
		context=new ClassPathXmlApplicationContext("Beans.xml");
	}
}
