package com.liufu.store.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class User extends Base implements Serializable {
    private int uid;
    private String username;
    private String password;
    private String salt;
    private int gender;
    private String phone;
    private String email;
    private String avatar;
    private Integer isDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(gender, user.gender) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(avatar, user.avatar) && Objects.equals(isDelete, user.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, salt, gender, phone, email, avatar, isDelete);
    }
}
