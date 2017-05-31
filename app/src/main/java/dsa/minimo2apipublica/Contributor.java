package dsa.minimo2apipublica;

/**
 * Created by ivanm on 31/05/2017.
 */

public class Contributor {
    public final String login;
    public final int contributions;
    public final String avatar_url;

    public Contributor(String login, int contributions, String avatar_url) {
        this.login = login;
        this.contributions = contributions;
        this.avatar_url = avatar_url;
    }


}
