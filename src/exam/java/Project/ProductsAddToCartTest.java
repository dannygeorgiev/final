package Project;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ProductsAddToCartTest extends TestUtil {
    @Test (dataProvider = "itemName")
    public void successfulProductsAddToCart(String itemName){
        LoginPage lgnPage = new LoginPage(driver);
        ProductPage prPage = lgnPage.login("dannytest@mail.bg","Test12345");
        prPage.chosenProduct(itemName);

    }





    @DataProvider(name = "itemName")
    public static Object [][] readProductsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/exam/resources/products.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[] [] csvDataObj = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++){
                csvDataObj[i] = csvData.get(i);
            }
            return csvDataObj;
        }catch (IOException e){
            System.out.println("Not Possible to find CSV!");
            return null;
        }
        catch (CsvException e){
            return null;
        }
    }
}
