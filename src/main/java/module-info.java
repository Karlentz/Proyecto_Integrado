module net.codejava.mongodb.laynezjourney {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens controller to javafx.fxml;
    exports controller;
    exports controller.connection;
    opens controller.connection to javafx.fxml;
}