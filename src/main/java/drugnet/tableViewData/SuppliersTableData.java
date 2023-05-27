package drugnet.tableViewData;

import java.sql.Timestamp;

public class SuppliersTableData {
    private int supplier_id;
    private String supplierName;
    private String supplierNumber;
    private String supplierLocation;
    private String supplierAddress;
    private String supplierComment;
    private Timestamp date_created;

    public SuppliersTableData(int supplier_id, String supplierName, String supplierNumber, String supplierLocation, String supplierAddress, String supplierComment, Timestamp date_created) {
        this.supplier_id = supplier_id;
        this.supplierName = supplierName;
        this.supplierNumber = supplierNumber;
        this.supplierLocation = supplierLocation;
        this.supplierAddress = supplierAddress;
        this.supplierComment = supplierComment;
        this.date_created = date_created;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierLocation() {
        return supplierLocation;
    }

    public void setSupplierLocation(String supplierLocation) {
        this.supplierLocation = supplierLocation;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierComment() {
        return supplierComment;
    }

    public void setSupplierComment(String supplierComment) {
        this.supplierComment = supplierComment;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }
}//end of clas...
