package pl.schoolmanager.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password,enabled from user " + "where username=?")
				.authoritiesByUsernameQuery("select username, user_role from user_role " + "where username=?");
	}

	@Configuration
	@Order(99)
	public static class standardSecurity extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers("/login", "/register", "/").permitAll()
			.antMatchers("/user/**").hasRole("ADMIN")
			.antMatchers("/schoolAdminView/{schoolAdminId}/**")
			.access("@schoolAdminAccess.checkAccess(authentication, #schoolAdminId)")
			.antMatchers("/teacherView/{teacherId}/**")
			.access("@teacherAccess.checkAccess(authentication, #teacherId)")
			.antMatchers("/studentView/{studentId}/**")
			.access("@studentAccess.checkAccess(authentication, #studentId)")
			.antMatchers("/**").authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/welcome", true)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true)
			.and()
			.exceptionHandling().accessDeniedPage("/403");
		}
	}
		
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}