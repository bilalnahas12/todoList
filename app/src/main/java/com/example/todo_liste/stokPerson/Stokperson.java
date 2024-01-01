package com.example.todo_liste.stokPerson;

import com.example.todo_liste.model.Person;
import com.example.todo_liste.model.Task;

import java.time.Period;
import java.util.ArrayList;

public class Stokperson {


    public static Person currentPerson ;
    public static ArrayList<Task> delitList = new ArrayList<>();
    private  static ArrayList<Person> persons = new ArrayList<Person>();
    static {
        persons.add(new Person("admin", "admin", "0000"));
    }
    public static void setCurrentPerson(int i) {
        Stokperson.currentPerson = persons.get(i);
    }


public static void isadd(Person person) {
    persons.add(person);

}
public static boolean isUserExists(String email) {
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
public static boolean isverify(String email,String password){
    if (getpostin(email,password)!=-1){
        setCurrentPerson(getpostin(email,password));
        return true;
    }
    return false;

}
public static int getpostin(String email,String password){
    int id = -1;
    for (Person prson : persons){
        if(prson.getEmail().equals(email)&&  prson.getPassword().equals(password)){
            id = prson.getId();
            break;
        }
        break;
    }
    return id;
}
    public static ArrayList<Task> add(Task task ) {
    Stokperson.currentPerson.addT(task);
        return Stokperson.currentPerson.getTasks();
    }
    public static void dremove(int i) {
        delitList.add(Stokperson.currentPerson.getTasks().get(i));
        Stokperson.currentPerson.getTasks().remove(i);
    }
    public static ArrayList<Task> search(String p) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : Stokperson.currentPerson.getTasks()) {
            if (String.valueOf(task.getPrp()).equals(p)) {
                boolean taskExists = false;
                for (Task t : tasks) {
                    if (t.equalTo(task)) {
                        taskExists = true;
                        break;
                    }
                }if (!taskExists) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }



}
