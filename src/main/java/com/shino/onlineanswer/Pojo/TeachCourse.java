package com.shino.onlineanswer.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teach_course_view")
public class TeachCourse {
    @TableField(value = "teacherID")
    private Integer teacherID;
    @TableField(value = "TeacherName")
    private String teacherName;
    @TableField(value = "teacherTitle")
    private String teacherTitle;
    @TableField(value = "teacherComment")
    private String teacherComment;
    @TableField(value = "courseID")
    private Integer courseID;
    @TableField(value = "courseName")
    private String courseName;
    @TableField(value = "CourseComment")
    private String CourseComment;
    @TableField(value = "username")
    private String username;
}
