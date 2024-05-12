module net.codejava.mongodb.laynezjourney {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.codejava.mongodb.laynezjourney to javafx.fxml;
    exports net.codejava.mongodb.laynezjourney;
}