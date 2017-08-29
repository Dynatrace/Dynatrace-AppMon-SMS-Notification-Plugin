package com.dynatrace.smsnotification.configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.dynatrace.diagnostics.pdk.ActionEnvironment;

public class InternetGatewaySMSConfiguration extends AbstractSMSNotificationPluginConfiguration {

    public enum InternetSMSGatewayServiceProvider {
        BulkSMS,
        Clickatell,
        Spryng
    }

    InternetSMSGatewayServiceProvider gatewayServiceProvider;

    InternetGatewaySMSConfiguration(ActionEnvironment pEnvironment) {
        super(pEnvironment);

        // Set SMS Gateway service provider
        String serviceProvider = getServiceProvider().toLowerCase();

        // BulkSMS
        if (serviceProvider.contains("bulksms")) {
            gatewayServiceProvider = InternetSMSGatewayServiceProvider.BulkSMS;
        } // Clickatell
        else if (serviceProvider.contains("clickatell")) {
            gatewayServiceProvider = InternetSMSGatewayServiceProvider.Clickatell;
        } // Clickatell
        else if (serviceProvider.contains("spryng")) {
            gatewayServiceProvider = InternetSMSGatewayServiceProvider.Spryng;
        }
    }

    @Override
    void validateConfiguration() throws SMSNotificationPluginConfigurationException {
        // Internet gateway user name
        if ((getInternetGatewayUserName() == null) || getInternetGatewayUserName().isEmpty()) {
            throw new SMSNotificationPluginConfigurationException("No value for internet gateway user name specified");
        }

        // Internet gateway password
        if ((getInternetGatewayPassword() == null) || getInternetGatewayPassword().isEmpty()) {
            throw new SMSNotificationPluginConfigurationException("No value for internet gateway password specified");
        }

        // Internet gateway API (Clickatell only)
        if ((gatewayServiceProvider != null) && (gatewayServiceProvider.equals(InternetSMSGatewayServiceProvider.Clickatell))) {
            // Internet gateway password
            if ((getInternetGatewayAPI() == null) || getInternetGatewayAPI().isEmpty()) {
                throw new SMSNotificationPluginConfigurationException("No value for internet gateway API specified");
            }
        }

        // SMS recipient
        if ((getSMSTo() == null) || getSMSTo().isEmpty()) {
            throw new SMSNotificationPluginConfigurationException("No SMS recipient");
        }

        // SMS Sender (Spryng only)
        if ((gatewayServiceProvider != null) && (gatewayServiceProvider.equals(InternetSMSGatewayServiceProvider.Spryng))) {
            if ((getSMSFrom() == null) || getSMSFrom().isEmpty()) {
                throw new SMSNotificationPluginConfigurationException("No SMS Sender name");
            }
        }
    }

    @Override
    public String getGatewayID() {
        return "internet." + gatewayServiceProvider.toString() + "." + getInternetGatewayAPI() + "." + getInternetGatewayUserName() + "." + getInternetGatewayPassword();
    }

    public InternetSMSGatewayServiceProvider getGatewayServiceProvider() {
        return gatewayServiceProvider;
    }

    @Override
    public String getInternetGatewayAPI() {
        return super.getInternetGatewayAPI();
    }

    @Override
    public String getInternetGatewayRoute() {
        return super.getInternetGatewayRoute();
    }

    @Override
    public String getInternetGatewayUserName() {
        return super.getInternetGatewayUserName();
    }

    @Override
    public String getSMSFrom() {
        return super.getSMSFrom();
    }

    @Override
    public String getInternetGatewayPassword() {
        return super.getInternetGatewayPassword();
    }

    @Override
    public String getSMSTo() {
        return super.getSMSTo();
    }

    @Override
    public String transformMessage(String pMessageText) {
        switch (gatewayServiceProvider) {
            case BulkSMS:
                // Apply URL encoding
                try {
                    // BulkSMS does not like square brackets ...
                    pMessageText = pMessageText.replaceAll("\\[", "(");
                    pMessageText = pMessageText.replaceAll("\\]", ")");
                    // ... and also a proper encoding is required
                    return URLEncoder.encode(pMessageText, "UTF-8");
                } catch (UnsupportedEncodingException pException) {
                }
                return pMessageText;
            case Clickatell:
                return pMessageText; // Not transformation required
            case Spryng:
                // Apply URL encoding
                try {
                    // ... and also a proper encoding is required
                    return URLEncoder.encode(pMessageText, "UTF-8");
                } catch (UnsupportedEncodingException pException) {
                }
                return pMessageText;
        }
        return super.transformMessage(pMessageText);
    }

}
