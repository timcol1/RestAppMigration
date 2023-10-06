package avlyakulov.timur.restapp.dto;


import avlyakulov.timur.restapp.models.ToDoModel;

import java.util.List;

//класс создан чтоб разграничить серверную модель и dto. Мы не хотим показывать пользователю пароль
public class UserDTO {
    private Long id;

    private String username;

    private List<ToDoDTO> todosUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ToDoDTO> getTodosUser() {
        return todosUser;
    }

    public void setTodosUser(List<ToDoDTO> todosUser) {
        this.todosUser = todosUser;
    }
}