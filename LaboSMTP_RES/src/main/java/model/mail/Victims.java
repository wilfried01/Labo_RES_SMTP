package model.mail;

public class Victims {
    private String email;

    public Victims(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Victims{" +
                "email = '" + email + '\'' +
                "}";
    }
}
