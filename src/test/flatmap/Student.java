package test.flatmap;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private List<String> courses;
	 
	 
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	
	public void addCourse(String courseName) {
		if (courses==null) 
			courses=new ArrayList<>();
		
		courses.add(courseName);
	}
}
