package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.google.CalculatorPage;


public class GoogleCloudCostTest extends CommonConditions {

    @Test
    public void theCostInTheCalculatorIsEqualToTheCostInTheMail() {
        boolean emailEqualsCalculator = new CalculatorPage(driver)
                .openCalcPage()
                .fillInTheFields("4")
                .addToEstimate()
                .sendEstimateByEmail()
                .compareCostByEmailAndCalculator();

                Assert.assertTrue(emailEqualsCalculator, "Amounts vary!");
    }
}
