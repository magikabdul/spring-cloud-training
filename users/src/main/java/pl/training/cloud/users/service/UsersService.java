package pl.training.cloud.users.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.model.ResultPage;
import pl.training.cloud.users.model.User;
import pl.training.cloud.users.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {

    @NonNull
    private UsersRepository usersRepository;
    @Setter
    @Value("${defaultDepartmentId}")
    private Long defaultDepartmentId;

    public void addUser(User user) {
        user.setDepartmentId(defaultDepartmentId) ;
        usersRepository.saveAndFlush(user);
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }

}
