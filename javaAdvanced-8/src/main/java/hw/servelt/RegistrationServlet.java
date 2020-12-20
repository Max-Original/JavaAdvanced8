package hw.servelt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw.domain.UserAccount;
import hw.domain.UserRole;
import hw.service.UserAccountService;
import hw.service.impl.UserAccountServiceImpl;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserAccountService userService = UserAccountServiceImpl.getUserAccountService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");

		if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
			userService.creat(new UserAccount(firstName, lastName, email, password, UserRole.USER.toString()));
		}
		
		
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}
	
	
}