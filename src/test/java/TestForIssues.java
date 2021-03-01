import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestForIssues extends TestBase {

    private static final String URL = "http://github.com";
    private static final String ISSUE_NUMBER = "#68";
    private static final String ISSUES = "Issues";
    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    void findIssueNumber() {

        open(URL);
        $(".header-search-input").setValue(REPOSITORY).submit();

        ElementsCollection resaultSearch = $$(".repo-list > li");

        String h = (resaultSearch.size()) + " repository results";

        $(withText(h)).should(visible);

        $(By.linkText(REPOSITORY)).click();
        $(withText(ISSUES)).click();
        $(withText(ISSUE_NUMBER)).should(visible);
    }

    @Test
    void findIssue() {

        open(URL);
        $(".header-search-input").setValue(REPOSITORY).submit();
        $(withText(ISSUES)).should(visible);

        $(By.linkText(REPOSITORY)).click();
        $(withText(ISSUES)).should(visible);
        $(withText(ISSUES)).click();

        $("#js-issues-search").setValue("is:issue is:closed").pressEnter();

        $(withText("#60")).should(visible);

        ElementsCollection resaultIssuesClosed = $$("div[aria-label='Issues'] a[aria-label*='Link']");

        $(withText(resaultIssuesClosed.size() + " Closed")).should(Condition.exist);
        $(withText("Listeners NamedBy")).should(visible);
    }
}
