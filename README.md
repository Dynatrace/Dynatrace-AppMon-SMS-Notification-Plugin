<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>SMS Notification Plugin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <meta content="Scroll Wiki Publisher" name="generator"/>
    <link type="text/css" rel="stylesheet" href="css/blueprint/liquid.css" media="screen, projection"/>
    <link type="text/css" rel="stylesheet" href="css/blueprint/print.css" media="print"/>
    <link type="text/css" rel="stylesheet" href="css/content-style.css" media="screen, projection, print"/>
    <link type="text/css" rel="stylesheet" href="css/screen.css" media="screen, projection"/>
    <link type="text/css" rel="stylesheet" href="css/print.css" media="print"/>
</head>
<body>
                <h1>SMS Notification Plugin</h1>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-Overview"  >
        <h2>Overview</h2>
    <p>
            <img src="images_community/download/attachments/45777033/icon.png" alt="images_community/download/attachments/45777033/icon.png" class="confluence-embedded-image" />
            </p>
    <p>
The dynaTrace SMS Notification Plugin enables dynaTrace to send notifications, such as alerts of incidents, via SMS to cell phones.The plugin is based on the open source project <a href="http://smslib.org/">SMSLib</a>, wich provides a flexible communication API, good support for hardware devices and also support for internet SMS gateway services.    </p>
    <p>
The following outbound technologies are supported:    </p>
<ul class=" "><li class=" ">    <p>
Hardware Devices (USB, Serial Port, Bluetooth. See L<a href="http://smslib.org/doc/compatibility/">ist of Known Compatible Devices</a>)    </p>
</li><li class=" ">    <p>
<a href="http://www.clickatell.com">Clickatell</a>, Internet Gatway    </p>
</li><li class=" ">    <p>
<a href="http://www.bulksms.com">BulkSMS</a>, Internet Gateway    </p>
</li></ul>    </div>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-PluginDetails"  >
        <h2>Plugin Details</h2>
    <div class="tablewrap">
        <table>
<thead class=" "></thead><tfoot class=" "></tfoot><tbody class=" ">    <tr>
            <td rowspan="1" colspan="1">
        <p>
Author    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
Christian Grimm (christian.grimm@dynatrace.com)    </p>
            </td>
        </tr>
    <tr>
            <td rowspan="1" colspan="1">
        <p>
dynaTrace Versions    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
dynaTrace 3.5    </p>
            </td>
        </tr>
    <tr>
            <td rowspan="1" colspan="1">
        <p>
License    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
<a href="attachments_5275722_2_dynaTraceBSD.txt">dynaTrace BSD</a>    </p>
            </td>
        </tr>
    <tr>
            <td rowspan="1" colspan="1">
        <p>
Support    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
<a href="https://community/display/DL/Support+Levels">Not Supported</a>    </p>
            </td>
        </tr>
    <tr>
            <td rowspan="1" colspan="1">
        <p>
Known Problems    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
    </p>
            </td>
        </tr>
    <tr>
            <td rowspan="1" colspan="1">
        <p>
Release History    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
2011-03-10 Initial Release    </p>
            </td>
        </tr>
    <tr>
            <td rowspan="1" colspan="1">
        <p>
Download    </p>
            </td>
                <td rowspan="1" colspan="1">
        <p>
<a href="https://community.compuwareapm.com/community/download/attachments/45777033/com.dynatrace.diagnostics.plugins.SMSNotification_0.8.5.jar?api=v2">dynaTrace SMS Notification Plugin 0.8.5</a>    </p>
            </td>
        </tr>
</tbody>        </table>
            </div>
    </div>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-Installation"  >
        <h2>Installation</h2>
    <p>
Import the Plugin into the dynaTrace Server. For details how to do this please refer to the <a href="SMS_Notification_Plugin.html">Online Documentation on Plugin Management</a>.    </p>
    </div>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-Configuration"  >
        <h2>Configuration</h2>
    <p>
The action plugin can be configured with three different setups:    </p>
    <div class="section-3"  id="45777033_SMSNotificationPlugin-HardwareDevice"  >
        <h3>Hardware Device</h3>
    <p>
The following screenshot shows an example configuration for the D-Link DWM-152 USB modem.    </p>
    <p>
            <img src="images_community/download/attachments/45777033/hardwaresettings.png" alt="images_community/download/attachments/45777033/hardwaresettings.png" class="confluence-embedded-image" />
            </p>
    <p>
    <span style="color: #4c5d64;">
<strong class=" ">Cickatell (Internet Gateway)</strong>    </span>
    </p>
    <p>
Sample <a href="http://www.clickatell.com">Clickatell</a> configuration:    </p>
    <p>
            <img src="images_community/download/attachments/45777033/clickatellsettings.png" alt="images_community/download/attachments/45777033/clickatellsettings.png" class="confluence-embedded-image" />
        <br/>Please note that an API has to be created within the Clickatell administration web console.    </p>
    </div>
    <div class="section-3"  id="45777033_SMSNotificationPlugin-BulkSMS%28InternetGateway%29"  >
        <h3>BulkSMS (Internet Gateway)</h3>
    <p>
This shows an example of how the SMS Plugin can be configured to use the <a href="http://www.bulksms.com">BulkSMS</a> internet gateway:    </p>
    <p>
            <img src="images_community/download/attachments/45777033/bulksmssettings.png" alt="images_community/download/attachments/45777033/bulksmssettings.png" class="confluence-embedded-image" />
            </p>
    </div>
    </div>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-ExampleSMS"  >
        <h2>Example SMS</h2>
    <p>
            <img src="images_community/download/attachments/45777033/examplesms.png" alt="images_community/download/attachments/45777033/examplesms.png" class="confluence-embedded-image" />
            </p>
    </div>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-Troubleshooting"  >
        <h2>Troubleshooting</h2>
    <p>
Please refer to the log file, which contains the full console output of the dynaTrace SMS Notification Plugin:    </p>
    <p>
%dynaTraceInstallation%/log/server/com.dynatrace.test.com.dynatrace.diagnostics.plugins.SMSNotificationAction.0.0.log    </p>
    </div>
    <div class="section-2"  id="45777033_SMSNotificationPlugin-Feedback"  >
        <h2>Feedback</h2>
    <p>
Please provide feedback on this plugin either by commenting on this page or by comments on the <a href="https://community/display/DTFORUM/Community+Plugins+and+Extensions">Community Plugins and Extensions</a>    </p>
    </div>
            </div>
        </div>
        <div class="footer">
        </div>
    </div>
</body>
</html>
