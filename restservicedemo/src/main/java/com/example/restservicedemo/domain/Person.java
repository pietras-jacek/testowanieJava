package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	
	private long id;
	
	private String firstName;
	private int yob;
	
	public Person() {
	}

	public Person(String firstName, int yob) {
		this.firstName = firstName;
		this.yob = yob;
	}
	
	public Person(long id, String firstName, int yob) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.yob = yob;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + yob;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (yob != other.yob)
			return false;
		return true;
	}
	
}
