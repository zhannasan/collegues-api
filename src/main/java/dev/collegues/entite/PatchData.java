package dev.collegues.entite;

public class PatchData {
	private String email;
	private String photoUrl;

	/**
	 * 
	 */
	public PatchData() {
		super();
	}

	/**
	 * @param email
	 * @param photoUrl
	 */
	public PatchData(String email, String photoUrl) {
		super();
		this.email = email;
		this.photoUrl = photoUrl;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * @param photoUrl
	 *            the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
