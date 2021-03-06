package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao	extends JpaRepository<Product,Integer> {
	//https://www.baeldung.com/spring-data-derived-queries
	//isimlendirme kurallarına uyman yeterli şu an hepsi çalışır halde.
	  Product getByProductName(String productName);
	  
	  Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	  
	  List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
	  
	  List<Product> getByCategoryIn(List<Integer> categories);
	  
	  List<Product> getByProductNameContains(String productName);//productname i içeriyorsa
	  
	  List<Product> getByProductNameStartsWith(String productName);
	  
	  @Query("From Product where productName=:productName and category.categoryId=:categoryId")
	  List<Product> getByNameAndCategory(String productName, int categoryId);
	  
	  //select * from products where product_name=bisey and categoryId=bisey
	  
	  @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id,p.productName,c.categoryName) From Category c Inner Join c.products p")
	  List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
	  //Sql sorgusu: Select p.product_id,p.product_name,c.category_name from product p inner join category c on p.category_id=c.category_id
}