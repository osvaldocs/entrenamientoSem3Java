package controller;

import javax.swing.*;

public class MenuController {


    public static void showMenu() {
        String[] options = {"Add product", "List products", "Update price", "Update stock", "Delete product", "Search product by name", "Exit  with summary"};
        int option;

            do {
            option = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "User system",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );


            switch (option) {
                case 0:
                    addProduct();
                    break;
                case 1:
                    listProducts();
                    break;
                case 2:
                    updateProductPrice();
                    break;
                case 3:
                    updateProductStock();
                    break;
                case 4:
                    deleteProductByID();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                    printSummary();
                    break;
            }

        } while (option != 6 && option != JOptionPane.CLOSED_OPTION);
    }
}
