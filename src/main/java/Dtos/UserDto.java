package Dtos;

public record UserDto(
    int id,
    String name,
    String username,
    String email,
    AddressDto address,
    String phone,
    String website,
    CompanyDto company
){
    public UserDto(String name, String username, String email, AddressDto address, String phone, String website, CompanyDto company) {
        this (0, name, username, email, address, phone, website, company);
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public AddressDto address() {
        return address;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String website() {
        return website;
    }

    @Override
    public CompanyDto company() {
        return company;
    }

    @Override
    public String toString() {
        return "Dtos.UserDto{" +
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



}
