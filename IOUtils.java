package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.shop.core.Product;

public class IOUtils {
	//add a method to store product details in a binary file using serializer
	static void storeProductDetails(Map<Integer,Product> map,String fileName)throws IOException{
		//JAVA --> OOS --> FOS (binary file)
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
			out.writeObject(map);
		}
	}
}
