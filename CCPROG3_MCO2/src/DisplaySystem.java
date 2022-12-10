import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.stage.Modality;
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
    private ImageView farmerFace, endBtn, registerBtn, MyStats,
                    Plow, WateringCan, Pickaxe, Fertilizer, Shovel,
                    Rose, Tulip, Sunflower, Turnip, Carrot, Potato, Mango, Apple,
                    FarmerDP, CropDP; 

    @FXML
    private Label PlayerCoins, PlayerLevel, GameDay, FarmerName,
                FertilizerBonusInfo, WaterBonusInfo, ExperienceNum, CostReduction, BonusEarnings,
                HarvestGrowth, WaterInfo, FertilizerInfo, CropType, CropName;

    @FXML
    private ProgressBar ProgressBar;
    

    @FXML
    public void clickTile (MouseEvent event){
        ImageView source = (ImageView) event.getSource();

        if (source.equals(Tile1)){
            System.out.println("hebebehebhebehbe");
        }
    }

    @FXML
    void endDay(MouseEvent event) {
        System.out.println("pindot end");
    }

    @FXML
    void registerFarmer(MouseEvent event) {
        System.out.println("reg baby");
    }

    @FXML
    void useTool(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();

        if (source.equals(WateringCan)){
            System.out.println("water!");
        } else if (source.equals(Pickaxe)){
            System.out.println("axe!");
        } else if (source.equals(Plow)){
            System.out.println("plow");
        } else if (source.equals(Shovel)){
            System.out.println("shovel");
        } else if (source.equals(Fertilizer)){
            System.out.println("fertilizer!");
        }
    }

    @FXML
    void displayStats (MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream farmerstream = new FileInputStream("C:\\Boo\\0_Codes\\CCPROG3_MCO\\CCPROG3_MCO2\\src\\FarmerInfo.fxml");
        VBox fBox = (VBox) loader.load(farmerstream);
        Scene fscreen = new Scene (fBox);

        Stage farmerWindow = new Stage();
        farmerWindow.setScene(fscreen);
        farmerWindow.initModality(Modality.WINDOW_MODAL);
        farmerWindow.show();
    }

    @FXML
    void plantSeed(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();

        if (source.equals(Tulip)){
            System.out.println("tulip");
        } else if (source.equals(Rose)){
            System.out.println("rose");
        } else if (source.equals(Sunflower)){
            System.out.println("sunf");
        } else if (source.equals(Potato)){
            System.out.println("potats");
        } else if (source.equals(Turnip)){
            System.out.println("turn");
        } else if (source.equals(Carrot)){
            System.out.println("car");
        } else if (source.equals(Mango)){
            System.out.println("man");
        } else if (source.equals(Apple)){
            System.out.println("apple");
        } 
    }

    public static void main(String[] args){
        Application.launch(args);


    } 

}
