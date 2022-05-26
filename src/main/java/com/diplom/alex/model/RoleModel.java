package com.diplom.alex.model;

public enum RoleModel {

    TEACHER, STUDENT;

    public static RoleModel getRole(UserModel user) {
        int roleId = user.getRoleId();
        return RoleModel.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
