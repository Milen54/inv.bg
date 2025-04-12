package api.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Credential {

/**    {
*        "email": "example@mail.com",
*            "password": "not-so-secret-password",
*            "domain": "nomer47"
*    }
 */

// DTO/POJO (Data Transfer Object, Plan Old Java Object)

    private String email;
    private String password;
    private String domain;

    public Credential(String email, String password, String domain){
        this.email = email;
        this.password = password;
        this.domain = domain;
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create gson instance using builder

        Credential credential = new Credential("denkovmilen@abv.bg", "parola123", "milen-ood");  // Create DTO instance
        credential.domain = "milen-ood";
        credential.email = "denkovmilen@abv.bg";
        credential.password = "parola123";

        String json = gson.toJson(credential);
        System.out.println(json);
    }

}

