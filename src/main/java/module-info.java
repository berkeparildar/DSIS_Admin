module DSIS.Admin.main {
    requires javafx.controls;
    requires javafx.fxml;
    opens dsis.admin to javafx.fxml;
    exports dsis.admin;
}