package pojo;

public class GetCourses {

	private String instructor;
	private String url;
	private String services;
	private String linkedIn;
	private String expertise;
	//using object of another class
	private Courses courses;

	//	performing encapsulation
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getlinkedIn() {
		return linkedIn;
	}
	public void setlinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}



}
