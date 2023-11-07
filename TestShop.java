package functional_strms;

import static utils.ShopUtils.populateProductList;
import static utils.ShopUtils.populateProductMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.shop.core.Category;
import com.shop.core.Product;

public class TestShop {

	public static void main(String[] args) {
		List<Product> productList = populateProductList();
		try(Scanner sc=new Scanner(System.in))
			{
			boolean exit=false;
			while(!exit) {
				System.out.println();
				System.out.println("Options: ");
				System.out.println("1.Display all products details\n"+"2.Display product names of a specific category and price\n"
				+"3.Apply specified discount on specified category product\n"+"0. Exit");
					try {
						switch (sc.nextInt()) {
						case 1:
							productList.forEach(p -> System.out.println(p));
							System.out.println("------------------------------------------------");
							break;
							
						case 2:
							//Display all product names of a particular category,
							//exceeding specific price. (from product list)
							System.out.println();
							System.out.println("Enter category name & price.");
							Category cat = Category.valueOf(sc.next().toUpperCase()); 
							double price=sc.nextDouble();
							System.out.println("------------------------------------------------");
							System.out.println("All product names by category:"+cat+" AND "+" Price:"+price);
							productList.stream()//stream<Product>: all
							.filter(p -> p.getProductCategory()==cat)//filter stream: as per category
							.filter(p -> p.getPrice()>price)//filter stream: as per price
							.map(p -> p.getName())//Stream<String>
							.forEach(name -> System.out.println(name));
							break;
							
						case 3:
							//Apply discount(user entered) on all products of specified category n print
							//those product details after applying the discount
							Map<Integer, Product> pdtMap = populateProductMap(populateProductList());
							pdtMap.forEach((k, v) -> System.out.println(v));
							System.out.println();
							System.out.println("Enter Category and discount to apply: ");
							Category c = Category.valueOf(sc.next().toUpperCase());
							double discount=sc.nextDouble();
							System.out.println("------------------------Discount applied on following products of specified category-----------------------");
							pdtMap.values()//Collection<Product>
							.stream()//Stream<Product>
							.filter(p -> p.getProductCategory()==c)//filter stream of products
							.forEach(p -> {
								p.setPrice(p.getPrice()-discount);
								System.out.println(p);
							});
							
							/*
							 * "OR"
							 *pdtMap.values()//Collection<Product>
							.stream()//Stream<Product>
							.filter(p -> p.getProductCategory()==c)//filter stream of products
							.peek(p -> p.setPrice(p.getPrice() - discount))
							.forEach(p -> System.out.println(p));
							*/
							break;
						
						case 0:
							exit=true;
							System.out.println("Exiting the program");
							break;
						}
					}catch (Exception e) {
						// to read off the pending i/ps from scanner
						sc.nextLine();
						System.out.println(e);
				}
			}
		}
	}
}