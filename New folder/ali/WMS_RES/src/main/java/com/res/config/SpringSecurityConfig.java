package com.res.config;

import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.res.filter.CustomUsernamePasswordAuthenticationFilter;
import com.res.handler.RESAuthenticationSuccessHandler;
import com.res.servlet.CaptchaGenServlet;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private com.res.service.impl.UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private RESAuthenticationSuccessHandler authenticationSuccessHandler;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	RequestMatcher csrfRequestMatcher = new RequestMatcher() {

		  // Enabled CSRF protection on the following urls:
		  private AntPathRequestMatcher[] requestMatchers = {
		      new AntPathRequestMatcher("/**/login/*"),
		      new AntPathRequestMatcher("/**/forgotpassword/*"),
		      new AntPathRequestMatcher("/**/resetpassword/*"),
		      new AntPathRequestMatcher("/**/registrationForm/*"),
		      new AntPathRequestMatcher("/**/doSignUp/*")
		  };
		  
		  @Override
		  public boolean matches(HttpServletRequest request) {
		    // If the request match one url the CSRF protection will be enabled
		    for (AntPathRequestMatcher rm : requestMatchers) {
		      if (rm.matches(request)) { return true; }
		    }
		    return false;
		  } // method matches
		};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		// Enable csrf only on some request matches
	      .csrf()
	        .requireCsrfProtectionMatcher(csrfRequestMatcher)
	        .and()
		.authorizeRequests()
			.antMatchers("/", "/captcha", "/forgotpassword", "/registrationForm", "/contactUs").permitAll()
			.antMatchers("/admin/**").access("hasRole('ADMIN')")
			.antMatchers("/adminView/**").access("hasRole('ADMIN_VIEW')")
			.antMatchers("/enc/**").access("hasRole('EnC')")
			.antMatchers("/supdte/**").access("hasRole('SUPDT_ENGG')")
			.antMatchers("/ce/**").access("hasRole('CE')")
			.antMatchers("/ee/**").access("hasRole('EE')")
			.antMatchers("/ae/**").access("hasRole('AE')")
			.antMatchers("/sube/**").access("hasRole('SUB_ENGG')")
			.antMatchers("/sdo/**").access("hasRole('SDO')")
			.antMatchers("/ao/**").access("hasRole('ACC_OFFICER')")
			.antMatchers("/contractor/**").access("hasRole('CONTRACTOR')")
			.and()
			.formLogin().loginPage("/login")
			.successHandler(authenticationSuccessHandler)
			.and()
			.exceptionHandling()
			.accessDeniedPage("/403")
			.and()
			.sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/login?timeout");

		// Protection against 'ClickJacking' attacks. solved 'X-Frame-Options Header Not Set'
		http.headers().frameOptions().sameOrigin();
		// Protection against Type 1 Reflected XSS attacks. solved 'Web Browser XSS Protection Not Enabled'
		http.headers().xssProtection();
		// Disabling browsers to perform risky mime sniffing. solved 'X-Content-Type-Options Header Missing'
		http.headers().contentTypeOptions();
		//Protection against Session Fixation
		http.sessionManagement().sessionFixation().migrateSession();
		
		http.addFilterBefore(
				authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/css/**", "/js/**", "/img/**", "/angular/**", "/new/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public ServletRegistrationBean captchaServlet() {
		ServletRegistrationBean captcha = new ServletRegistrationBean(
				new CaptchaGenServlet(), "/captcha");
		return captcha;
	}
	
	@Bean
	public Filter authenticationFilter() {
		return new CustomUsernamePasswordAuthenticationFilter(userDetailsServiceImpl);
	}
	
	// Register HttpSessionEventPublisher
	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}
	
	
}
