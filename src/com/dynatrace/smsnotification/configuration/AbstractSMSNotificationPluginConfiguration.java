package com.dynatrace.smsnotification.configuration;

import com.dynatrace.diagnostics.pdk.ActionEnvironment;

public abstract class AbstractSMSNotificationPluginConfiguration {
    // Property Constants

    private static String PROPERTY_SERVICE_PROVIDER = "serviceProvider";
    private static String PROPERTY_HARDWARE_MODEL = "hardwareModel";
    private static String PROPERTY_HARDWARE_PORT = "hardwarePort";
    private static String PROPERTY_HARDWARE_BAUD_RATE = "hardwareBaudRate";
    private static String PROPERTY_HARDWARE_SIM_PIN = "hardwareSimPin";
    private static String PROPERTY_INTERNET_GATEWAY_API = "internetGatewayAPI";
    private static String PROPERTY_INTERNET_GATEWAY_USERNAME = "internetGatewayUsername";
    private static String PROPERTY_INTERNET_GATEWAY_PASSWORD = "internetGatewayPassword";
    private static String PROPERTY_SMS_FROM = "smsFrom";
    private static String PROPERTY_SMS_TO = "smsTo";

    // Properties
    private String mServiceProvider;
    private String mHardwareModel;
    private String mHardwarePort;
    private String mHardwareBaudRate;
    private String mHardwareSIMPIN;
    private String mInternetGatewayAPI;
    private String mInternetGatewayUserName;
    private String mInternetGatewayPassword;
    private String mSmsFrom;
    private String mSMSTo;

    AbstractSMSNotificationPluginConfiguration(ActionEnvironment pEnvironment) throws SMSNotificationPluginConfigurationException {
        // Read Configuration
        mServiceProvider = pEnvironment.getConfigString(PROPERTY_SERVICE_PROVIDER);
        mHardwareModel = pEnvironment.getConfigString(PROPERTY_HARDWARE_MODEL);
        mHardwarePort = pEnvironment.getConfigString(PROPERTY_HARDWARE_PORT);
        mHardwareBaudRate = pEnvironment.getConfigString(PROPERTY_HARDWARE_BAUD_RATE);
        mHardwareSIMPIN = pEnvironment.getConfigPassword(PROPERTY_HARDWARE_SIM_PIN);
        mInternetGatewayAPI = pEnvironment.getConfigString(PROPERTY_INTERNET_GATEWAY_API);
        mInternetGatewayUserName = pEnvironment.getConfigString(PROPERTY_INTERNET_GATEWAY_USERNAME);
        mInternetGatewayPassword = pEnvironment.getConfigPassword(PROPERTY_INTERNET_GATEWAY_PASSWORD);
        mSmsFrom = pEnvironment.getConfigString(PROPERTY_SMS_FROM);
        mSMSTo = pEnvironment.getConfigString(PROPERTY_SMS_TO);

        // Validate Configuration
        validateConfiguration();
    }

    public static AbstractSMSNotificationPluginConfiguration getInstance(ActionEnvironment pEnvironment) throws SMSNotificationPluginConfigurationException {
        if (pEnvironment.getConfigString(PROPERTY_SERVICE_PROVIDER).toLowerCase().contains("hardware")) {
            // Hardware SMS Configuration
            return new HardwareDeviceSMSConfiguration(pEnvironment);
        } else {
            // Internet Gateway SMS Configuration
            return new InternetGatewaySMSConfiguration(pEnvironment);
        }
    }

    abstract void validateConfiguration() throws SMSNotificationPluginConfigurationException;

    public abstract String getGatewayID();

    String getServiceProvider() {
        return mServiceProvider;
    }

    String getHardwareModel() {
        return mHardwareModel;
    }

    String getHardwarePort() {
        return mHardwarePort;
    }

    String getHardwareBaudRate() {
        return mHardwareBaudRate;
    }

    String getHardwareSIMPIN() {
        return mHardwareSIMPIN;
    }

    String getInternetGatewayAPI() {
        return mInternetGatewayAPI;
    }

    String getInternetGatewayUserName() {
        return mInternetGatewayUserName;
    }

    String getInternetGatewayPassword() {
        return mInternetGatewayPassword;
    }

    String getSMSFrom() {
        return mSmsFrom;
    }

    public String getSMSTo() {
        return mSMSTo;
    }

    public String transformMessage(String pMessageText) {
        return pMessageText;
    }

}
