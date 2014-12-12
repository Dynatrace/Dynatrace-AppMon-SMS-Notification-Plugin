# SMS Notification Plugin

## Overview

![images_community/download/attachments/45777033/icon.png](images_community/download/attachments/45777033/icon.png)

The dynaTrace SMS Notification Plugin enables dynaTrace to send notifications, such as alerts of incidents, via SMS to cell phones.The plugin is based on the open source project
[SMSLib](http://smslib.org/), wich provides a flexible communication API, good support for hardware devices and also support for internet SMS gateway services.

The following outbound technologies are supported:

  * Hardware Devices (USB, Serial Port, Bluetooth. See L[ist of Known Compatible Devices](http://smslib.org/doc/compatibility/)) 

  * [Clickatell](http://www.clickatell.com), Internet Gatway 

  * [BulkSMS](http://www.bulksms.com), Internet Gateway 

## Plugin Details

| Name | SMS Notification Plugin
| :--- | :---
| Author | Christian Grimm (christian.grimm@dynatrace.com)
| Supported dynaTrace Versions | >= 5.5
| License | [dynaTrace BSD](dynaTraceBSD.txt)
| Support | [Not Supported](https://community.compuwareapm.com/community/display/DL/Support+Levels)
| Release History | 2011-03-10 Initial Release
| Download | [dynaTrace SMS Notification Plugin 0.8.5](com.dynatrace.diagnostics.plugins.SMSNotification_0.8.5.jar)

## Installation

Import the Plugin into the dynaTrace Server. For details how to do this please refer to the [Online Documentation on Plugin Management](https://community.compuwareapm.com/community/display/DOCDT35/Plugin+Management).

## Configuration

The action plugin can be configured with three different setups:

### Hardware Device

The following screenshot shows an example configuration for the D-Link DWM-152 USB modem.

![images_community/download/attachments/45777033/hardwaresettings.png](images_community/download/attachments/45777033/hardwaresettings.png)

**Cickatell (Internet Gateway)**

Sample [Clickatell](http://www.clickatell.com) configuration:

![images_community/download/attachments/45777033/clickatellsettings.png](images_community/download/attachments/45777033/clickatellsettings.png)  
Please note that an API has to be created within the Clickatell administration web console.

### BulkSMS (Internet Gateway)

This shows an example of how the SMS Plugin can be configured to use the [BulkSMS](http://www.bulksms.com) internet gateway:

![images_community/download/attachments/45777033/bulksmssettings.png](images_community/download/attachments/45777033/bulksmssettings.png)

## Example SMS

![images_community/download/attachments/45777033/examplesms.png](images_community/download/attachments/45777033/examplesms.png)

## Troubleshooting

Please refer to the log file, which contains the full console output of the dynaTrace SMS Notification Plugin:

%dynaTraceInstallation%/log/server/com.dynatrace.test.com.dynatrace.diagnostics.plugins.SMSNotificationAction.0.0.log

## Feedback

Please provide feedback on this plugin either by commenting on this page or by comments on the [Community Plugins and Extensions](https://community.compuwareapm.com/community/display/DTFORUM/Community+Plugins+and+Extensions)

