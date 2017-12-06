package pl.training.cloud.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@RefreshScope
@Service
public class TestConfig {

    private Long defaultDepartmentId;

    public void setDefaultDepartmentId(@Value("#{defaultDepartmentId}") Long defaultDepartmentId) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, " ### Default department id: " + defaultDepartmentId);
        this.defaultDepartmentId = defaultDepartmentId;
    }

}
