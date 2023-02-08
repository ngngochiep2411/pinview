package com.example.smarttable;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class UserModel {

    @SmartColumn
    String rank;
    @SmartColumn
    String name1;
    @SmartColumn
    String name2;
    @SmartColumn
    String name3;
    @SmartColumn
    String name4;
    @SmartColumn
    String name5;
    @SmartColumn
    String name6;
    @SmartColumn
    String name7;
    @SmartColumn
    String name8;

    public UserModel(String rank, String name1, String name2, String name3, String name4, String name5, String name6, String name7, String name8) {
        this.rank = rank;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name5 = name5;
        this.name6 = name6;
        this.name7 = name7;
        this.name8 = name8;
    }
}
