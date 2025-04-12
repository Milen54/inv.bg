package core;

import api.ItemAPI;
import api.LoginAPI;

public class ApiManager {
        private String token;
        private LoginAPI loginAPI;
        private ItemAPI itemAPI;

        public ApiManager(String email, String password, String domain) {
            this.token = getLoginAPI().obtainToken(email, password, domain);
        }

        public LoginAPI getLoginAPI() {
            if (loginAPI == null) {
                loginAPI = new LoginAPI();
            }
            return loginAPI;
        }

        public ItemAPI getItemAPI() {
            if (itemAPI == null) {
                itemAPI = new ItemAPI(token);
            }
            return itemAPI;
        }

}
