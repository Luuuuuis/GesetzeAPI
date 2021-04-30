/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 24.04.21, 23:40.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeapi.controllers;

import de.luuuuuis.gesetzeapi.GesetzeAPI;
import de.luuuuuis.gesetzeapi.model.Law;
import de.luuuuuis.gesetzeapi.model.LawBook;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BooksController {

    @GetMapping("/")
    public List<LawBookInfo> getLawBooks() {
        return GesetzeAPI.lawBooks.stream().map(lawBook -> LawBookInfo.builder().name(lawBook.getName()).title(lawBook.getTitle()).build()).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public LawBook getLawBook(@PathVariable("name") String name) {
        return LawBookInfo.builder().name(name).title(name).build().getLawBook();
    }

    @GetMapping("/{name}/{paragraph}")
    public Law getLaw(@PathVariable("name") String name, @PathVariable("paragraph") int paragraph) {
        return LawBookInfo.builder().name(name).title(name).build().getLawBook().getLaws().stream().filter(law ->
                law.getParagraph().endsWith(String.valueOf(paragraph))).findFirst().orElseThrow();
    }

    @Builder
    @Getter
    static class LawBookInfo {

        private final String name, title;

        private LawBook getLawBook() {
            return GesetzeAPI.lawBooks.stream().filter(book -> book.getName().equalsIgnoreCase(name) || book.getTitle().equalsIgnoreCase(title)).findFirst().orElseThrow();
        }
    }
}
