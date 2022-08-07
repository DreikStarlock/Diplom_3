package pojo;

import lombok.Data;

@Data
public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(UserPojo userPojo) {
        this.email = userPojo.getEmail();
        this.password = userPojo.getPassword();
    }

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCredentials from(UserPojo userPojo) {
        return new UserCredentials(userPojo);
    }

    public static UserCredentials withoutEmail(UserPojo userPojo) {
        return new UserCredentials("", userPojo.getPassword());
    }

    public static UserCredentials withoutPassword(UserPojo userPojo) {
        return new UserCredentials(userPojo.getEmail(), "");
    }
}
