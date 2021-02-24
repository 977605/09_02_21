import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
        $("input[name='q']").click();
        $("input[name='q']").sendKeys(REPOSITORY);
        $("input[name='q']").pressEnter();

        ElementsCollection resaultSearch = $$(".repo-list > li");

        String h = (resaultSearch.size()) + " repository results";

        $(withText(h)).should(Condition.exist);

        $(By.linkText(REPOSITORY)).click();
        $(withText(ISSUES)).click();
        $(withText(ISSUE_NUMBER)).should(Condition.exist);
    }

    @Test
    void findIssue() {

        open(URL);
        $("input[name='q']").click();
        $("input[name='q']").sendKeys(REPOSITORY);
        $("input[name='q']").pressEnter();
        $(withText(ISSUES)).should(Condition.exist);

        $(By.linkText(REPOSITORY)).click();
        $(withText(ISSUES)).should(Condition.exist);
        $(withText(ISSUES)).click();

        $("#js-issues-search").setValue("is:issue is:closed").pressEnter();

        $(withText("#60")).should(Condition.exist);

        ElementsCollection resaultIssuesClosed  =  $$("div[aria-label='Issues'] a[aria-label*='Link']");

        $(withText(resaultIssuesClosed.size()+" Closed")).should(Condition.exist);
        $(withText("Listeners NamedBy")).should(Condition.exist);
    }
}
