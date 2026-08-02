package com.res.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.res.constants.RESConstants;

@Component
public class RESAuthenticationSuccessHandler implements
AuthenticationSuccessHandler {

	public static final Logger logger = LoggerFactory.getLogger(RESAuthenticationSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		/*HttpSession session = httpServletRequest.getSession();
		session.setAttribute("loggedInUser", httpServletRequest.getRemoteUser());*/
		
		String remoteAddr = httpServletRequest.getHeader("X-Forwarded-For");
		if (remoteAddr == null || "".equals(remoteAddr)) {
			remoteAddr = httpServletRequest.getRemoteAddr();
		}
		logger.info("User - "
				+ authentication.getName()
				+ ", Role - "
				+ authentication.getAuthorities().toString()
				+ " - User Logged In Successfully from System IP: " + remoteAddr);
		
		/*String language = httpServletRequest.getParameter("lang");

		if (!StringUtils.isEmpty(language) && language.equals("hi")) {
			httpServletRequest.getSession().setAttribute(
					SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("hi", "IN"));
		}*/
		
		HttpSession session = httpServletRequest.getSession();

		String targetUrl = "";
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();

		for (GrantedAuthority authority : authorities) {

			String role = authority.getAuthority();
			session.setAttribute(RESConstants.LOGGED_IN_USER_ROLE, role);
			
			if(RESConstants.ROLE_ADMIN.equals(role)) {
				targetUrl = "/admin/home#/changepassword";
				break;
			} else if(RESConstants.ROLE_ADMIN_VIEW.equals(role)) {
				targetUrl = "/adminView/home#/changepassword";
				break;
			} else if(RESConstants.ROLE_EE.equals(role)) {
				targetUrl = "/ee/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_AE.equals(role)) {
				targetUrl = "/ae/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_SUB_ENGG.equals(role)) {
				targetUrl = "/sube/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_SDO.equals(role)) {
				targetUrl = "/sdo/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_SUPDT_ENGG.equals(role)) {
				targetUrl = "/supdte/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_CE.equals(role)) {
				targetUrl = "/ce/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_EnC.equals(role)) {
				targetUrl = "/enc/home#/changepassword";
				break;
			}else if(RESConstants.ROLE_DIR_GP.equals(role)) {
				targetUrl = "/dirgp/home#/changepassword";
				break;
			} else if(RESConstants.ROLE_ACC_OFFICER.equals(role)) {
				targetUrl = "/ao/home#/changepassword";
				break;
				
			} else if(RESConstants.ROLE_CONTRACTOR.equals(role)) {
				targetUrl = "/contractor/home#/changepassword";
				break;
			} else {
				targetUrl = "/403";
			}
		}
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
