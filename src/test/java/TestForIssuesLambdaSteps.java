import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

public class TestForIssuesLambdaSteps extends TestBase {

    private static final String URL = "http://github.com";
    private static final String ISSUE_NUMBER = "#68";
    private static final String ISSUE_NAME = "Listeners NamedBy";
    private static final String ISSUES = "Issues";
    private static final String REPOSITORY = "eroshenkoam/allure-example";


    @Test
    @Link(URL)
    @DisplayName("Поиск Issue по номеру")
    @Tags({@Tag("web"), @Tag("critical")})
    @Owner("natalia")
    @Feature("Issues")
    @Story("Поиск Issue по номеру")
    void findIssueNumber() {
        parameter("Repository", REPOSITORY);
        parameter("Issue number", ISSUE_NUMBER);

        step("Открываем страницу " + URL, () -> {
            open(URL);
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY).submit();
        });

        step("Получаем результаты поиска репозитория " + REPOSITORY, () -> {
            ElementsCollection resaultSearch = $$(".repo-list > li");
            String results = (resaultSearch.size()) + " repository results";

            $(withText(results)).should(visible);
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переходим в " + ISSUES, () -> {
            $(withText(ISSUES)).click();
        });

        step("Подтверждаем, что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText(ISSUE_NUMBER)).should(visible);
        });
    }

    @Test
    @Link(URL)
    @DisplayName("Поиск закрытой Issue по названию")
    @Tags({@Tag("web"), @Tag("critical")})
    @Severity(SeverityLevel.BLOCKER)
    @Owner("natalia")
    @Feature("Issues")
    @Story("Поиск закрытой Issue по названию")
    void findIssue() {
        parameter("Repository", REPOSITORY);
        parameter("Issue name", ISSUE_NAME);

        step("Открываем страницу " + URL, () -> {
            open(URL);
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY).submit();
        });

        step("Получаем результаты поиска репозитория " + REPOSITORY, () -> {
            ElementsCollection resaultSearch = $$(".repo-list > li");
            String results = (resaultSearch.size()) + " repository results";

            $(withText(results)).should(visible);
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
            $(withText("Listeners NamedBy")).should(visible);
        });
    }

}
