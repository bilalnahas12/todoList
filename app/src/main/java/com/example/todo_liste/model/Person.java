package com.example.todo_liste.model;

import java.util.ArrayList;

public class Person {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isSigned=false;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Person(String name, String email, String password) {
        this.id = idCounter++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSigned = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addT(Task task) {
        tasks.add(task);
    }
}
