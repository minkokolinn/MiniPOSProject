package test.flatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapSample {
	public static void main(String[] args) {
		Student s1=new Student();
		s1.setName("Kyaw Kyaw");
		s1.addCourse("Java SE");
		s1.addCourse("Spring");
		s1.addCourse("Kotlin");
		
		Student s2=new Student();
		s2.setName("Mg Mg");
		s2.addCourse("Java SE");
		s2.addCourse("Java EE");
		
		List<Student> students=new ArrayList<>();
		students.add(s1);
		students.add(s2);
		
		List<String> result=students //list of student ko return
			.stream()	//stream api ngone htr dk student 1 khu ko return
			.map(stu->stu.getCourses())	//stu htl ka List of course ko u htr like p, return pyn htr like p
			.flatMap(courses->courses.stream())//stream api ngone htr dk string course 1 khu ko return
			.distinct()	//JavaSE 2 khar ma paw yan
			.collect(Collectors.toList());
		
		result.forEach(System.out::println);
	}
}
