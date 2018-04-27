package kr.ac.jejunu;
//
import java.sql.*;

public abstract class ProductDao {
    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=UTF-8", "jeju", "MyNewPass");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Product product = new Product();
        product.setId(resultSet.getLong("id"));//id
        product.setTitle(resultSet.getString("title"));//name
        product.setPrice(resultSet.getInt("price"));//pass

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return product;
    }
    public Long add(Product product) throws ClassNotFoundException, SQLException {
                Connection connection = getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("insert into product(title, price) VALUES (?,?)");
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("select last_insert_id()");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                Long id = resultSet.getLong(1);

                resultSet.close();
                preparedStatement.close();
                connection.close();

                        return id;
            }

/*          private Connection getConnection() throws ClassNotFoundException, SQLException {
                Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=utf-8", "jeju", "MyNewPass");
            }*/
            abstract public Connection getConnection() throws ClassNotFoundException, SQLException;
        //        Class.forName("com.mysql.jdbc.Driver");
        //        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=utf-8"
        //                , "jeju", "MyNewPass");
}