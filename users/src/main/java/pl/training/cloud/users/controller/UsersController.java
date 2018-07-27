package pl.training.cloud.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.cloud.users.dto.PageDto;
import pl.training.cloud.users.dto.UserDto;
import pl.training.cloud.users.model.Mapper;
import pl.training.cloud.users.model.ResultPage;
import pl.training.cloud.users.model.User;
import pl.training.cloud.users.service.DepartmentsService;
import pl.training.cloud.users.service.UsersService;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "users")
@RestController
public class UsersController {

    private UsersService usersService;
    private DepartmentsService departmentsService;
    private Mapper mapper;
    private UriBuilder uriBuilder = new UriBuilder();

    @Autowired
    public UsersController(UsersService usersService, @Qualifier("feignDepartmentsService") DepartmentsService departmentsService, Mapper mapper) {
        this.usersService = usersService;
        this.departmentsService = departmentsService;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        usersService.addUser(user);
        URI uri = uriBuilder.requestUriWithId(user.getId());
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageDto<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") int pageNumber,
            @RequestParam(required = false, defaultValue = "10", name = "pageSize") int pageSize) {
        ResultPage<User> resultPage = usersService.getUsers(pageNumber, pageSize);
        List<UserDto> usersDtos = mapper.map(resultPage.getContent(), UserDto.class);
        usersDtos.forEach(this::mapDepartmentName);
        return new PageDto<>(usersDtos, resultPage.getPageNumber(), resultPage.getTotalPages());
    }

    private void mapDepartmentName(UserDto userDto) {
        departmentsService.getDepartmentById(userDto.getDepartmentId())
                .ifPresent(department -> userDto.setDepartmentName(department.getName()));
    }

}
