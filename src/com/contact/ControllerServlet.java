package com.contact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ControllerServlet() {
        super();
        System.out.println("Controller--> no arg const");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller--> doGet()");
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller--> process()");
		// Getting URI
		String uri=request.getRequestURI();
		System.out.println("URI-->"+uri);
		
		// Create Request Dispatcher & assign null
		RequestDispatcher rd=null;
		// Create Model 
		Model model= new Model();

		
		if(uri.contains("/openRegisterView.do")) {
			rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		}
		
		if(uri.contains("/openLoginView.do")) {
			rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		
		if(uri.contains("/openListOfUsersView.do")) {
			List<RegBean> list=model.getUsers();
			// set the list and forward
			request.setAttribute("listUsers", list);
			rd=request.getRequestDispatcher("DisplayUsers.jsp");
			rd.forward(request, response);
		}
		
		if(uri.contains("/openEditAccountView.do")) {
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("user")==null) {
				request.setAttribute("errorMsg", "First login, then edit account!");
				rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
			else {
				rd=request.getRequestDispatcher("EditAccount.jsp");
				rd.forward(request, response);
			}
		}
		
		if(uri.contains("/openAddContactsView.do")) {
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("user")==null) {
				request.setAttribute("errorMsg", "First login, then add contacts to your account!");
				rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
			else {
			rd=request.getRequestDispatcher("AddContact.jsp");
			rd.forward(request, response);
			}
		}
		
		if(uri.contains("/openListContactsView.do")) {
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("user")==null) {
				request.setAttribute("errorMsg", "First login, then list your cotacts!!");
				rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
			else {
				String email=(String)session.getAttribute("user");
				System.out.println(email);
				List<ContactBean> contactList=model.getContacts(email);
			
			request.setAttribute("listContacts", contactList);
			rd=request.getRequestDispatcher("ListContact.jsp");
			rd.forward(request, response);
			}
		}
		
		if(uri.contains("/openSearchContactsView.do")) {
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("user")==null) {
				request.setAttribute("errorMsg", "First account!");
				rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
			else {
			rd=request.getRequestDispatcher("SearchContact.jsp");
			rd.forward(request, response);
			}
		}
		
		if(uri.contains("/openEditContactsView.do")) {
			rd=request.getRequestDispatcher("EditContact.jsp");
			rd.forward(request, response);
		}
		
		if(uri.contains("/openBirthdayRemainderView.do")) {
			rd=request.getRequestDispatcher("BirthdayRemainder.jsp");
			rd.forward(request, response);
		}
		
		if(uri.contains("/register.do")) {
			RegBean bean=(RegBean) request.getAttribute("reg");
			System.out.println("bean-->"+bean);
			

			String result=model.register(bean);
			if(result.equals("SUCCESS")) {
				// Registration succeeded
				// forward to success.jsp
				request.setAttribute("message", "Your registeration process succeeded! "
						+ "Click <a href='HomePage.jsp'>Click to go back to HomePage</a> ");
			rd=request.getRequestDispatcher("Success.jsp");
			rd.forward(request, response);
			}
			else {
				// Registration failed
				System.out.println("in register() else block");
				request.setAttribute("errorMsg", result);
				rd=request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
				
			}
		}
		
		if(uri.contains("/login.do")) {
			LoginBean bean=(LoginBean) request.getAttribute("login");
			System.out.println("Bean-->"+bean);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", bean.getEmail());
			
			String result=model.login(bean);
			if(result.equals("SUCCESS")) {
				// Registration succeeded
				// forward to Menu.jsp
				request.setAttribute("message", "You have loggedin successfully!!! ");
				rd=request.getRequestDispatcher("Menu.jsp");
				rd.forward(request, response);
			}
			else {
				// Login failed
				request.setAttribute("errorMsg", result);
				rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		}
		
		if(uri.contains("/editAccount.do")) {
			RegBean bean=(RegBean) request.getAttribute("edit");
			System.out.println("Bean-->"+bean);
			
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("user")==null) {
				request.setAttribute("errorMsg", "First login, then edit account!");
				rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
			else {
				String result=model.editAccount(bean);
				if(result.equals("SUCCESS")) {
					//Editing process succeeded, forward to success.jsp
					request.setAttribute("message", "Your editing process succeeded!"
							+ "Click <a href='HomePage.jsp'>Click to go back to HomePage</a>"
							+ "Click <a href='/ContactAppWeb/logout.do'>Click here to logout.</a>");
					rd=request.getRequestDispatcher("Success.jsp");
					rd.forward(request, response);
				}
				else {
					// Editing process failed
					request.setAttribute("errorMsg", result);
					rd=request.getRequestDispatcher("EditAccount.jsp");
					rd.forward(request, response);
				
				}
			}
		}
		
		if(uri.contains("/logout.do")) {
			//invalidate the session
			HttpSession session = request.getSession(false);
			if(session!=null)
			{
				session.removeAttribute("user");
				session.invalidate();
			}
			rd = request.getRequestDispatcher("Success.jsp");
			request.setAttribute("message","You have logged out successfully! Click <a href='HomePage.jsp'>Click to go back to HomePage</a>");
			rd.forward(request, response);
		}
		
		if(uri.contains("/addContact.do")) {
			ContactBean cbean=(ContactBean) request.getAttribute("contact");
			System.out.println("contBean-->"+cbean);
			
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("user")==null) {
				request.setAttribute("errorMsg", "First login, then add Contact!");
				rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
			}
				else {
					String email=(String)session.getAttribute("user");
					System.out.println(email);
					String result=model.addContact(cbean, email);
					if(result.equals("SUCCESS")) {
						//Editing process succeeded, forward to success.jsp
						request.setAttribute("message", "Contact details added succesfully!!!"
								+ "Click <a href='HomePage.jsp'>Click to go back to HomePage</a>"
								+ "<a href='Menu.jsp'>Click here to go back to menu</a>"
								+ "Click <a href='/ContactAppWeb/logout.do'>Click here to logout.</a>");
						rd=request.getRequestDispatcher("Success.jsp");
						rd.forward(request, response);
					}
					else {
						// Editing process failed
						request.setAttribute("errorMsg", result);
						rd=request.getRequestDispatcher("AddContact.jsp");
						rd.forward(request, response);
					
					}
				}

		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller--> doPost()");
		doGet(request, response);
	}

}
