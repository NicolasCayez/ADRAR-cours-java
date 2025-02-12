package TpAccountTaskCategory.Models;

import TpAccountTaskCategory.Repositories.AccountRepository;
import TpAccountTaskCategory.Repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    /* ------------------------------------------ **
     * ATTRIBUTES
     * ------------------------------------------ */
    private int id;
    private String title;
    private String description;
    private java.sql.Date createdAt;
    private boolean status;
    private int account_id;

    /* ------------------------------------------ **
     * CONSTRUCTORS
     * ------------------------------------------ */
    // Empty
    public Task() {
    }
    // Full
    public Task(int id, String title, String description, java.sql.Date createdAt, boolean status, int account_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.account_id = account_id;
    }
    // Full without Id
    public Task(String title, String description, java.sql.Date createdAt, boolean status, int account_id) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.account_id = account_id;
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
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    /* ------------------------------------------ **
     * toString
     * ------------------------------------------ */
    @Override
    public String toString() {
        Account account = AccountRepository.findById(account_id);
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
