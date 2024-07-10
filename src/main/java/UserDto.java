public class UserDto{
    int id;
    String name;
    String username;
    String email;

    UserDto (){

    }
    UserDto (int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }

    AddressDto address;
    String phone;
    String website;
    CompanyDto company;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String username() {
        return username;
    }

    public AddressDto address() {
        return address;
    }

    public String email() {
        return email;
    }

    public String website() {
        return website;
    }

    public String phone() {
        return phone;
    }

    public CompanyDto company() {
        return company;
    }
}
