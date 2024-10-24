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

			// Product friedRice = new Product("Fried Rice", "Day Old Rice Fried in a Pan",
			// 20000L, food.getId());
			Product friedRice = new Product(food.getId(), "Nasi Goreng", "Fadli");
			friedRice.setCategory(food);
			productRepository.save(friedRice);

			Variant hainanFriRice = new Variant(friedRice.getId(), "Hainan Fried Rice",
					"Fried Rice Hainan Style, Lots of Ginger and a little moist", new BigDecimal(25000),
					new BigDecimal(10), "ruri");
			hainanFriRice.setProduct(friedRice);
			variantRepository.save(hainanFriRice);
			// Faker faker = new Faker(new Locale("es"));
			// int categories = 5;
			// for (int i = 0; i < categories; i++) {
			// Category categorySeed = new Category(faker.name().fullName(),
			// faker.internet().slug());
			// categoryRepository.save(categorySeed);
			// }
		};
	}
}
