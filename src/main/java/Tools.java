import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Tools {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    public static void createNewUser() {
        String currentUri = BASE_URL + "/users";
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpUserRequest = null;
        try {
            httpUserRequest = HttpRequest.newBuilder(new URI(currentUri))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpUserRequest, HttpResponse.BodyHandlers.ofString());
            List<UserDto> responseDtos = objectMapper.readValue(httpResponse.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, UserDto.class));
            int maxId = 0;
            for (int i = 0; i < responseDtos.size(); i++) {
                if (responseDtos.get(i).id() > maxId) {
                    maxId = responseDtos.get(i).id();
                }
            }
            UserDto newUser = new UserDto(maxId);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonFormat = gson.toJson(newUser);
            try {
                HttpRequest createUserHttpRequest = HttpRequest.newBuilder(new URI(currentUri))
                        .POST(HttpRequest.BodyPublishers.ofString(jsonFormat))
                        .header("Content-type", "application/json")
                        .build();
                HttpResponse<String> createUserHttpResponse = httpClient.send(createUserHttpRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println(createUserHttpResponse.statusCode());
                UserDto createdUser = objectMapper.readValue(createUserHttpResponse.body(), UserDto.class);
                System.out.println(createdUser);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserData(int id, String nameToUpdate) throws URISyntaxException, IOException, InterruptedException {
        String currentUri = BASE_URL + "/users/" + id;
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpUpdateUserRequest = HttpRequest.newBuilder(new URI(currentUri))
                .GET()
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpUpdateUserRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.statusCode());
        UserDto responseDto = objectMapper.readValue(httpResponse.body(), UserDto.class);
        responseDto.setName(nameToUpdate);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonFormat = gson.toJson(responseDto);
        System.out.println(jsonFormat);
        HttpRequest updateUserRequest = HttpRequest.newBuilder(new URI(currentUri))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonFormat))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> updateUserHttpResponse = httpClient.send(updateUserRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(updateUserHttpResponse.statusCode());
        ;

        if (updateUserHttpResponse.statusCode() == 200) {
            System.out.println("User updated successfully");
        } else {
            System.out.println("Failed to update user: " + updateUserHttpResponse.body());
        }


    }

    public static void deleteUser(int id) throws URISyntaxException, IOException, InterruptedException {
        String currentUri = BASE_URL + "/users/" + id;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(currentUri))
                .DELETE()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (httpResponse.statusCode() == 200 || httpResponse.statusCode() == 204) {
            System.out.println("User deleted successfully");
        } else {
            System.out.println("Failed to delete user: " + httpResponse.body());
        }
    }

    public static void showAllUsers() {
        String currentUri = BASE_URL + "/users";
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest httpUserRequest = HttpRequest.newBuilder(new URI(currentUri))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpUserRequest, HttpResponse.BodyHandlers.ofString());
            List<UserDto> responseDtos = objectMapper.readValue(httpResponse.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, UserDto.class));
            responseDtos.forEach(response -> System.out.println(response.toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showUserById(int id) {
        String currentUri = BASE_URL + "/users/" + id;
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(currentUri))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showUserByUsername(String username) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(BASE_URL + "/users"))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<UserDto> responseDtos = objectMapper.readValue(httpResponse.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, UserDto.class));
            for (int i = 0; i < responseDtos.size(); i++) {
                if (responseDtos.get(i).username.equals(username)) {
                    System.out.println(responseDtos.get(i));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }

    public static void writeLastUsersPostComments(int userId) {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {

            //смотрим посты по юзер айди
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(BASE_URL + "/users/" + userId + "/posts"))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<PostsDto> responseDtos = objectMapper.readValue(httpResponse.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, PostsDto.class));

            //находим нужный пост
            int currentPost = responseDtos.get(responseDtos.size() - 1).id();
            String currentUri = BASE_URL + "/posts/" + currentPost + "/comments";

            httpRequest = HttpRequest.newBuilder(new URI(currentUri))
                    .GET()
                    .build();
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<CommentsDto> commentsDtos = objectMapper.readValue(httpResponse.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CommentsDto.class));


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonFormat = gson.toJson(commentsDtos);

            try (
                    FileOutputStream fos = new FileOutputStream("user-" + userId + "-post-" + currentPost + "-comments.json");
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            ) {
                bw.write(jsonFormat);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showOpenTasks(int userId) {
        try {
            String currentUri = BASE_URL + "/users/" + userId + "/todos";
            ObjectMapper objectMapper = new ObjectMapper();
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(currentUri))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            List<TodoDto> todoDtos = objectMapper.readValue(httpResponse.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, TodoDto.class));
            todoDtos.stream()
                    .filter(todo -> !todo.completed())
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
