package avlyakulov.timur.restapp.servicies;

import avlyakulov.timur.restapp.dto.ToDoDTO;
import avlyakulov.timur.restapp.exceptions.UserNotFoundException;
import avlyakulov.timur.restapp.models.ToDoModel;
import avlyakulov.timur.restapp.models.UserModel;
import avlyakulov.timur.restapp.repositories.ToDoRepository;
import avlyakulov.timur.restapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public ToDoDTO createToDo(ToDoModel toDoModel, Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            toDoModel.setOwner(user.get());
            return convertToToDoDTO(toDoRepository.save(toDoModel));
        } else {
            throw new UserNotFoundException("Пользователь с таким id не был найден, и ему не была установлена такая задача");
        }
    }

    public ToDoDTO completeToDo(Long id) {
        Optional<ToDoModel> todo = toDoRepository.findById(id);
        if (todo.isPresent()) {
            todo.get().setCompleted(!todo.get().getCompleted());
            return convertToToDoDTO(toDoRepository.save(todo.get()));
        } else {
            throw new RuntimeException("Данная задача не была найдена");
        }
    }

    public ToDoDTO convertToToDoDTO(ToDoModel toDoModel) {
        return modelMapper.map(toDoModel, ToDoDTO.class);
    }
}