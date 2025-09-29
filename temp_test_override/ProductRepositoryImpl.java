import java.util.List;
import java.util.ArrayList;

public class ProductRepositoryImpl implements Repository {

    @Override
    public Object insert(Object obj) {
        System.out.println("Insert called");
        return obj;
    }

    @Override
    public List<Object> findAll() {
        System.out.println("FindAll called");
        return new ArrayList<>();
    }

    @Override
    public Object findById(int id) {
        System.out.println("FindById called for id: " + id);
        return null;
    }

    @Override
    public boolean update(Object obj) {
        System.out.println("Update called");
        return true;
    }

    @Override
    public boolean delete(Object obj) {
        System.out.println("Delete called");
        return true;
    }
}
