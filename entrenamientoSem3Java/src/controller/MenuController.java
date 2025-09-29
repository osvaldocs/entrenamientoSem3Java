package controller;

import javax.swing.*;

public class MenuController {

    public static void showMenu() {
        String[] options = {"Add Product", "List Products", "Update Product", "Update Product Price", "Update Product Stock", "Delete Product", "Search Product by Name", "View Operation Summary", "Exit"};
        int option;

        do {
            option = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "PRODUCT MANAGEMENT MENU",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (option) {
                case 0: // Add Product
                    ProductController.createProduct();
                    break;
                case 1: // List Products
                    ProductController.getAll();
                    break;
                case 2: // Update Product
                    ProductController.updateProduct();
                    break;
                case 3: // Update Product Price
                    ProductController.updatePrice();
                    break;
                case 4: // Update Product Stock
                    ProductController.updateStock();
                    break;
                case 5: // Delete Product
                    ProductController.deleteProduct();
                    break;
                case 6: // Search Product by Name
                    ProductController.searchProductByName();
                    break;
                case 7: // View Operation Summary
                    ProductController.printSummary();
                    break;
                case 8: // Exit
                    JOptionPane.showMessageDialog(null, "Exiting application. Goodbye!");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    option = 8; // Treat closing the dialog as Exit
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        } while (option != 8);
    }
}
