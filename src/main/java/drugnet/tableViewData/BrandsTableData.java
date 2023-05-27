package drugnet.tableViewData;

import java.sql.Timestamp;

public class BrandsTableData {
    int brand_id;
    String brandName;
    String manufacturerName;
    Timestamp date_created;

    public BrandsTableData(int brand_id, String brandName, String manufacturerName, Timestamp date_created) {
        this.brand_id = brand_id;
        this.brandName = brandName;
        this.manufacturerName = manufacturerName;
        this.date_created = date_created;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }
}//end of class...
