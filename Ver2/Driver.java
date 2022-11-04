package Ver2;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int status[] = {1};

        while(!exit){
        System.out.println("My Farm");
        System.out.println("[1] Start");
        System.out.println("[2] Exit");
        System.out.print("\nInput: ");
        int choice = scanner.nextInt();

            if(choice == 1){
                GameSystem g = new GameSystem(status);
                boolean gameOver = false;
                while(!gameOver){
                    g.displayHeader();
                    g.displayFarm();
                    g.displayMainOptions();
                    choice = scanner.nextInt();

                    if(choice == 1){
                        System.out.print("\nSelect Row: ");
                        int row = scanner.nextInt();
                        System.out.print("Select Column: ");
                        int col = scanner.nextInt();
                        scanner.nextLine();

                        g.selectTileOptions(row, col);
                        char tileChoice = scanner.next().charAt(0);

                        if(tileChoice == 'T'){
                            g.displayToolOptions(row, col);
                            char toolChoice = scanner.next().charAt(0);

                            g.useTool(row, col, toolChoice);
                        }
                        else if(tileChoice == 'P'){
                            g.displayCropOptions(row, col);
                            char cropChoice = scanner.next().charAt(0);

                            g.plantSeed(row, col, cropChoice);
                        }
                        else if(tileChoice == 'H'){
                            g.harvestTile(row, col);
                        }
                        else if(tileChoice == 'I'){
                            g.displayTileInfo(row, col);
                        }
                    }
                    else if(choice == 2){
                        g.nextDay();
                    }
                    else if(choice == 3){
                        g.displayFarmerOptions();
                        int farmerChoice = scanner.nextInt();

                        if(farmerChoice == 1){
                            g.registerFarmer();
                        }
                        else if(farmerChoice == 2){
                            g.displayFarmerDetails();
                        }
                    }
                    
                    gameOver = g.checkGameOver();
                }
            }
            else if(choice == 2){
                exit = true;
            }
        }
        scanner.close();
    }
}