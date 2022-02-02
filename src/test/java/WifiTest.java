import com.epam.mobile.testing.pages.WifiPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WifiTest {

    @Test
    public void checkSsidSection() {
        WifiPage wifiPage = new WifiPage();
//        wifiPage.tapWifiSignalImg()
//                .checkStatusWifi()
//                .checkTextTurnOffAds();

        SoftAssert softAssert = new SoftAssert();
        String first = String.valueOf(wifiPage.getWifiStatuses().indexOf(0));
        softAssert.assertAll();
    }
}
