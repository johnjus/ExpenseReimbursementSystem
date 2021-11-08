package main.models.reimbursement;

public class Reimbursement 
{
	private int reimbId;
	private float reimbAmount;
	private String reimbSubmitted;
	private String reimbResolved;
	private String reimbDescription;
	private int reimbAuthor;
	private String reimbAuthorName;
	private int reimbResolver; // 0 = null value in sql... so no resolver has been assinged
	private String reimbResolverName;
	private int reimbStatusId; // 0 = DENIED, 1 = ACCEPTED, 2 = OPEN
	private String reimbStatus; // value of the status primary key
	private int reimbTypeId; //0 = OTHER, 1 = FOOD, 2 = TRAVEL, 3 = LODGING
	private String reimbType; // value of the type primary key
	
	//no args constructor
	public Reimbursement()
	{
		
	}
	//all args constructor
	public Reimbursement(int reimbId, int reimbAmount, String reimbSubmitted, String reimbResolved,
			String reimbDescription, int reimbAuthor, String reimbAuthorName,  int reimbResolver, String reimbResolverName, int reimbStatusId, String reimbStatus,
			int reimbTypeId, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbAuthorName = reimbAuthorName;
		this.reimbResolver = reimbResolver;
		this.reimbResolverName = reimbResolverName;
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
		this.reimbType = reimbType;
	}
	
	
	public Reimbursement(int reimbId, float reimbAmount, String reimbSubmitted, String reimbResolved,
			String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusId, String reimbStatus,
			int reimbTypeId, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
		this.reimbType = reimbType;
	}
	/**
	 * This is used when creating a reimbursement ticket by the employee
	 *We dont need the id of the ticket becuase the database will assign that 
	 *We dont need the reimbSubmitted because the database will use its own timestamp to give the time it was added to the database
	 *We dont need the resolved time because this is a new ticket and has not been resolved.... A ticket cannot be submitted and resolved in the same moment
	 *We dont need the resolver because of what was mention above
	 *We dont need the string types of the statusid or type id because the database has the look tables to associate to those values
	 * 
	 * 
	 * @param reimbAmount
	 * @param reimbDescription
	 * @param reimbAuthor
	 * @param reimbStatusId
	 * @param reimbTypeId
	 */
	public Reimbursement(int reimbAmount, String reimbDescription,
			int reimbAuthor, int reimbStatusId, int reimbTypeId) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
//		this.reimbAuthor = reimbAuthor;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
// 	this constructor will be used when a manager updates an reimbursement
	public Reimbursement(int reimbId, int reimbStatusId)
	{
		super();
		this.reimbId = reimbId;
		this.reimbStatusId = reimbStatusId;
//		this.reimbResolver = reimbResolver;
	}
	
	
	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public float getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(float reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(String reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public String getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(String reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public int getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public int getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
	@Override
	public String toString() {
		return "\n Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbAuthor=" + reimbAuthor + ", reimbAuthorName=" + reimbAuthorName + ", reimbResolver="
				+ reimbResolver + ", reimbResolverName=" + reimbResolverName + ", reimbStatusId=" + reimbStatusId
				+ ", reimbStatus=" + reimbStatus + ", reimbTypeId=" + reimbTypeId + ", reimbType=" + reimbType + "]";
	}


	
}



