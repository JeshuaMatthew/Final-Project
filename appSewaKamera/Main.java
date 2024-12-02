package appSewaKamera;

import appSewaKamera.config.Database;
import appSewaKamera.views.MenuView;
import appSewaKamera.views.TerminalView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "appSewaKamera")
public class Main{
    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MenuView terminalView = context.getBean(TerminalView.class);
        terminalView.run();

    }

    @Bean
    Database database (){
        Database database = new Database("bisnis_sewa_kamera", "root","", "localhost","3306");
        database.setup();
        return database;
    }



}
