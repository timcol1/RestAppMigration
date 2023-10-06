package avlyakulov.timur.restapp.controllers;

import avlyakulov.timur.restapp.models.ToDoModel;
import avlyakulov.timur.restapp.servicies.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ResponseEntity<?> createToDo(@RequestBody ToDoModel toDoModel, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(toDoService.createToDo(toDoModel, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<?> updateToDo(@RequestBody ToDoModel toDoModel, @PathVariable Long id) {
        try {

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }*/

    @PutMapping()
    public ResponseEntity<?> completeToDo(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(toDoService.completeToDo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}