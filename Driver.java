import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        int x[] =   {1, 1, 1, 1, 1,
                    0, 1, 1, 1, 0,
                    1, 0, 0, 1, 1,
                    0, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    0, 1, 1, 1, 0,
                    1, 0, 0, 1, 1,
                    0, 1, 1, 1, 1,
                    1, 1, 1, 1, 1};
        while(!exit){
        GameSystem g = new GameSystem(x);
        g.displayStart();
        g.inputPrompt();
            int choice = input.nextInt();
            if(choice == 1){
                while(!g.checkGameOver()){
                    g.displayHeader();
                    System.out.println();
                    g.displayFarm();
                    g.displayMainOptions();
                    choice = input.nextInt();
                    input.nextLine();
                    if(choice == 1){ //tile options
                        System.out.println("\n[SELECT TILE]");
                        System.out.print("Input row: ");
                        int row = input.nextInt();
                        System.out.print("Input column: ");
                        int col = input.nextInt();
                        input.nextLine();
                        g.displayTileOptions(row, col);

                        String choice1 = input.nextLine();
                        if(choice1.equals("T")){
                            g.displayToolOptions(row, col);
                            String toolInput = input.nextLine();
                            g.useTool(row, col, toolInput);
                        }
                        else if(choice1.equals("P")){
                            int allowed = g.displayCropOptions(row, col);
                            if(allowed == 1){
                                String seedIndex = input.nextLine();
                                int result = g.plantSeed(row, col, g.generateSeed(seedIndex));
                                if(result == 0){
                                    System.out.println("Planting was not successful.");
                                }
                                else{
                                    System.out.println("Planting was successful.");
                                }
                            }
                            else System.out.println("Cannot plant there. ");
                        }
                        else if(choice1.equals("H")){
                            g.harvestTile(row, col);
                        }
                    }

                    if(choice == 2){ //next day
                        g.nextDay();
                    }
                    
                    if(choice == 3){ //farmer options
                        g.displayFarmerOptions();
                        g.inputPrompt();
                        choice = input.nextInt();
                        if(choice == 1) g.registerFarmer();
                        if(choice == 2) g.displayFarmerStats();
                    }
                }
                
                System.out.println("Game over");
            }
            else if (choice == 2) exit = true;
        }

        input.close();
    }
}
