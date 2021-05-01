/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 18:19.
 * Copyright (c) 2021.
 */

/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 18:16.
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
