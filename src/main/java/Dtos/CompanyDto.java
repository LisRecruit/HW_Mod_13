package Dtos;

public record CompanyDto(
        String name,
        String catchPhrase,
        String bs
) {
    public CompanyDto(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String bs() {
        return bs;
    }

    @Override
    public String catchPhrase() {
        return catchPhrase;
    }
}
