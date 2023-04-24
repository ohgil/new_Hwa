package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Review {
    public int id;
    public int member_id;
    public int product_id;
    public String text;
    public double grade;
    public String extra__writerName;

    public Review(Map<String, Object> reviewMap) {
        this.id = (int) reviewMap.get("id");
        this.member_id = (int) reviewMap.get("member_id");
        this.product_id = (int) reviewMap.get("product_id");
        this.text = (String) reviewMap.get("review");
        this.grade = (double) reviewMap.get("grade");
        if(reviewMap.get("extra__writerName") != null) {
            this.extra__writerName = (String)reviewMap.get("extra__writerName");
        }
    }
}
