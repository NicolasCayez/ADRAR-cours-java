package TpAccountTaskCategory.Models;

import TpAccountTaskCategory.Repositories.CategoryRepository;

import java.util.ArrayList;

public class Task {
    /* ------------------------------------------ **
     * ATTRIBUTES
     * ------------------------------------------ */
    private int id;
    private String title;
    private String description;
    private java.sql.Date createdAt;
    private boolean status;
//    private int account_id; // on met l'objet et pas juste l'ID
    private Account account;
    private ArrayList<Category> categories;

    /* ------------------------------------------ **
     * CONSTRUCTORS
     * ------------------------------------------ */
    // Empty
    public Task() {
    }
    // Full
    public Task(int id, String title, String description, java.sql.Date createdAt, boolean status, Account account) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.account = account;
    }
    // Full without Id
    public Task(String title, String description, java.sql.Date createdAt, boolean status, Account account) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.account = account;
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    // description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // createdAt
    public java.sql.Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(java.sql.Date createdAt) {
        this.createdAt = createdAt;
    }
    // status
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    // account_id
//    public int getAccount_id() {
//        return account_id;
//    }
//    public void setAccount_id(int account_id) {
//        this.account_id = account_id;
//    }
    // account
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    // Categories
    public ArrayList<Category> getCategories() {
        return categories;
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public void addCategories(Category category) {
        this.categories.add(category);
    }
    public void removeCategories(Category category) {
        this.categories.remove(category);
    }


    /* ------------------------------------------ **
     * toString
     * ------------------------------------------ */
    @Override
    public String toString() {
//        Account account = AccountRepository.findById(account_id);
        ArrayList<Category> categoriesList = CategoryRepository.findAllByTaskId(id);
        String categories = "";
        for (Category oneCategory : categoriesList) {
            categories += oneCategory.getName() + ", ";
        }
        return "Id: " + id +
                " - title: " + title + '\'' +
                " : desc: " + description + '\'' +
                " - " + createdAt +
                (status?" [Active] ":" [Not active] ") +
                " - for : " + account.getFirstname() +
                " " + account.getLastname() +
                " [" + categories + "]";
    }
}
