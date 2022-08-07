package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private String email;
    private String password;
    private String name;

    public static UserPojo getRandom() {
        String email = RandomStringUtils.randomAlphanumeric(8) + "@scs.com";
        String password = RandomStringUtils.randomAlphanumeric(8);
        String name = RandomStringUtils.randomAlphanumeric(8);
        return new UserPojo(email, password, name);
    }
}
