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
import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.layout.VBox;
public class DisplaySystem extends Application {
    private ArrayList<Crop> cropList;
    private ArrayList<Tool> toolList;
    private Farmer farmer;
    private Farm farm;
    private int day;

    public DisplaySystem(int status[]){
        this.farmer = new Farmer();
        this.farm = new Farm(status);
        this.day = 1;

        this.cropList = new ArrayList<Crop>();
        this.cropList.add(new Turnip());
        this.cropList.add(new Carrot());
        this.cropList.add(new Potato());
        this.cropList.add(new Rose());
        this.cropList.add(new Tulips());
        this.cropList.add(new Sunflower());
        this.cropList.add(new Mango());
        this.cropList.add(new Apple());

        this.toolList = new ArrayList<Tool>();
        this.toolList.add(new Plow());
        this.toolList.add(new WateringCan());
        this.toolList.add(new Fertilizer());
        this.toolList.add(new Pickaxe());
        this.toolList.add(new Shovel());
    }

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

    boolean check = false; 

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
    public Tile clickTile (MouseEvent event, boolean check) throws IOException{
        ImageView source = (ImageView) event.getSource();
        Tile tile = null;
        if (!check){
            if (source.equals(Tile1)){
                displayTileInfo (1,1);
            } else if (source.equals(Tile2)){
                displayTileInfo (1,2);
            } else if (source.equals(Tile3)){
                displayTileInfo (1,3);
            } else if (source.equals(Tile4)){
                displayTileInfo (1,4);
            } else if (source.equals(Tile5)){
                displayTileInfo (1,5);
            } else if (source.equals(Tile6)){
                displayTileInfo (1,6);
            } else if (source.equals(Tile7)){
                displayTileInfo (1,7);
            } else if (source.equals(Tile8)){
                displayTileInfo (1,8);
            } else if (source.equals(Tile9)){
                displayTileInfo (1,9);
            } else if (source.equals(Tile10)){
                displayTileInfo (1,10);
            } else if (source.equals(Tile11)){
                displayTileInfo (2,1);
            } else if (source.equals(Tile12)){
                displayTileInfo (2,2);
            } else if (source.equals(Tile13)){
                displayTileInfo (2,3);
            } else if (source.equals(Tile14)){
                displayTileInfo (2,4);
            } else if (source.equals(Tile15)){
                displayTileInfo (2,5);
            } else if (source.equals(Tile16)){
                displayTileInfo (2,6);
            } else if (source.equals(Tile17)){
                displayTileInfo (2,7);
            } else if (source.equals(Tile18)){
                displayTileInfo (2,8);
            } else if (source.equals(Tile19)){
                displayTileInfo (2,9);
            } else if (source.equals(Tile20)){
                displayTileInfo (2,10);
            } else if (source.equals(Tile21)){
                displayTileInfo (3,1);
            } else if (source.equals(Tile22)){
                displayTileInfo (3,2);
            } else if (source.equals(Tile23)){
                displayTileInfo (3,3);
            } else if (source.equals(Tile24)){
                displayTileInfo (3,4);
            } else if (source.equals(Tile25)){
                displayTileInfo (3,5);
            } else if (source.equals(Tile26)){
                displayTileInfo (3,6);
            } else if (source.equals(Tile27)){
                displayTileInfo (3,7);
            } else if (source.equals(Tile28)){
                displayTileInfo (3,8);
            } else if (source.equals(Tile29)){
                displayTileInfo (3,9);
            } else if (source.equals(Tile30)){
                displayTileInfo (3,10);
            } else if (source.equals(Tile31)){
                displayTileInfo (4,1);
            } else if (source.equals(Tile32)){
                displayTileInfo (4,2);
            } else if (source.equals(Tile33)){
                displayTileInfo (4,3);
            } else if (source.equals(Tile34)){
                displayTileInfo (4,4);
            } else if (source.equals(Tile35)){
                displayTileInfo (4,5);
            } else if (source.equals(Tile36)){
                displayTileInfo (4,6);
            } else if (source.equals(Tile37)){
                displayTileInfo (4,7);
            } else if (source.equals(Tile38)){
                displayTileInfo (4,8);
            } else if (source.equals(Tile39)){
                displayTileInfo (4,9);
            } else if (source.equals(Tile40)){
                displayTileInfo (4,10);
            } else if (source.equals(Tile41)){
                displayTileInfo(5, 1);
            } else if (source.equals(Tile42)){
                displayTileInfo (5,2);
            } else if (source.equals(Tile43)){
                displayTileInfo (5,3);
            } else if (source.equals(Tile44)){
                displayTileInfo (5,4);
            } else if (source.equals(Tile45)){
                displayTileInfo (5,5);
            } else if (source.equals(Tile46)){
                displayTileInfo (5,6);
            } else if (source.equals(Tile47)){
                displayTileInfo (5,7);
            } else if (source.equals(Tile48)){
                displayTileInfo (5,8);
            } else if (source.equals(Tile49)){
                displayTileInfo (5,9);
            } else if (source.equals(Tile50)){
                displayTileInfo (5,10);
            }
        } else if (check){
            if (source.equals(Tile1)){
                tile = farm.getTile(1,1);
            } else if (source.equals(Tile2)){
                tile = farm.getTile(1,2);
            } else if (source.equals(Tile3)){
                tile = farm.getTile (1,3);
            } else if (source.equals(Tile4)){
                tile = farm.getTile(1,4);
            } else if (source.equals(Tile5)){
                tile = farm.getTile(1,5);
            } else if (source.equals(Tile6)){
                tile = farm.getTile (1,6);
            } else if (source.equals(Tile7)){
                tile = farm.getTile(1,7);
            } else if (source.equals(Tile8)){
                tile = farm.getTile(1,8);
            } else if (source.equals(Tile9)){
                tile = farm.getTile(1,9);
            } else if (source.equals(Tile10)){
                tile = farm.getTile(1,10);
            } else if (source.equals(Tile11)){
                tile = farm.getTile (2,1);
            } else if (source.equals(Tile12)){
                tile = farm.getTile(2,2);
            } else if (source.equals(Tile13)){
                tile = farm.getTile(2,3);
            } else if (source.equals(Tile14)){
                tile = farm.getTile (2,4);
            } else if (source.equals(Tile15)){
                tile = farm.getTile(2,5);
            } else if (source.equals(Tile16)){
                tile = farm.getTile (2,6);
            } else if (source.equals(Tile17)){
                tile = farm.getTile (2,7);
            } else if (source.equals(Tile18)){
                tile = farm.getTile (2,8);
            } else if (source.equals(Tile19)){
                tile = farm.getTile(2,9);
            } else if (source.equals(Tile20)){
                tile = farm.getTile (2,10);
            } else if (source.equals(Tile21)){
                tile = farm.getTile(3,1);
            } else if (source.equals(Tile22)){
                tile = farm.getTile(3,2);
            } else if (source.equals(Tile23)){
                tile = farm.getTile (3,3);
            } else if (source.equals(Tile24)){
                tile = farm.getTile (3,4);
            } else if (source.equals(Tile25)){
                tile = farm.getTile(3,5);
            } else if (source.equals(Tile26)){
                tile = farm.getTile(3,6);
            } else if (source.equals(Tile27)){
                tile = farm.getTile (3,7);
            } else if (source.equals(Tile28)){
                tile = farm.getTile(3,8);
            } else if (source.equals(Tile29)){
                tile = farm.getTile(3,9);
            } else if (source.equals(Tile30)){
                tile = farm.getTile (3,10);
            } else if (source.equals(Tile31)){
                tile = farm.getTile(4,1);
            } else if (source.equals(Tile32)){
                tile = farm.getTile(4,2);
            } else if (source.equals(Tile33)){
                tile = farm.getTile(4,3);
            } else if (source.equals(Tile34)){
                tile = farm.getTile(4,4);
            } else if (source.equals(Tile35)){
                tile = farm.getTile(4,5);
            } else if (source.equals(Tile36)){
                tile = farm.getTile(4,6);
            } else if (source.equals(Tile37)){
                tile = farm.getTile(4,7);
            } else if (source.equals(Tile38)){
                tile = farm.getTile(4,8);
            } else if (source.equals(Tile39)){
                tile = farm.getTile(4,9);
            } else if (source.equals(Tile40)){
                tile = farm.getTile (4,10);
            } else if (source.equals(Tile41)){
                tile = farm.getTile(5, 1);
            } else if (source.equals(Tile42)){
                tile = farm.getTile(5,2);
            } else if (source.equals(Tile43)){
                tile = farm.getTile(5,3);
            } else if (source.equals(Tile44)){
                tile = farm.getTile(5,4);
            } else if (source.equals(Tile45)){
                tile = farm.getTile (5,5);
            } else if (source.equals(Tile46)){
                tile = farm.getTile (5,6);
            } else if (source.equals(Tile47)){
                tile = farm.getTile(5,7);
            } else if (source.equals(Tile48)){
                tile = farm.getTile(5,8);
            } else if (source.equals(Tile49)){
                tile = farm.getTile(5,9);
            } else if (source.equals(Tile50)){
                tile = farm.getTile(5,10);
            }
        }
    }
 
    public void displayTileInfo (int row, int col) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream farmerstream = new FileInputStream("C:\\Boo\\0_Codes\\CCPROG3_MCO\\CCPROG3_MCO2\\src\\TileInfo.fxml");
        VBox cBox = (VBox) loader.load(farmerstream);
        Scene cscreen = new Scene (cBox);

        Stage tileWindow = new Stage();
        tileWindow.setScene(cscreen);
        tileWindow.initModality(Modality.WINDOW_MODAL);
        tileWindow.show();
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
        check = true;

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

    @FXML
    void HarvestCrop(MouseEvent event) {
        System.out.println("Harvest harvet");
    }

    public static void main(String[] args){
        Application.launch(args);


    } 

}
