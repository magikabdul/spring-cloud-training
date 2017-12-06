package pl.training.cloud.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.config.DepartmentsConfig;
import pl.training.cloud.users.model.ResultPage;
import pl.training.cloud.users.model.User;
import pl.training.cloud.users.repository.UsersRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UsersService {

    private DepartmentsConfig departmentsConfig;
    private UsersRepository usersRepository;
    private EventEmitter eventEmitter;

    @Autowired
    public UsersService(UsersRepository usersRepository, EventEmitter eventEmitter, DepartmentsConfig departmentsConfig) {
        this.usersRepository = usersRepository;
        this.eventEmitter = eventEmitter;
        this.departmentsConfig = departmentsConfig;
    }

    public void addUser(User user) {
        user.setDepartmentId(departmentsConfig.getDefaultDepartmentId()) ;
        Logger.getLogger(getClass().getName()).log(Level.INFO, "### Setting default department id: " + departmentsConfig.getDefaultDepartmentId());
        usersRepository.saveAndFlush(user);
        eventEmitter.emit(user);
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }

}
