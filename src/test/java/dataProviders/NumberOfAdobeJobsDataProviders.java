package dataProviders;

import org.testng.annotations.DataProvider;

public class NumberOfAdobeJobsDataProviders {
    // Data providers
    @DataProvider(name = "numberOfJobsDataProvider")
    public Object[][] adobeJobsDataProvider() {
        return new Object[][]{
                {10, true},
                {30, true},
                {100, false}
        };
    }
}
