package avlyakulov.timur.restapp.servicies;

import avlyakulov.timur.restapp.dto.UserDTO;
import avlyakulov.timur.restapp.exceptions.UserAlreadyExistException;
import avlyakulov.timur.restapp.exceptions.UserNotFoundException;
import avlyakulov.timur.restapp.models.UserModel;
import avlyakulov.timur.restapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserModel registration(UserModel user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepository.save(user);
    }

    public UserDTO findById(Long id) {
        Optional<UserModel> userFoundById = userRepository.findById(id);
        if (userFoundById.isPresent()) {
            return convertToUserDTO(userFoundById.get());
        } else {
            throw new UserNotFoundException("Пользователь с таким id не был найден");
        }
    }

    public void deleteUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent())
            userRepository.deleteById(id);
        else {
            throw new UserNotFoundException("Пользователь с таким id не был найден и удаление не было выполнено");
        }
    }

    public UserDTO convertToUserDTO(UserModel userModel) {
        return modelMapper.map(userModel, UserDTO.class);
    }
}