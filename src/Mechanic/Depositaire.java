package Mechanic;


public class Depositaire {
	private String name;
	private String tel;
	private String fax;
	private String email;
	private String contact;
	
	public Depositaire(String name, String tel, String fax, String email, String contact)
	{
		this.name = name;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.contact = contact;
	}
	
	public Depositaire(String[]s)
	{
		this.name = s[0];
		this.tel = s[1];
		this.fax = s[2];
		this.email = s[3];
		this.contact = s[4];
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String toString()
	{
		return name+"*"+tel+"*"+fax+"*"+email+"*"+contact;
	}
	

}
