package com.example.cm.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE uid = :userId")
    User finddById(int userId);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    /**
     * 插入数据时，您可以提供冲突策略。在此代码框中，您不需要冲突策略，因为该单词是您的主键，
     * 并且默认的SQL行为是ABORT，因此您无法将具有相同主键的两个项插入到数据库中。如果表有多个列，
     * 则可以使用@Insert（onConflict = OnConflictStrategy.REPLACE）要替换一行。
     *
     * @param users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void updata(User... user);
}
