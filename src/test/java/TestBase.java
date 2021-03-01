import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Step("Открываем страницу")
    public void openPage(String url) {
        open(url);
    }

    @Step("Поиск репозитория")
    public void searchRepository(String repository) {
        $(".header-search-input").setValue(repository).submit();
    }

    @Step("Получаем результаты поиска репозитория")
    public void resultsOfSearchRepository() {
        ElementsCollection resaultSearch = $$(".repo-list > li");
        String results = (resaultSearch.size()) + " repository results";
        $(withText(results)).should(visible);
    }

    @Step("Переходим в репозиторий")
    public void goToRepository(String repository) {
        $(By.linkText((repository))).click();
    }

    @Step("Переходим в Issues")
    public void goToIssues(String issues) {
        $(withText(issues)).click();
    }

    @Step("Переходим в закрытые Issues")
    public void goToClosedIssues() {
        $("#js-issues-search").setValue("is:issue is:closed").pressEnter();
    }

    @Step("Проверяем наличие Issue по номеру")
    public void checkIssueByNumber(String issueNumber) {
        $(withText(issueNumber)).should(visible);
    }

    @Step("Ищем Issue по названию в закрытых Issues")
    public void goToIssueByName(String issueName) {

        $(withText(issueName)).should(visible);
    }

}
