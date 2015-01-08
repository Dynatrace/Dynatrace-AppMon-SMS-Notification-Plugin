package com.dynatrace.smsnotification.configuration;

import com.dynatrace.diagnostics.pdk.ActionEnvironment;

public class HardwareDeviceSMSConfiguration extends AbstractSMSNotificationPluginConfiguration {

	HardwareDeviceSMSConfiguration(ActionEnvironment pEnvironment) {
		super(pEnvironment);
	}

	@Override
	void validateConfiguration() throws SMSNotificationPluginConfigurationException {
		// Hardware manufacturer
		if ((getHardwareManufacturer() == null) || getHardwareManufacturer().isEmpty()) {
			throw new SMSNotificationPluginConfigurationException("No hardware manufacturer selected");
		}

		// Hardware device model
		if ((getHardwareModel() == null) || getHardwareModel().isEmpty()) {
			throw new SMSNotificationPluginConfigurationException("No hardware device model selected");
		}

		// Hardware port
		if ((getHardwarePort() == null) || getHardwarePort().isEmpty()) {
			throw new SMSNotificationPluginConfigurationException("No hardware port selected");
		}

		// Hardware baud rate
		if ((getHardwareBaudRate() == null) || getHardwareBaudRate().isEmpty()) {
			throw new SMSNotificationPluginConfigurationException("No hardware baud rate");
		}

		// SMS recipient
		if ((getSMSTo() == null) || getSMSTo().isEmpty()) {
			throw new SMSNotificationPluginConfigurationException("No SMS recipient");
		}
	}

	@Override
	public String getGatewayID() {
		return "modem." + getHardwarePort() + "." + getHardwareSIMPIN() + "." + getHardwareBaudRate();
	}

	public String getHardwareManufacturer() {
		int separatorPosition = super.getHardwareModel().indexOf(" (");
		if (separatorPosition > -1) {
			return super.getHardwareModel().substring(0, separatorPosition);
		}
		return super.getHardwareModel();
	}

	@Override
	public String getHardwareModel() {
		int bracketPositionFrom = super.getHardwareModel().indexOf("(");
		int bracketPositionTo = super.getHardwareModel().indexOf(")");
		if ((bracketPositionFrom > -1) && (bracketPositionTo > -1)) {
			return super.getHardwareModel().substring(bracketPositionFrom + 1, bracketPositionTo);
		}
		return super.getHardwareModel();
	}

	@Override
	public String getHardwarePort() {
		return super.getHardwarePort();
	}

	@Override
	public String getHardwareBaudRate() {
		return super.getHardwareBaudRate();
	}

	@Override
	public String getHardwareSIMPIN() {
		return super.getHardwareSIMPIN();
	}

}
