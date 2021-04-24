/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 24.04.21, 23:40.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeapi.model;

import lombok.Data;

import java.util.List;

@Data
public class LawBook {

    private List<Law> laws;
    private String title, name;

}