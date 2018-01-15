package com.contact;

public class LoginBean {
String email,pass;

public LoginBean() {
	super();
	System.out.println("LoginBean--> no arg cons");
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
	System.out.println("LoginBean--> setEmail");

}

public String getPass() {
	return pass;
}


public void setPass(String pass) {
	this.pass = pass;
	System.out.println("LoginBean--> setPass");

}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((pass == null) ? 0 : pass.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (obj == null) {
		return false;
	}
	if (!(obj instanceof LoginBean)) {
		return false;
	}
	LoginBean other = (LoginBean) obj;
	if (email == null) {
		if (other.email != null) {
			return false;
		}
	} else if (!email.equals(other.email)) {
		return false;
	}
	if (pass == null) {
		if (other.pass != null) {
			return false;
		}
	} else if (!pass.equals(other.pass)) {
		return false;
	}
	return true;
}


@Override
public String toString() {
	return "LoginBean [email=" + email + ", pass=" + pass + "]";
}

public String validate() {
	StringBuilder sb=new StringBuilder();
	if(email== null || email.trim().equals(""))
		sb.append("Enter the correct email id you have registered..<br/>");
	if(pass==null || pass.equals(""))
		sb.append("Enter the password you have registered..<br/>");
	String msg=sb.toString();
	if(msg.equals(""))
		return "SUCCESS";
	else
		return msg;
}


}
