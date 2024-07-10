public class AddressDto{
        String street;
        String suite;
        String city;
        String zipcode;
        GeoDto geo;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setGeo(GeoDto geo) {
        this.geo = geo;
    }

    public String street() {
        return street;
    }

    public String suite() {
        return suite;
    }

    public String city() {
        return city;
    }

    public GeoDto geo() {
        return geo;
    }

    public String zipcode() {
        return zipcode;
    }
}
