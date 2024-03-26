package redesocial;

public class User {
    
    private String name;
    private String email;         
    private String password;
    // private List<Post> posts;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        // this.posts = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return name + ";" + email + ";" + password;
    }
    
}
