package org.cnu.realcoding.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    private String name;
    private String kind;
    private String ownerName;
    private String ownerPhoneNumber;
    private List<String> medicalRecords;
}
