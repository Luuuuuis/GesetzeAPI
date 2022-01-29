/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 29.01.22, 18:16.
 * Copyright (c) 2022.
 */

package de.luuuuuis.gesetzeapi.model;

import lombok.Data;

import java.util.List;

@Data
public class LawBook {

    private String title, name;
    private List<Law> laws;

}