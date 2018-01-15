package com.contact;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.sun.org.apache.regexp.internal.recompile;

public class ContactBean {
	String name,email,phno,tag,gender,dob;
	
	public ContactBean() {
		super();
		System.out.println("ContactBean--> no arg()");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		System.out.println("ContactBean-->setName");
	}
	
	

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
		System.out.println("ContactBean-->setEmail");

	}
	
	public String getPhno() {
		return phno;
	}
	
	public void setPhno(String phno) {
		this.phno = phno;
		System.out.println("ContactBean-->setPhno");

	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
		System.out.println("ContactBean-->setTag");

	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
		System.out.println("ContactBean-->setGender");

	}
	
	public String getDob() {
		return dob;
	}

	
	public void setDob(String date){
		this.dob=date;
		System.out.println("ContactBean-->setDob()");
	}

	
	@Override
	public String toString() {
		return "ContactBean [name=" + name + ", email=" + email + ", phno=" + phno + ", tag=" + tag + ", gender="
				+ gender + ", dob=" + dob + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phno == null) ? 0 : phno.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		if (!(obj instanceof ContactBean)) {
			return false;
		}
		ContactBean other = (ContactBean) obj;
		if (dob == null) {
			if (other.dob != null) {
				return false;
			}
		} else if (!dob.equals(other.dob)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phno == null) {
			if (other.phno != null) {
				return false;
			}
		} else if (!phno.equals(other.phno)) {
			return false;
		}
		if (tag == null) {
			if (other.tag != null) {
				return false;
			}
		} else if (!tag.equals(other.tag)) {
			return false;
		}
		return true;
	}
	
	public String validate() {
		StringBuilder sb=new StringBuilder();
		if(name==null || name.trim().equals(""))
			sb.append("Name is mandatory to create a contact..<br/>");
		if(email==null || email.trim().equals(""))
			sb.append("Email is mandatory to create a contact..<br/>");
		if(phno==null || phno.trim().equals(""))
			sb.append("Phone number is mandatory to create a contact..<br/>");
		if(tag==null || tag.trim().equals(""))
			sb.append("Enter some tags. it is easy to search the contact later<br/>");
		if(dob==null || dob.trim().equals(""))
			sb.append("Enter Date of birth of the person so that you would be remainded..<br/>");
		if(gender==null)
			sb.append("Choose the gender<br/>");
		String msg=sb.toString();
		if(msg.equals("")) {
			return "SUCCESS";
		}
		else
			return msg;
	}
}
