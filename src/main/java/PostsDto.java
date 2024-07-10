public record PostsDto(
        int userId,
        int id,
        String title,
        String body
) {
    @Override
    public int userId() {
        return userId;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String body() {
        return body;
    }
}
