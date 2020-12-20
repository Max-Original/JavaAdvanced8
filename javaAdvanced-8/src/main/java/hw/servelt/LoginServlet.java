package hw.servelt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.UserLogin;
import hw.domain.UserAccount;
import hw.service.UserAccountService;
import hw.service.impl.UserAccountServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserAccountService userService = UserAccountServiceImpl.getUserAccountService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserAccount user = userService.getUserAccountByEmail(email);
	
		if (user != null && user.getPassword().equals(password)) {
			UserLogin userLogin = new UserLogin();
			userLogin.destinationUrl = "welcomeToTheCabinet.jsp";
			userLogin.userEmail = user.getEmail();
			String json = new Gson().toJson(userLogin);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

}
