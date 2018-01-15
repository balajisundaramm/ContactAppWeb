package com.contact;

public class RegBean {
	String name, email,pass,rpass;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("RegBen--> setName()");

	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		System.out.println("RegBen--> setEmail()");

	}

	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
		System.out.println("RegBen--> setPass()");

	}

	
	public String getRpass() {
		return rpass;
	}

	
	public void setRpass(String rpass) {
		this.rpass = rpass;
		System.out.println("RegBen--> setRpass()");
	}

	public RegBean() {
		super();
		System.out.println("RegBean--> no arg cons");
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((rpass == null) ? 0 : rpass.hashCode());
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
		if (!(obj instanceof RegBean)) {
			return false;
		}
		RegBean other = (RegBean) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pass == null) {
			if (other.pass != null) {
				return false;
			}
		} else if (!pass.equals(other.pass)) {
			return false;
		}
		if (rpass == null) {
			if (other.rpass != null) {
				return false;
			}
		} else if (!rpass.equals(other.rpass)) {
			return false;
		}
		return true;
	}

	
	@Override
	public String toString() {
		return "RegBean [name=" + name + ", email=" + email + ", pass=" + pass + ", rpass=" + rpass + "]";
	}
	
	public String validate() {
		StringBuilder sb=new StringBuilder();
		if(name==null || name.trim().equals(""))
			sb.append("Enter your name. Name is mandatory.<br/>");
		if(email==null || email.trim().equals(""))
			sb.append("Enter your email.Your email is mandatory<br/>");
		if(pass==null || pass.equals(""))
			sb.append("Your password is mandatory<br/>");
		else
			if(rpass==null || rpass.equals(""))
				sb.append("Enter password again in retype password.<br/>");
			else if(!rpass.equals(pass))
				sb.append("enter the same password!!<br/>");
		String msg=sb.toString();
		if(msg.equals(""))
			return "SUCCESS";
		else
			return msg;
		
	}
		
}
