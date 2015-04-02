package it.unical.mat.andlv.test;


import java.util.ArrayList;
import java.util.Arrays;

import it.unical.mat.andlv.base.mapper.Predicate;
import it.unical.mat.andlv.base.mapper.Term;


@Predicate("p")
public class Person {
	
	public Person() {
	}
	
	@Term(0)
	int age;
	
	@Term(1)
	String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
	

}
