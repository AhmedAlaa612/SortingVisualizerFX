module com.example.dsafinalproject {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
            
    opens com.example.dsafinalproject to javafx.fxml;
    exports com.example.dsafinalproject;
}