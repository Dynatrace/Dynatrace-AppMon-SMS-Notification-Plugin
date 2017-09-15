// This file is to be used in conjunction with smslib.org library version 3.4.6 or compatible
//
// Software is licensed with the MIT-license, allowing you to use/change the code as you see fit
// created by sbakker www.ictcore.biz
//
/////////////////////
// Copyright (c) 2017 IctCoreBiz
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without
// restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following
// conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.
/////////////////////
package org.smslib.http;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.TimeoutException;
import org.smslib.OutboundMessage.FailureCauses;
import org.smslib.OutboundMessage.MessageStatuses;
import org.smslib.helper.Logger;

public class SpryngHTTPGateway extends HTTPGateway {

    Object SYNC_Commander;

    final String providerUrl = "https://www.spryng.nl";

    String username, password, route;

    public SpryngHTTPGateway(String id, String myUsername, String myPassword, String myRoute) {
        super(id);
        this.username = myUsername;
        this.password = myPassword;
        this.route = myRoute;
        this.SYNC_Commander = new Object();
        setAttributes(77);
    }

    @Override
    public void startGateway() throws TimeoutException, GatewayException, IOException, InterruptedException {
        getService().getLogger().logInfo("Starting gateway.", null, getGatewayId());
        super.startGateway();
    }

    @Override
    public void stopGateway() throws TimeoutException, GatewayException, IOException, InterruptedException {
        getService().getLogger().logInfo("Stopping gateway.", null, getGatewayId());
        super.stopGateway();
    }

    @Override
    public float queryBalance() throws TimeoutException, GatewayException, IOException, InterruptedException {
        URL url = null;
        List<HttpHeader> request = new ArrayList<HttpHeader>();
        List<String> response;
        url = new URL(this.providerUrl + "/check.php?username=" + this.username + "&password=" + this.password);
        synchronized (this.SYNC_Commander) {
            response = HttpPost(url, request);
        }
        return Float.parseFloat(response.get(0));
    }

    @Override
    public boolean sendMessage(OutboundMessage msg) throws TimeoutException, GatewayException, IOException, InterruptedException {
        URL url = null;
        List<HttpHeader> request = new ArrayList<HttpHeader>();
        List<String> response;
        boolean ok = false;
        StringBuilder completeURL = new StringBuilder();
        completeURL.append(this.providerUrl + "/send.php");
        completeURL.append("?OPERATION=SEND&USERNAME=");
        completeURL.append(this.username);
        completeURL.append("&PASSWORD=");
        completeURL.append(this.password);
        completeURL.append("&SERVICE=Dynatrace");
        completeURL.append("&SENDER=");
        completeURL.append(this.getFrom());
        completeURL.append("&ROUTE=");
        completeURL.append(this.route);
        // By default Spryng has LONG messages disallowed, to allow it set the below param to 1
        // then also change the below max size of the message
        completeURL.append("&ALLOWLONG=0");
        completeURL.append("&DESTINATION=");
        completeURL.append(msg.getRecipient());
        completeURL.append("&BODY=");
        String message = msg.getText();
        //below length of String can be up to 612 when using ALLOWLONG=1
        completeURL.append(message.substring(0, Math.min(message.length(), 160)));
        url = new URL(completeURL.toString());
        synchronized (this.SYNC_Commander) {
            response = HttpPost(url, request);
        }
        if (response.get(0).equals("1")) {
            msg.setDispatchDate(new Date());
            msg.setGatewayId(getGatewayId());
            msg.setMessageStatus(MessageStatuses.SENT);
            incOutboundMessageCount();
            ok = true;
        } else {
            getService().getLogger().logError("Error sending message. Response: " + response.get(0), null, getGatewayId());
            switch (Integer.parseInt(response.get(0))) {
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                    msg.setFailureCause(FailureCauses.BAD_FORMAT);
                    break;
                case 200:
                case 202:
                    msg.setFailureCause(FailureCauses.GATEWAY_AUTH);
                    break;
                case 201:
                    msg.setFailureCause(FailureCauses.NO_ROUTE);
                    break;
                case 203:
                    msg.setFailureCause(FailureCauses.NO_CREDIT);
                    break;
                default:
                    msg.setFailureCause(FailureCauses.GATEWAY_FAILURE);
                    break;
            }
            msg.setRefNo(null);
            msg.setDispatchDate(null);
            msg.setMessageStatus(MessageStatuses.FAILED);
            ok = false;
        }
        return ok;
    }
}
