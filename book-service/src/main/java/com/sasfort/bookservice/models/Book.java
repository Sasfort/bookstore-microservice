package com.sasfort.bookservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String description;
    private String author;
}
