import org.junit.jupiter.api.Test;

public class TestForIssuesSteps {

    final TestBase steps = new TestBase();

    private static final String URL = "http://github.com";
    private static final String ISSUE_NUMBER = "#68";
    private static final String ISSUE_NAME = "Listeners NamedBy";
    private static final String ISSUES = "Issues";
    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    void findIssueNumber() {

        steps.openPage(URL);
        steps.searchRepository(REPOSITORY);
        steps.resultsOfSearchRepository();
        steps.goToRepository(REPOSITORY);
        steps.goToIssues(ISSUES);
        steps.checkIssueByNumber(ISSUE_NUMBER);

    }

    @Test
    void findIssue() {
        steps.openPage(URL);
        steps.searchRepository(REPOSITORY);
        steps.resultsOfSearchRepository();
        steps.goToRepository(REPOSITORY);
        steps.goToIssues(ISSUES);
        steps.goToClosedIssues();
        steps.goToIssueByName(ISSUE_NAME);

    }
}
