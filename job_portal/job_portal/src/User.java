
class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String education;
    private String preferences;
    private String experience;
    private String contactNo;
    private int points;

    public User(int id, String name, String email, String education, String preferences, String experience,
            String contact_no, String password, int point) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.education = education;
        this.preferences = preferences;
        this.experience = experience;
        this.contactNo = contact_no;
        this.points = point;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int point) {
        this.points = point;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getExperience() {
        return experience;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}