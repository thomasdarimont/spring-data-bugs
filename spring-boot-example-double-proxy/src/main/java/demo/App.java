package demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * proxyTargetClass=true leads to:
 * Could not generate CGLIB subclass of class [class com.sun.proxy.$Proxy48]: Common causes of this problem include using a final class or a non-visible class; nested exception is java.lang.IllegalArgumentException: Cannot subclass final class class com.sun.proxy.$Proxy48
 * 
 * proxyTargetClass=false
 * Leads to proxy in proxy
 * 
 * See //HACK in
 * org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.wrapIfNecessary(Object, String, Object)
 * which avoids the "double" proxying
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass=false)
public class App {

	public static void main(String[] args) {

		CustomerRepository repository = SpringApplication.run(App.class, args).getBean(CustomerRepository.class);

		repository.save(new Customer("First1", "Last1"));
		repository.save(new Customer("First2", "Last2"));

		repository.flush();

		Runnable code = () -> {
			System.out.println(repository.findByLastnameLike("La%"));
		};

		runAs("user", Arrays.asList("USER"), code);
		runAs("admin", Arrays.asList("ADMIN"), code);
	}

	static void runAs(String username, List<String> roles, Runnable code) {

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, "N/A",
				AuthorityUtils.createAuthorityList(roles.stream().toArray(String[]::new)));
		SecurityContextHolder.getContext().setAuthentication(auth);

		System.out.printf("Run as: %s with roles: %s%n", username, roles);
		try {
			code.run();
			System.out.printf("worked!%n");
		} catch (Exception ex) {
			System.out.printf("failed with error:  %s!%n", ex.getMessage());
		}
	}
}
