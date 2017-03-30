package bl.model;

public class Administrator extends User {

    public Administrator(String pseudo, String password, String email) {
        super(pseudo, password, email, Role.ADMINISTRATOR);
    }

    public Administrator clone() {
        return new Administrator(this.pseudo, this.password, this.email);
    }

}
