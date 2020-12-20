package dto;

public class UserLogin {
	public String userEmail;
	public String destinationUrl;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getDestinationUrl() {
		return destinationUrl;
	}
	public void setDestinationUrl(String destinationUrl) {
		this.destinationUrl = destinationUrl;
	}
	@Override
	public String toString() {
		return "UserLogin [userEmail=" + userEmail + ", destinationUrl=" + destinationUrl + "]";
	}
	
	
}
