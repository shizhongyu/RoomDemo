package com.example.cm.room;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "pub_year")
    private Integer pubyear;

    public User() {
    }

    private User(Builder builder) {
        setUid(builder.uid);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setPubyear(builder.pubyear);
    }


    /**
     * Get Set 方法必须有
     *
     * @return
     */
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPubyear() {
        return pubyear;
    }

    public void setPubyear(Integer pubyear) {
        this.pubyear = pubyear;
    }

    public static final class Builder {
        private int uid;
        private String firstName;
        private String lastName;
        private Integer pubyear;

        public Builder() {
        }

        public Builder uid(int val) {
            uid = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder pubyear(Integer val) {
            pubyear = val;
            return this;
        }

        public User build() {
            return new User(this);
        }


    }


    @NonNull
    @Override
    public String toString() {
        return uid + firstName + lastName + "\n";
    }
}
