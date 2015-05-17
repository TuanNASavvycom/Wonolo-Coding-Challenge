package net.londatiga.android.instagram;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Instagram user
 * 
 * @author Lorensius W. L. T
 *
 */
public class InstagramUser implements Parcelable {
	public String id;
	public String username;
	public String fullName;
	public String profilPicture;
	public String accessToken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProfilPicture() {
		return profilPicture;
	}

	public void setProfilPicture(String profilPicture) {
		this.profilPicture = profilPicture;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.username);
		dest.writeString(this.fullName);
		dest.writeString(this.profilPicture);
		dest.writeString(this.accessToken);
	}

	public InstagramUser() {
	}

	private InstagramUser(Parcel in) {
		this.id = in.readString();
		this.username = in.readString();
		this.fullName = in.readString();
		this.profilPicture = in.readString();
		this.accessToken = in.readString();
	}

	public static final Parcelable.Creator<InstagramUser> CREATOR = new Parcelable.Creator<InstagramUser>() {
		public InstagramUser createFromParcel(Parcel source) {
			return new InstagramUser(source);
		}

		public InstagramUser[] newArray(int size) {
			return new InstagramUser[size];
		}
	};
}