public record CommentsDto(
        int postId,
        int id,
        String name,
        String email,
        String body
) {
    @Override
    public int postId() {
        return postId;
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
    public String body() {
        return body;
    }
}
