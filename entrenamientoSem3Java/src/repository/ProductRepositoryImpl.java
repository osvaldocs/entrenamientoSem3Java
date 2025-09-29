package repository;

import models.Products;
import util.ConnectionFactory;
import util.SummaryUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = null;
        Products objProduct = (Products) obj;
        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot insert product.");
                return null;
            }

            String sql = "INSERT INTO products (product_name, price , stock) VALUES (?,?,?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());

            objPrepare.execute();
            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objProduct.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Product was succesfully added");
            SummaryUtil.addMessage("Added product: " + objProduct.getName() + " (ID: " + objProduct.getId() + ")");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error inserting product: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error inserting product: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return objProduct;
    }

    @Override
    public List<Object> findAll() {
        List<Object> productList = new ArrayList<>();
        Connection objConnection = null;
        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot fetch products.");
                return productList;
            }
            String sql = "SELECT * FROM products";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Products objProduct = new Products();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("product_name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                productList.add(objProduct);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error fetching products: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error fetching products: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return productList;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = null;
        Products objProduct = null;
        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot find product.");
                return null;
            }
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objProduct = new Products();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("product_name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error finding product: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error finding product: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return objProduct; // Esto se devuelve como Object
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = null;
        Products objProduct = (Products) obj;
        boolean isUpdated = false;

        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot update product.");
                return false;
            }
            String sql = "UPDATE products SET product_name = ?, price = ?, stock = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setInt(4, objProduct.getId());

            int result = objPrepare.executeUpdate();

            if (result > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Product updated successfully");
                SummaryUtil.addMessage("Updated product: " + objProduct.getName() + " (ID: " + objProduct.getId() + ")");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating product: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error updating product: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return isUpdated;
    }

    public boolean updatePrice(int productId, double newPrice) {
        Connection objConnection = null;
        boolean isUpdated = false;

        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot update product price.");
                return false;
            }
            String sql = "UPDATE products SET price = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDouble(1, newPrice);
            objPrepare.setInt(2, productId);

            int result = objPrepare.executeUpdate();

            if (result > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Product price updated successfully");
                SummaryUtil.addMessage("Updated price for product ID " + productId + " to " + newPrice);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating product price: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error updating product price: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return isUpdated;
    }

    public boolean updateStock(int productId, int newStock) {
        Connection objConnection = null;
        boolean isUpdated = false;

        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot update product stock.");
                return false;
            }
            String sql = "UPDATE products SET stock = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, newStock);
            objPrepare.setInt(2, productId);

            int result = objPrepare.executeUpdate();

            if (result > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Product stock updated succesfully");
                SummaryUtil.addMessage("Updated stock for product ID " + productId + " to " + newStock);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating product stock: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error updating product stock: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Products objProduct = (Products) obj;
        Connection objConnection = null;
        boolean isDeleted = false;

        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot delete product.");
                return false;
            }
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objProduct.getId());

            int result = objPrepare.executeUpdate();

            if(result > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Product deleted succesfully");
                SummaryUtil.addMessage("Deleted product: " + objProduct.getName() + " (ID: " + objProduct.getId() + ")");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error deleting product: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error deleting product: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return isDeleted;
    }

    public List<Object> findByName(String name) {
        List<Object> products = new ArrayList<>();
        Connection objConnection = null;
        try {
            objConnection = ConnectionFactory.openConnection();
            if (objConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Cannot search products.");
                return products;
            }
            String sql = "SELECT * FROM products WHERE product_name LIKE ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Products objProduct = new Products();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("product_name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                products.add(objProduct);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error searching products: " + e.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error searching products: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return products;
    }
}
