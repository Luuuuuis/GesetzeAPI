/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 02.05.21, 13:50.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeparser.model;

import lombok.Data;

import java.util.List;

@Data
public class LawBook {

    public final String name, title;
    public final List<Law> laws;

}
