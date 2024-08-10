package online_shop.online_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import online_shop.online_shop.property.FileStorageProperties;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "online_shop.online_shop.repository")
@EntityScan(basePackages = "online_shop.online_shop.domain")
@EnableConfigurationProperties({ FileStorageProperties.class })
public class OnlineShopApplication {
	// @Autowired
	// private UserRepository userRepository;

	// @Autowired
	// private ProductRepository productRepository;

	// @Autowired
	// private CategoryRepository categoryRepository;
	// @Autowired
	// private CartRepository cartRepository;
	// @Autowired
	// private OrderRepository orderRepository;
	// @Autowired
	// private CartItemRepository cartItemRepository;
	// @Autowired
	// private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// // clean database
	// orderItemRepository.deleteAll();
	// orderRepository.deleteAll();
	// cartItemRepository.deleteAll();
	// cartRepository.deleteAll();
	// productRepository.deleteAll();
	// categoryRepository.deleteAll();
	// userRepository.deleteAll();

	// // Create and save a new User
	// User user = new User("John Doe", "john@example.com", "password123",
	// Role.CUSTOMER);
	// userRepository.save(user);

	// // Create and save a new Category
	// Category electronics = new Category("Electronics", "Devices and gadgets");
	// categoryRepository.save(electronics);

	// // Create and save a new Product
	// Product phone = new Product("Smartphone", "Latest model smartphone", 699.99,
	// electronics, null);
	// productRepository.save(phone);

	// // Create and save a new Cart and CartItem
	// Cart cart = new Cart(user, 0);
	// CartItem cartItem = new CartItem(cart, phone, 1, phone.getPrice());
	// cart.addItem(cartItem);
	// cartRepository.save(cart);

	// // Update Cart total price
	// cart.setTotalPrice(phone.getPrice());
	// cartRepository.save(cart);

	// // Create and save a new Order and OrderItem
	// Order order = new Order(new Date(), "PENDING", cart.getTotalPrice(), user);
	// OrderItem orderItem = new OrderItem(phone, order, 1, phone.getPrice());
	// order.addItem(orderItem);
	// orderRepository.save(order);

	// // Retrieve and print all users
	// System.out.println("All Users:");
	// userRepository.findAll().forEach(System.out::println);

	// // Retrieve and print all products
	// System.out.println("All Products:");
	// productRepository.findAll().forEach(System.out::println);

	// // Retrieve and print all orders
	// System.out.println("All Orders:");
	// orderRepository.findAll().forEach(System.out::println);
	// productRepository.save(phone);

	// }
}
