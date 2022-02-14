package pl.edu.wszib.expensetracker.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.expensetracker.database.IExpenseDAO;
import pl.edu.wszib.expensetracker.database.IUserDAO;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.expensetracker.controllers",
        "pl.edu.wszib.expensetracker.service",
        "pl.edu.wszib.expensetracker.session",
})
public class TestConfiguration {
    @Bean
    public IUserDAO userDAO() {
        return Mockito.mock(IUserDAO.class);
    }

    @Bean
    public IExpenseDAO expenseDAO() {
        return Mockito.mock(IExpenseDAO.class);
    }
}
