package kr.ac.jejunu;
//
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;

public class ProductDaoTest {

    private ProductDao productDao;
    private ProductDao hallaProductDao;

    @Before
    public void setup() {
        productDao = new JejuProductDao();
        hallaProductDao = new HallaProductDao();
    }


    @Test
    public void get() throws SQLException, ClassNotFoundException {
        //ProductDao productDao = new ProductDao();
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;


        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());


        //ProductDao productDao1 = new ProductDao();
        //Product product = productDao.get(id);
        assertThat(id, is(product.getId()));
        assertThat(title, is(product.getTitle()));
        assertThat(price, is(product.getPrice()));
            }


    @Test
    public void add() throws SQLException, ClassNotFoundException {
        String title = "사과";
        Integer price = 2000;

        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        //ProductDao productDao = new ProductDao();
        Long id = productDao.add(product);
        Product resultUser = productDao.get(id);
        assertThat(id, is(resultUser.getId()));
        assertThat(id, is(resultUser.getId()));
        assertThat(price, is(resultUser.getPrice()));
    }

    @Test
    public void hallaGet() throws SQLException, ClassNotFoundException {
        Long id= 1L;
        Product product = hallaProductDao.get(id);
        assertThat(product.getId(), is(1L));
        assertThat(product.getTitle(), is("제주감귤"));
        assertThat(product.getPrice(), is(15000));
        }

        @Test
        public void hallaAdd() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("바나나");
        product.setPrice(1800);
        Long id = hallaProductDao.add(product);

        Product insertedProduct = hallaProductDao.get(id);
        assertThat(insertedProduct.getId(), is(id));
        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));
    }
}