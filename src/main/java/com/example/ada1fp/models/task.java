package com.example.ada1fp.models;

public class task {
    private static int idCounter = 0; // Static counter for generating unique IDs
    private final int id; // Unique ID for each task
    private int priority;
    private String description;
    private String expirationDate;

    // Constructor
    public task(int priority, String description, String expirationDate) {
        this.id = ++idCounter; // Increment the counter and assign it as the ID
        this.priority = priority;
        this.description = description;
        this.expirationDate = expirationDate;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    // Getter for id
    public int getId() {
        return id;
    }

    // Getter and Setter for priority
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}