package entity;

public class User {
	private int idusers;
	private String fullname;
	private String gender;
	private String state;
	private String city;
	private String street;
	private String zipcode;
	private String birthyear;
	private String email;
	private String password;
	
	public User(){
		super();
	}
	
	public User(int idusers, String fullname, String gender, String state, String city, String street, String zipcode,
			String birthyear, String email, String password) {
		super();
		this.idusers = idusers;
		this.fullname = fullname;
		this.gender = gender;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.birthyear = birthyear;
		this.email = email;
		this.password = password;
	}

	public int getIdusers() {
		return idusers;
	}
	
	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getBirthyear() {
		return birthyear;
	}
	
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return " ID: " + this.idusers + ", Full name: " + this.fullname + ", Email: " + this.email;
	}
}
