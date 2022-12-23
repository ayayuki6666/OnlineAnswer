package com.shino.onlineanswer.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "student")
public class Student {
    @TableId("studentID")
    private Integer studentID;
    @TableField("studentName")
    private String studentName;
    @TableField("username")
    private String username;
}
