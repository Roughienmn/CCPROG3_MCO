import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
public class DisplaySystem extends Application {
    @Override 
    public void start (Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String path = "C:\\Boo\\0_Codes\\CCPROG3_MCO\\CCPROG3_MCO2\\src\\DisplaySystem.fxml";
        FileInputStream stream = new FileInputStream(path);
        vBox = (VBox) loader.load(stream);
        Scene screen = new Scene (vBox);
        stage.setScene(screen);
        stage.show();
    }

    @FXML
    VBox vBox;

    @FXML 
    private ImageView  Tile1, Tile2, Tile3, Tile4, Tile5, 
            Tile6, Tile7, Tile8, Tile9, Tile10,
            Tile11, Tile12, Tile13, Tile14, Tile15,
            Tile16, Tile17, Tile18, Tile19, Tile20,
            Tile21, Tile22, Tile23, Tile24, Tile25, 
            Tile26, Tile27, Tile28, Tile29, Tile30,
            Tile31, Tile32, Tile33, Tile34, Tile35, 
            Tile36, Tile37, Tile38, Tile39, Tile40,
            Tile41, Tile42, Tile43, Tile44, Tile45, 
            Tile46, Tile47, Tile48, Tile49, Tile50; 
    
    @FXML
    private ImageView farmerFace;

    @FXML
    private Label PlayerCoins;

    @FXML
    private Label PlayerLevel;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private Label FarmerName;

    @FXML
    public void clickTile (MouseEvent event){
        ImageView source = (ImageView) event.getSource();

        if (source.equals(Tile9)){
            System.out.println("hebebehebhebehbe");
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }

}
