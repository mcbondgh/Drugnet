package drugnet.sms;
import drugnet.fetchedData.SmsApiData;
import drugnet.models.MainModel;

import java.net.*;
import java.io.*;
public class SmsConfiguration extends MainModel {
    /**
     * @author BulkSMS Ghana
     */
    private final String API_key = getSmsApi().get(1).toString();
    private final String sender_id = getSmsApi().get(2).toString(); //11 characters
    private URL url;

    public String SendMessage(String message, String mobile_number) throws Exception {
        url = new URL("http://clientlogin.bulksmsgh.com/smsapi?key=" + API_key + "&to=" + mobile_number + "&msg=" + message + "&sender_id=" + sender_id);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine = in.readLine().trim();
        String status = "";
        switch (inputLine) {
            case "1000" -> status = "Message Sent";
            case "1002" -> status = "Message not sent";
            case "1003" -> status = "You don't have enough balance";
            case "1004" -> status = "Invalid API Key";
            case "1005" -> status = "Phone number not valid";
            case "1006" -> status = "Invalid Sender ID";
            case "1008" -> status = "Empty message";
        }
        in.close();
        return status;
    }
    public String getSmsBalance() throws Exception {
        String balance = "";
         url = new URL("http://clientlogin.bulksmsgh.com/api/smsapibalance?key=" + API_key);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        balance = in.readLine().trim();
        in.close();
        return balance;
    }

}//end of class...
