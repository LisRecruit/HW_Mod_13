package Dtos;

public record TodoDto(
        int userId,
        int id,
        String title,
        Boolean completed
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
    public Boolean completed() {
        return completed;
    }
}
