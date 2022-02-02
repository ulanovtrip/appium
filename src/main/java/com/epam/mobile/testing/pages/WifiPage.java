package com.epam.mobile.testing.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WifiPage extends BasePage {

    @AndroidFindBy(id = "org.kman.WifiManager:id/native_ad_remove")
    private MobileElement turnOffAds;

    @AndroidFindBy(id = "org.kman.WifiManager:id/wifi_ap_features")
    private MobileElement statusWifi;

    @AndroidFindBy(id = "org.kman.WifiManager:id/wifi_ap_bssid")
    private MobileElement bssid;

    @AndroidFindBy(id = "org.kman.WifiManager:id/wifi_ap_signal_img")
    private MobileElement signalBtn;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'wifi_ap_')]")
    private List<MobileElement> wifiStatus;

    public WifiPage tapWifiSignalImg() {
        signalBtn.click();
        return this;
    }

    public WifiPage checkTextTurnOffAds() {
        turnOffAds.getText();
        return this;
    }

    public WifiPage checkStatusWifi() {
        statusWifi.getText();
        return this;
    }

    //метод для извлечения текста из листа wifiStatus
    public List<String> getWifiStatuses() {
        return wifiStatus.stream().map(RemoteWebElement::getText).collect(Collectors.toList());
    }
}
