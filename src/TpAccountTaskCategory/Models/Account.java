package TpAccountTaskCategory.Models;

public class Account {
    /* ------------------------------------------ **
     * ATTRIBUTES
     * ------------------------------------------ */
    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private String password;

    /* ------------------------------------------ **
     * CONSTRUCTORS
     * ------------------------------------------ */
    // Empty
    public Account() {
    }
    // Full
    public Account(int id, String lastname, String firstname, String email, String password) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }
    // Full without Id
    public Account(String lastname, String firstname, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    /* ------------------------------------------ **
     * GET / SET
     * ------------------------------------------ */
    // Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // lastname
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    // firstname
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    //email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    // password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /* ------------------------------------------ **
     * toString
     * ------------------------------------------ */

    @Override
    public String toString() {
        return "Id: " + id +
                " - " + lastname +
                ", " + firstname +
                " - " + email;
    }
}