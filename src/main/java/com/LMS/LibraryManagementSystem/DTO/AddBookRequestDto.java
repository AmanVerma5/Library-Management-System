package com.LMS.LibraryManagementSystem.DTO;

import com.LMS.LibraryManagementSystem.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddBookRequestDto {

    private String title;
    private int price;
    private Genre genre;
    private int authorId;
}
