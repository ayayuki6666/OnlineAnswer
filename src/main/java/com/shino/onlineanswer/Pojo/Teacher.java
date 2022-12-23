package com.shino.onlineanswer.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "teacher")
public class Teacher {
    @TableId(value = "teacherID",type = IdType.AUTO)
    private Integer teacherID;
    @TableField(value = "TeacherName")
    private String TeacherName;
    @TableField(value = "TeacherTitle")
    private String TeacherTitle;
    @TableField(value = "teacherComment")
    private String teacherComment;
}
