package com.shino.onlineanswer.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("take_course_view")
public class TakeCourse {
    @TableField("username")
    private String username;
    @MppMultiId("studentID")
    private Integer studentID;
    @TableField("studentName")
    private String studentName;
    @MppMultiId("courseID")
    private Integer courseID;
    @TableField("courseName")
    private String courseName;
    @TableField("type")
    private String type;
}
