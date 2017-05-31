package dsa.minimo2apipublica;

/**
 * Created by ivanm on 31/05/2017.
 */

public class Usuario {

    public String login = null;
    public String avatar_url = null;
    public int public_repos,followers,following;

    public Usuario(String login, String avatar_url, int public_repos, int followers, int following) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.public_repos = public_repos;
        this.followers = followers;
        this.following = following;
    }

    public Usuario(){}
}
