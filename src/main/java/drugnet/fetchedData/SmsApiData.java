package drugnet.fetchedData;

import drugnet.sms.SmsConfiguration;

import java.sql.Timestamp;


public class SmsApiData extends SmsConfiguration {
    int api_id;
    String sms_api;
    String sender_id;
    Timestamp date_created;

    public SmsApiData() {}

    public SmsApiData(int api_id, String sms_api, String sender_id, Timestamp date_created) {
        this.api_id = api_id;
        this.sms_api = sms_api;
        this.sender_id = sender_id;
        this.date_created = date_created;
    }

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getSms_api() {
        return sms_api;
    }

    public void setSms_api(String sms_api) {
        this.sms_api = sms_api;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }
}//end of class...
