package com.xa.backend342;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xa.backend342.entities.Category;
import com.xa.backend342.entities.Product;
import com.xa.backend342.entities.Variant;
import com.xa.backend342.repositories.CategoryRepository;
import com.xa.backend342.repositories.ProductRepository;
import com.xa.backend342.repositories.VariantRepository;

@SpringBootApplication
public class Backend342Application {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	VariantRepository variantRepository;

	public static void main(String[] args) {
		SpringApplication.run(Backend342Application.class, args);
	}

	@Bean
	@SuppressWarnings("unused")
	CommandLineRunner commandLineRunner() {
		return args -> {
			// Category Seeding
			Category food = new Category("Food", "Admin");
			Category beverage = new Category("Beverage", "Admin");
			Category medicine = new Category("Medicine", "Admin");
			categoryRepository.save(food);
			categoryRepository.save(beverage);
			categoryRepository.save(medicine);

			// Product friedRice = new Product("Fried Rice", "Day Old Rice Fried in a Pan", 20000L, food.getId());
			Product friedRice = new Product(food.getId(),"Nasi Goreng", "Fadli");
			friedRice.setCategory(food);
			productRepository.save(friedRice);
			Product friedRiceNoodle = new Product(food.getId(),"Kwetiau Goreng", "Fadli");
			friedRiceNoodle.setCategory(food);
			productRepository.save(friedRiceNoodle);
			Product Noodles = new Product(food.getId(),"Mie", "Fadli");
			Noodles.setCategory(food);
			productRepository.save(Noodles);
			Product juice = new Product(beverage.getId(),"Jus", "Fadli");
			juice.setCategory(beverage);
			productRepository.save(juice);
			Product tea = new Product(beverage.getId(),"Teh", "Fadli");
			tea.setCategory(beverage);
			productRepository.save(tea);

			Variant hainanFriRice = new Variant(friedRice.getId(),"Nasi Goreng Hainan","Fried Rice Hainan Style, Lots of Ginger and a little moist",new BigDecimal(25000),new BigDecimal(10),"ruri");
			hainanFriRice.setProduct(friedRice);
			variantRepository.save(hainanFriRice);
			Variant indoFriRice = new Variant(friedRice.getId(),"Nasi Goreng Indonesia","Fried Rice Indonesian Style, Sweet Soy Sauce and some Oyster Sauce makes this a savory meal with a hint of sweetness",new BigDecimal(17000),new BigDecimal(10),"ruri");
			indoFriRice.setProduct(friedRice);
			variantRepository.save(indoFriRice);
			
			Variant friRiNoodSpecial = new Variant(friedRiceNoodle.getId(),"Kwetiau Goreng Spesial","Kwetiau goreng dengan topping telur, daging ayam, daging sapi, ati, dan sosis",new BigDecimal(17000),new BigDecimal(10),"ruri");
			friRiNoodSpecial.setProduct(friedRiceNoodle);
			variantRepository.save(friRiNoodSpecial);
			Variant friRiNoodSeafood = new Variant(friedRiceNoodle.getId(),"Kwetiau Goreng Seafood","Kwetiau goreng dengan topping telur, udang, gurita, dan cumi",new BigDecimal(17000),new BigDecimal(10),"ruri");
			friRiNoodSeafood.setProduct(friedRiceNoodle);
			variantRepository.save(friRiNoodSeafood);

			Variant gabanJuice = new Variant(juice.getId(),"Jus Gaban","Jus murah, besar, kental, manis, dan enak",new BigDecimal(17000),new BigDecimal(10),"ruri");
			gabanJuice.setProduct(juice);
			variantRepository.save(gabanJuice);
			Variant earlTea = new Variant(tea.getId(),"Teh Earl Grey","Teh Asal Inggris",new BigDecimal(17000),new BigDecimal(10),"ruri");
			earlTea.setProduct(tea);
			variantRepository.save(earlTea);
			Variant jasmineTea = new Variant(tea.getId(),"Teh Jasmine","Teh dari daun bunga jasmine",new BigDecimal(17000),new BigDecimal(10),"ruri");
			jasmineTea.setProduct(tea);
			variantRepository.save(jasmineTea);
		};
	}
}
