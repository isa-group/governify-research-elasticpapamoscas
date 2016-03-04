package es.us.isa.papamoscas.proxysla.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.us.isa.papamoscas.proxysla.repositories.MetricsRepository;
import es.us.isa.papamoscas.proxysla.repositories.RouterRepository;


@Component
public class MetricsFilter implements Filter {
	
	@Autowired
	MetricsRepository metricsRepository;	
	
	@Autowired
	RouterRepository routeRepo;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String user = httpRequest.getParameter("user");
		String path = httpRequest.getRequestURI();
		
		if(path.contains("api")){
			if (routeRepo.isPermitedRequest(user)){
				metricsRepository.addRequestIn(user);
				
				chain.doFilter(request, response);
				
				int status = httpResponse.getStatus();
					
				if(status == 200 ){
					metricsRepository.addRequestOK(user, "OK");
				}else{
					metricsRepository.addRequestOK(user, "ERROR");
				}
			}
				
		}else{
			
			chain.doFilter(request, response);
			
		}
			
		
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
