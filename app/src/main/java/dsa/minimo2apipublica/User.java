package dsa.minimo2apipublica;

/**
 * Created by ivanm on 31/05/2017.
 */

public class User {

    int _id;
    String email, password;

    public User(int id, String email, String password) {
        this._id = id;
        this.email = email;
        this.password = password;
    }
}
