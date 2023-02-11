package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.google.CloudGoogleHomePage;

public class GoogleCloudSearchResults extends CommonConditions{

    private final String searchQuery = "Google Cloud Platform Pricing Calculator";
    @Test(description = "JIRA-7566")
    public void commonSearchResultsNotEmpty() {
        int amountSearchResults = new CloudGoogleHomePage(driver)
                .openPage()
                .searchString(searchQuery)
                .getSearchResults();
        System.out.println(amountSearchResults);

        Assert.assertTrue(amountSearchResults > 0, "Search results are query!");
    }
}
