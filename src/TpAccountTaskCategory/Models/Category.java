package TpAccountTaskCategory.Models;

public class Category {
    /* ------------------------------------------ **
     * ATTRIBUTES
     * ------------------------------------------ */
    private int id;
    private String name;

    /* ------------------------------------------ **
     * CONSTRUCTORS
     * ------------------------------------------ */
    // Empty
    public Category() {
    }
    // Full
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    // Full without Id
    public Category(String name) {
        this.name = name;
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
    // name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /* ------------------------------------------ **
     * toString
     * ------------------------------------------ */

    @Override
    public String toString() {
        return "Id: " + id +
                " - " + name;
    }
}