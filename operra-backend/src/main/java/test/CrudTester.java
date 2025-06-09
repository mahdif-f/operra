package test;


import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import model.test.Product;

@Startup
@Singleton
public class CrudTester {
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
//        try {
//            Product p = new Product();
//            p.setName("کالا تست");
//            em.persist(p);
//            System.out.println("✔️ تست Insert موفق");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}
