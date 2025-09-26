
import util.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("hola");
        
        // Probar conexión a BD
        ConnectionFactory.openConnection();
        ConnectionFactory.closeConnection();
    }
}

