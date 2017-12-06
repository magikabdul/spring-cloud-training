package pl.training.cloud.users.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@RefreshScope
@Service
public class DepartmentsConfig {

    @Getter
    private Long defaultDepartmentId;

    @Value("${defaultDepartmentId}")
    public void setDefaultDepartmentId(Long defaultDepartmentId) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "### Setting new default department id: " + defaultDepartmentId);
        this.defaultDepartmentId = defaultDepartmentId;
    }

}
