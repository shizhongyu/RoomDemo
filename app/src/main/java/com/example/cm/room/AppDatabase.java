package com.example.cm.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;



@Database(entities = {User.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    public abstract UserDao userDao();

    //每次创建AppDatabase实例都会产生比较大的开销，所以应该将AppDatabase设计成单例的。
    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user.db")
                                    .allowMainThreadQueries()//除非在建造器上调用了allowMainThreadQueries()，否则Room不支持主线程上的数据库访问，
                                    // 因为它可能会长时间锁定UI。返回LiveData或Flowable实例的异步查询可免除此规则，因为它们在需要时异步地在后台线程上运行查询。
                                    .addMigrations(MIGRATION_2_3)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };

}
