package Document;

import Base.baseTests;
import Pages.DocumentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDocumentPrice extends baseTests {

    String expectedPrice = "$149";

    @Test
    public void ValidateDocumentPrice(){
        homePage.navigateToHome();
        DocumentPage documentPage = homePage.selectGetPaid();
        Assert.assertEquals(documentPage.getPrice(), expectedPrice, "Document price doesn't match");
    }

}
