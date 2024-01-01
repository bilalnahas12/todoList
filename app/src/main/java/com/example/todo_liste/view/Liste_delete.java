package com.example.todo_liste.view;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.todo_liste.R;
import com.example.todo_liste.adapter.MyAdapter;
import com.example.todo_liste.model.Task;
import com.example.todo_liste.stokPerson.Stokperson;
public class Liste_delete extends AppCompatActivity {

    ListView lv;
        List<HashMap<String, String>> list=new ArrayList<>();
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_liste_delete);
            lv=findViewById(R.id.lvd);
            MyAdapter a = new MyAdapter((ArrayList) convertTaskListToHashMapList(Stokperson.delitList), this);
            lv.setAdapter(a);

        }
        private List<HashMap<String, String>> convertTaskListToHashMapList(ArrayList<Task> task) {
            for (Task t:task) {
                HashMap<String, String> taskMap = new HashMap<>();
                taskMap.put("nom", t.getTitre());
                taskMap.put("description", t.getDescription());
                taskMap.put("endDate", String.valueOf(t.getDate_fin()));
                list.add(taskMap);

            }
            return list;
        }

    }