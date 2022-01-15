package com.epam.mobile.testing.driver;

import com.epam.mobile.testing.configuration.AddressConfigurator;
import com.epam.mobile.testing.configuration.CapabilitiesConfigurator;
import com.epam.mobile.testing.configuration.ConfigurationReader;
import com.epam.mobile.testing.configuration.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class DriverManager {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE =
            EnvironmentType.valueOf(ConfigurationReader.get().env().toUpperCase());
    private static AppiumDriver<MobileElement> driver;

    private DriverManager() {

    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static AppiumDriver<MobileElement> createDriver() {
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                driver = new AndroidDriver<>(
                        AddressConfigurator
                                .getAppiumDriverLocalService(
                                        ConfigurationReader
                                                .get()
                                                .appiumPort()),
                        CapabilitiesConfigurator
                                .getLocalCapabilities());
                break;
            default:
                throw new IllegalArgumentException(format("Unexpected environment value: %s", ENVIRONMENT_TYPE));
        }
        LOG.info("Driver is created");
        LOG.info("Environment type is {}", ENVIRONMENT_TYPE);
        return driver;
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            LOG.info("Driver is closed");
        });
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.get().udid()));
            LOG.info("AVD is closed");
        } catch (IOException e) {
            LOG.info("AVD was not closed, message: {}", e.getMessage());
        }
    }
}
