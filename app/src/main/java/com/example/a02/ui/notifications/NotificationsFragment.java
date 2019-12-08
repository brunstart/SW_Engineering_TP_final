package com.example.a02.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.a02.CalendarDb;
import com.example.a02.R;
import com.example.a02.ui.home.HomeFragment;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;


    private CalendarDb DB;
    Button addButton;

    EditText todo;
    EditText days;
    EditText sound;
    EditText image;
    EditText goal;

    int daysInt;

    String todoString;
    String soundString;
    String imageString;

    String today = HomeFragment.today;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        /*
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
*/



        todo = (EditText)root.findViewById(R.id.Todo);
        days = (EditText)root.findViewById(R.id.days);
        sound = (EditText)root.findViewById(R.id.sound);
        image = (EditText)root.findViewById(R.id.image);
        goal = (EditText)root.findViewById(R.id.goal);

        DB = new CalendarDb(getContext(), "CalendarDB");

        addButton = (Button)root.findViewById(R.id.addTodo);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    DB.openDB(getContext(),"CalendarDB");
                    int goalInt = 0;
                    if (days.getText().toString().isEmpty()) {
                        daysInt = 0;
                    } else
                        daysInt = Integer.parseInt(days.getText().toString());
                    if (goal.getText().toString().isEmpty()){
                        goalInt = 0;
                    }else goalInt = Integer.parseInt((goal.getText().toString()));

                    todoString = todo.getText().toString();
                    soundString = sound.getText().toString();
                    imageString = image.getText().toString();
                    DB.InsertTABLE(today, todoString, 0, goalInt, soundString, imageString, daysInt);

                    todo.setText("");
                    days.setText("");
                    sound.setText("");
                    image.setText("");
                    days.setText("");
                    goal.setText("");

                }catch(Exception ex){
                    System.out.println("추가 실패!");
                }
                DB.closeDB();
            }
        });


        return root;
    }
}