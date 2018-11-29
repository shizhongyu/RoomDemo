package com.example.cm.room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/timshinlee/article/details/74226965
 * https://www.jianshu.com/p/cfde3535233d
 * https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
 * https://developer.android.com/topic/libraries/architecture/room
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView textView;

    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(getData());
            }
        });

        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        findViewById(R.id.bt_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delData();
            }
        });

        findViewById(R.id.bt_find_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(findById());
            }
        });

        findViewById(R.id.bt_find_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(findByName());
            }
        });

        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(update());
            }
        });
        textView = findViewById(R.id.tv);
    }


    private void insertData() {
        userList.clear();
        User user1 = new User.Builder()
                .firstName("li")
                .lastName("yu")
                .uid(1)
                .pubyear(1)
                .build();
        User user2 = new User.Builder()
                .firstName("wang")
                .lastName("yu")
                .uid(2)
                .build();
        User user3 = new User.Builder()
                .firstName("liu")
                .lastName("yu")
                .uid(3)
                .build();

        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);

        long[] longs = AppDatabase.getInstance(MainActivity.this)
                .userDao().insertAll(userList.toArray(new User[userList.size()]));
        Log.d(TAG, "insertData: " + longs.length);
    }


    private List<User> getData() {
        return AppDatabase.getInstance(MainActivity.this)
                .userDao().getAll();
    }

    private void showData(List<User> userList) {
        if(userList == null || userList.size() == 0) {
            return;
        }
        String str = "";
        for (int i = 0; i < userList.size(); i++) {
            str += userList.get(i).toString();
        }
        textView.setText(str);
    }

    private void showData(User... user) {
        if(user == null) {
            return;
        }
        String str = "";
        for (int i = 0; i < user.length; i++) {
            str += user[i].toString();
        }
        textView.setText(str);
    }

    private void delData() {
        AppDatabase.getInstance(MainActivity.this)
                .userDao().delete(getData().get(getData().size() - 1));
    }


    private User findById() {
        return AppDatabase.getInstance(MainActivity.this)
                .userDao().finddById(1);
    }

    private User findByName() {
        return AppDatabase.getInstance(MainActivity.this)
                .userDao().findByName("li", "yu");
    }

    private User update() {
        User user = AppDatabase.getInstance(MainActivity.this)
                .userDao().finddById(1);
        if(user != null) {
            user.setLastName("yun");
        }
        AppDatabase.getInstance(MainActivity.this)
                .userDao().updata(user);
        return user;
    }

}
