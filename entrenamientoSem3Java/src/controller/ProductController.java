package controller;

import models.Products;
import repository.ProductRepositoryImpl;
import util.SummaryUtil;

import javax.swing.*;
import java.util.List;

public class ProductController {

    public static void createProduct() {
        try {
            String name = JOptionPane.showInputDialog("Enter product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter product stock:"));

            Products newProduct = new Products(0, name, price, stock);
            ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
            Products insertedProduct = (Products) productRepository.insert(newProduct);

            if (insertedProduct != null && insertedProduct.getId() != 0) {
                JOptionPane.showMessageDialog(null, "Product created successfully: " + insertedProduct);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create product.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers for price and stock.");
            SummaryUtil.addMessage("Error: Invalid number format during product creation.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during product creation: " + e.getMessage());
            SummaryUtil.addMessage("Error: " + e.getMessage());
        }
    }

    public static void getAll() {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        List<Object> products = productRepository.findAll();

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products available.");
        } else {
            StringBuilder productList = new StringBuilder("Products:\n");
            for (Object obj : products) {
                Products product = (Products) obj;
                productList.append(product.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, productList.toString());
            SummaryUtil.addMessage("Listed all products.");
        }
    }

    public static void updateProduct() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the product to update:"));
            ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
            Products existingProduct = (Products) productRepository.findById(id);

            if (existingProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found.");
                SummaryUtil.addMessage("Attempted to update non-existent product ID: " + id);
                return;
            }

            String name = JOptionPane.showInputDialog("Enter new product name (current: " + existingProduct.getName() + "):", existingProduct.getName());
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter new product price (current: " + existingProduct.getPrice() + "):", existingProduct.getPrice()));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter new product stock (current: " + existingProduct.getStock() + "):", existingProduct.getStock()));

            existingProduct.setName(name);
            existingProduct.setPrice(price);
            existingProduct.setStock(stock);

            if (productRepository.update(existingProduct)) {
                JOptionPane.showMessageDialog(null, "Product updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update product.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
            SummaryUtil.addMessage("Error: Invalid number format during product update.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during product update: " + e.getMessage());
            SummaryUtil.addMessage("Error: " + e.getMessage());
        }
    }

    public static void updatePrice() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the product to update price:"));
            ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
            Products existingProduct = (Products) productRepository.findById(id);

            if (existingProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found.");
                SummaryUtil.addMessage("Attempted to update price for non-existent product ID: " + id);
                return;
            }

            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new price for " + existingProduct.getName() + " (current: " + existingProduct.getPrice() + "):"));

            if (productRepository.updatePrice(id, newPrice)) {
                JOptionPane.showMessageDialog(null, "Product price updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update product price.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for price.");
            SummaryUtil.addMessage("Error: Invalid number format during price update.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during product price update: " + e.getMessage());
            SummaryUtil.addMessage("Error: " + e.getMessage());
        }
    }

    public static void updateStock() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the product to update stock:"));
            ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
            Products existingProduct = (Products) productRepository.findById(id);

            if (existingProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found.");
                SummaryUtil.addMessage("Attempted to update stock for non-existent product ID: " + id);
                return;
            }

            int newStock = Integer.parseInt(JOptionPane.showInputDialog("Enter new stock for " + existingProduct.getName() + " (current: " + existingProduct.getStock() + "):"));

            if (productRepository.updateStock(id, newStock)) {
                JOptionPane.showMessageDialog(null, "Product stock updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update product stock.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for stock.");
            SummaryUtil.addMessage("Error: Invalid number format during stock update.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during product stock update: " + e.getMessage());
            SummaryUtil.addMessage("Error: " + e.getMessage());
        }
    }

    public static void deleteProduct() {
        try {
            getAll(); // Show all products before asking for ID
            int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the product to delete:"));

            ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
            Products productToDelete = (Products) productRepository.findById(idToDelete);

            if (productToDelete == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + idToDelete + " not found.");
                SummaryUtil.addMessage("Attempted to delete non-existent product ID: " + idToDelete);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + productToDelete.getName() + " (ID: " + idToDelete + ")?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (productRepository.delete(productToDelete)) {
                    JOptionPane.showMessageDialog(null, "Product deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete product.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Product deletion cancelled.");
                SummaryUtil.addMessage("Product deletion cancelled by user.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for ID.");
            SummaryUtil.addMessage("Error: Invalid number format during product deletion.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during product deletion: " + e.getMessage());
            SummaryUtil.addMessage("Error: " + e.getMessage());
        }
    }

    public static void searchProductByName() {
        try {
            String nameToSearch = JOptionPane.showInputDialog("Enter product name to search:");
            ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
            List<Object> foundProducts = productRepository.findByName(nameToSearch);

            if (foundProducts.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No products found with name containing '" + nameToSearch + "'.");
                SummaryUtil.addMessage("Searched for products by name: '" + nameToSearch + "'. None found.");
            } else {
                StringBuilder productList = new StringBuilder("Products found:\n");
                for (Object obj : foundProducts) {
                    Products product = (Products) obj;
                    productList.append(product.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, productList.toString());
                SummaryUtil.addMessage("Searched for products by name: '" + nameToSearch + "'. Found " + foundProducts.size() + ".");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during product search: " + e.getMessage());
            SummaryUtil.addMessage("Error: " + e.getMessage());
        }
    }

    public static void printSummary() {
        List<String> messages = SummaryUtil.getSummaryMessages();
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No operations to summarize.");
        } else {
            StringBuilder summary = new StringBuilder("Operation Summary:\n");
            for (String message : messages) {
                summary.append("- ").append(message).append("\n");
            }
            JOptionPane.showMessageDialog(null, summary.toString());
        }
        SummaryUtil.clearSummary(); // Clear after displaying
    }
}
