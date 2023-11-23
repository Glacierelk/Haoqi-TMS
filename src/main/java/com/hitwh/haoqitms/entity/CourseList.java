package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseList extends Course {
    private String instructorName;

    @Override
    public String toString() {
        return "CourseList{" +
                "instructorName='" + instructorName + '\'' +
                "} " + super.toString();
    }
}
