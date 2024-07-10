public record CompanyDto(
        String name,
        String catchPhrase,
        String bs
) {
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
