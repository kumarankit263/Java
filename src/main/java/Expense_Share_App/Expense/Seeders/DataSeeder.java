//package Expense_Share_App.Expense.Seeders;
//import Expense_Share_App.Expense.models.User;
//import Expense_Share_App.Expense.repositories.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//@Component
//public class DataSeeder implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//
//    public DataSeeder(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        if (userRepository.count() == 0) {
//            Date date = Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant());
//
//            User u1 = User.builder().name("Alice").phone("9998887770").password("alice123").build();
//            User u2 = User.builder().name("Bob").phone("9998887771").password("bob123").build();
//            User u3 = User.builder().name("Charlie").phone("9998887772").password("charlie123").build();
//            User u4 = User.builder().name("David").phone("9998887773").password("david123").build();
//
//            u1.setCreatedAt(date);
//            u1.setUpdatedAt(date);
//            u2.setCreatedAt(date);
//            u2.setUpdatedAt(date);
//            u3.setCreatedAt(date);
//            u3.setUpdatedAt(date);
//            u4.setCreatedAt(date);
//            u4.setUpdatedAt(date);
//
//            List<User> users = List.of(
//                    u1, u2, u3, u4
//            );
//
//            userRepository.saveAll(users);
//            System.out.println("✅ Seeded test users");
//        } else {
//            System.out.println("ℹ️ Users already seeded");
//        }
//    }
//}
