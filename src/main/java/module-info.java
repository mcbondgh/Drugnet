module drugnet {
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires materialfx;
    requires javafx.controls;
    requires com.jfoenix;
    requires java.desktop;
    requires org.controlsfx.controls;

    opens drugnet to javafx.fxml;
    opens drugnet.controllers to javafx.fxml;
    opens drugnet.models;
    opens drugnet.tableViewData;
    opens drugnet.special_methods;
    opens drugnet.dbconfig;
    opens drugnet.views;

    exports drugnet.tableViewData;
    exports drugnet.special_methods;
    exports drugnet.dbconfig;
    exports drugnet;
    exports drugnet.models;
    exports drugnet.controllers;


}