package pl.training.cloud.users.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.users.model.Department;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AutoBalancedDepartmentsService implements DepartmentsService {

    private static final String RESOURCE_URI = "http://departments-microservice/departments/";

    @NonNull
    private RestTemplate restTemplate;

    @Cacheable(value = "departments", unless = "#result == null")
    @Override
    public Optional<Department> getDepartmentById(Long id) {
        try {
            return Optional.of(restTemplate.getForObject(RESOURCE_URI + id, Department.class));
        } catch (HttpClientErrorException ex) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "### Fetching department failed");
        }
        return Optional.empty();
    }

}
