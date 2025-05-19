package test;


import model.test.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CRUDTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("operraPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // ایجاد یک مشتری جدید
            Customer c = new Customer();
            c.setName("Ali Reza");
            c.setEmail("ali.reza@example.com");
            em.persist(c);

            em.getTransaction().commit();

            System.out.println("مشتری با موفقیت ذخیره شد با شناسه: " + c.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
