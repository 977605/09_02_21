import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class TestForIssuesSteps extends TestBase {

    private static final String URL = "http://github.com";
    private static final String ISSUE_NUMBER = "#68";
    private static final String ISSUE_NAME = "Listeners NamedBy";
    private static final String ISSUES = "Issues";
    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    void findIssueNumber() {

        step("Открываем страницу " + URL, () -> {
            open(URL);
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $("input[name='q']").click();
            $("input[name='q']").sendKeys(REPOSITORY);
            $("input[name='q']").pressEnter();
        });

        step("Получаем результаты поиска репозитория " + REPOSITORY, () -> {
            ElementsCollection resaultSearch = $$(".repo-list > li");
            String results = (resaultSearch.size()) + " repository results";

            $(withText(results)).should(Condition.exist);
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переходим в " + ISSUES, () -> {
            $(withText(ISSUES)).click();
        });

        step("Подтверждаем, что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText(ISSUE_NUMBER)).should(Condition.exist);
        });
    }

    @Test
    void findIssue() {

        step("Открываем страницу " + URL, () -> {
            open(URL);
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $("input[name='q']").click();
            $("input[name='q']").sendKeys(REPOSITORY);
            $("input[name='q']").pressEnter();
        });

        step("Получаем результаты поиска репозитория " + REPOSITORY, () -> {
            ElementsCollection resaultSearch = $$(".repo-list > li");
            String results = (resaultSearch.size()) + " repository results";

            $(withText(results)).should(Condition.exist);
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переходим в " + ISSUES, () -> {
            $(withText(ISSUES)).click();
        });

        step("Переходим в закрытые " + ISSUES, () -> {
            $("#js-issues-search").setValue("is:issue is:closed").pressEnter();
        });

        step("В закрытых Issues присутствует Issue с именем " + ISSUE_NAME, () -> {
            $(withText("Listeners NamedBy")).should(Condition.exist);
        });
    }

}
