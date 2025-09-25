package com.app;

import java.util.Objects;

public class Member {
	private String memberId;
	private String name;
	private String email;
	
	public Member(String memberId, String name, String email) {
		this.memberId = memberId;
		this.name = name;
		this.email = email;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return memberId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(memberId, other.memberId);
	}
}
