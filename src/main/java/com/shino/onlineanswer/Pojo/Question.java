package com.shino.onlineanswer.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("question")
public class Question {
    @TableId(value = "questionNo",type = IdType.AUTO)
    private Integer questionNO;
    @TableField("courseID")
    private Integer courseID;
    @TableField("questionTitle")
    private String questionTitle;
    @TableField("type")
    private String type;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;
    @TableField("username")
    private String username;
}
