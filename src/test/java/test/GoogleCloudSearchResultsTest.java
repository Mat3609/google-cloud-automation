package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.google.GoogleCloudHomePage;

public class GoogleCloudSearchResultsTest extends CommonConditions{

    private final String searchQuery = "Google Cloud Platform Pricing Calculator";
    @Test(description = "JIRA-7566")
    public void commonSearchResultsNotEmpty() {
        int amountSearchResults = new GoogleCloudHomePage(driver)
                .openPage()
                .search(searchQuery)
                .getSearchResults();

        Assert.assertTrue(amountSearchResults > 0, "Search results are empty!");
    }
}
