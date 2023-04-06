package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.google.GoogleCloudPage;


public class GoogleCloudCostTest extends CommonConditions {

    @Test
    public void theCostInTheCalculatorIsEqualToTheCostInTheMail() {
        boolean costMatches = new GoogleCloudPage(driver)
                .openPage()
                .fillInTheFields("4")
                .addToEstimate()
                .sendEstimateByEmail()
                .compareCostByEmailAndCalculator();

                Assert.assertTrue(costMatches, "Amounts vary!");
    }
}
