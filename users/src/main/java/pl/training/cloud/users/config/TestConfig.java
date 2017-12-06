package pl.training.cloud.users.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;

@RefreshScope
@Service
public class TestConfig {

    @Setter
    @Value("${defaultDepartmentId}")
    private Long defaultDepartmentId;

    @PostConstruct
    public void setDefaultDepartmentId() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, " ### Default department id: " + defaultDepartmentId);
    }

}
