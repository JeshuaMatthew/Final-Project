import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import todoapp.config.Database;
import todoapp.views.TodoListView;


@ComponentScan(basePackages = "todoapp")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TodoListView todoListView = context.getBean(TodoListView.class);
        todoListView.run();
    }

    @Bean
    Database database(){
        Database database = new Database("my_database", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}
