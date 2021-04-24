/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 25.04.21, 00:47.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzelibrary.model;

import lombok.Data;

import java.util.List;

@Data
public class LawBook {

    private List<Law> laws;
    private String title, name;

}