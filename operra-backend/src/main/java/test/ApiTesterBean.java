package test;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import meta.registry.MenuInitializer;

@Named("apiTester")
@RequestScoped
public class ApiTesterBean {

    @Inject
    private MenuInitializer menuInitializer;
    private String response;

    private static final String BASE_URL = "http://localhost:8180/operra/api";


    @PostConstruct
    public void init() {
        menuInitializer.init();
    }

    public void testContext() {
        response = callGet("/meta/context/product");
    }

    public void testList() {
        response = callGet("/crud/product");
    }

    public void testCreate() {
        String json = "{\"name\":\"تست از JSF\",\"price\":1234}";
        response = callPost("/crud/product", json);
    }

    private String callGet(String path) {
        try (Client client = ClientBuilder.newClient()) {
            Response res = client.target(BASE_URL + path)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            return res.readEntity(String.class);
        } catch (Exception e) {
            return "خطا: " + e.getMessage();
        }
    }

    private String callPost(String path, String json) {
        try (Client client = ClientBuilder.newClient()) {
            Response res = client.target(BASE_URL + path)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            return res.readEntity(String.class);
        } catch (Exception e) {
            return "خطا: " + e.getMessage();
        }
    }

    public String getResponse() {
        return response;
    }
}
