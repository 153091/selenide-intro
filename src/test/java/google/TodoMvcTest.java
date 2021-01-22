package google;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TodoMvcTest {

    @Test
    void completesTask() {
        //селенид ждет сам!, но мы для учебного примера указываем конкретное время
        Configuration.timeout = 6000;
        // open TodoMvc page
        System.setProperty("webdriver.chrome.driver", "/Users/user1234/Downloads/chromedriver");
        open("https://todomvc.com/examples/emberjs/");

        // add tasks: a, b , c
        // element(By.id("new-to do").setValue("a").pressEnter();
        element("#new-todo").setValue("a").pressEnter();
        element("#new-todo").setValue("b").pressEnter();
        element("#new-todo").setValue("c").pressEnter();

        // tasks should be a, b, c
        elements("#todo-list>li").shouldHave(CollectionCondition.exactTexts("a", "b", "c"));

        // toggle b
        // 1 среди всех задач
        // 2 найти задачу с текстом b
        // 3 найти её чек-бокс  4 кликнуть
        elements("#todo-list>li").findBy(Condition.exactText("b"))
                .find(".toggle").click();
        // completed tasks should be b
        elements("#todo-list>li").filterBy(Condition.cssClass("completed"))
                .shouldHave(CollectionCondition.exactTexts("b"));
        //active tasks should be a, b
        elements("#todo-list>li").filterBy(Condition.not(Condition.cssClass("completed")))
                .shouldHave(CollectionCondition.exactTexts("a", "c"));
    }
}
