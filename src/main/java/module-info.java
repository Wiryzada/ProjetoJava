module com.anime {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.anime to javafx.fxml;
    exports com.anime;
}
