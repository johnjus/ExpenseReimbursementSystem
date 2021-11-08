package main.models.accounts;

public class Account 
{
	private int accountId; //ers_user_id
	private String username;//ers_username
	private String password;//ers_password
	private String firstName;//user_first_name
	private String lastName;//user_last_name
	private String email;//user_email
	private int roleId; // user_role_id
	private String roleName; //ers_user_roles --- user_role
	
	//no args constructor
	public Account()
	{
		
	}
	//all states constructor
	public Account(int accountId, String username, String password, String firstName, String lastName, String email,
			int roleId, String roleName) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	//getters and setters
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int id) {
		this.accountId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	//to string method
	@Override
	public String toString() {
		return "Account [id=" + accountId + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	
	
}
