package com.group04.docker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "suppliers")
public class Suppliers {
    @Id
    private String _id;
    private String companyName;
    private String contactName;
    private String phone;
    private String bussinessPage;
}
