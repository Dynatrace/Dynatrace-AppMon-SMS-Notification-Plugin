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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.HttpURLConnection;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.TimeoutException;
import org.smslib.OutboundMessage.FailureCauses;
import org.smslib.OutboundMessage.MessageStatuses;
import org.smslib.helper.Logger;

public class TGGHTTPGateway extends HTTPGateway {

    Object SYNC_Commander;

    final String providerUrl = "https://www.tgg.nl/sms/api/v2";

    String apikey;

    public TGGHTTPGateway(String id, String myInternetGatewayAPI) {
        super(id);
        this.apikey = myInternetGatewayAPI;
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
    public boolean sendMessage(OutboundMessage msg) throws TimeoutException, GatewayException, IOException, InterruptedException {
        URL url = null;
//        List<HttpHeader> request = new ArrayList<HttpHeader>();
        List<String> response;
        boolean ok = false;
        String messagetxt = msg.getText();
        String smsmessage = messagetxt.substring(0, Math.min(messagetxt.length(), 640));
        // No default JSON parser/builder, hence creating simple JSON message manually (one recipient suported)
        StringBuilder payload = new StringBuilder();
        payload.append("\n\n{\n");
        payload.append("\"Messages\": [{\n");
        payload.append("  \"Title\":\"Dynatrace SMS Notification Plugin\",\n");
        payload.append("  \"Body\":\"");
        payload.append(messagetxt);
        payload.append("\",\n");
        payload.append("  \"Recipients\": [{\n");
        payload.append("    \"PhoneNumber\": \"");
        payload.append(msg.getRecipient());
        payload.append("\"\n}]");
        payload.append("  }],\n");
        payload.append("  \"APIKey\": \"");
        payload.append(this.apikey);
        payload.append("\" \n}\n");

        url = new URL(this.providerUrl + "/message");
        synchronized (this.SYNC_Commander) {
            response = HttpPost(url, payload.toString());
        }
        // response contains a jsonstring like:
        // {"Items":[{"SMSID":12345678,"Success":1,"Message":"","SkippedNumbers":[]}]}
        // Success must be 1
        // No JSON Parser available, hence quick check
        if (response.get(0).contains("\"Success\":1")) {
            msg.setDispatchDate(new Date());
            msg.setGatewayId(getGatewayId());
            msg.setMessageStatus(MessageStatuses.SENT);
            incOutboundMessageCount();
            ok = true;
        } else {
            getService().getLogger().logError("Error sending message. Response: " + response.get(0), null, getGatewayId());
            msg.setFailureCause(FailureCauses.GATEWAY_FAILURE);
            msg.setRefNo(null);
            msg.setDispatchDate(null);
            msg.setMessageStatus(MessageStatuses.FAILED);
            ok = false;
        }
        return ok;
    }

    //// DEFAULT HttpPost does not allow for (JSON) Body sending,
    //// hence reimplemented based on default implementation of HTTPGateway
    List<String> HttpPost(URL url, String message)
            throws IOException {
        List<String> responseList = new ArrayList();

        getService().getLogger().logInfo("HTTP POST: " + url, null, getGatewayId());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(20000);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        con.connect();
        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
        out.write(message);
        out.flush();
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            responseList.add(line);
        }
        in.close();
        return responseList;
    }

}
