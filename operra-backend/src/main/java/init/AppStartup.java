package init;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import regis.MetaInitializer;

@ApplicationScoped
public class AppStartup {

    @PostConstruct
    public void init() {
        MetaInitializer.initialize();
    }
}
