package de.luuuuuis.gesetzeapi.model;

import lombok.Getter;

import java.util.List;

@Getter
public class LawBook {

    private List<Law> laws;
    private String title, name;

}