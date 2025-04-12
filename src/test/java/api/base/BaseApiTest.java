package api.base;

import core.ApiManager;
import core.Config;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {

    protected ApiManager api;

    @BeforeEach
    public void setup() {
        api = new ApiManager(Config.EMAIL, Config.PASSWORD, Config.DOMAIN);
    }
}
