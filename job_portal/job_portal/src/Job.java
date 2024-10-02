class Job {
	private int id;
	private String password;
	private String company_name;
	private String role;
	private String salary_range;
	private String status;
	private String location;
	private int vacancy;
	private int points;

	// public Job() {
	// super();
	// }

	public Job(int id, String company_name, String role, String salary_range, String location, int vacancy,
			String status, String password, int points) {
		super();
		this.id = id;
		this.company_name = company_name;
		this.role = role;
		this.salary_range = salary_range;
		this.status = status;
		this.location = location;
		this.vacancy = vacancy;
		this.password = password;
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int point) {
		this.points = point;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getcompany_name() {
		return company_name;
	}

	public void setcompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalary() {
		return salary_range;
	}

	public void setSalary(String salary_range) {
		this.salary_range = salary_range;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

}