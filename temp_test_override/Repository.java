import java.util.List;

public interface Repository {
    Object insert(Object obj);
    List<Object> findAll();
    Object findById(int id);
    boolean update(Object obj);
    boolean delete(Object obj);
}
