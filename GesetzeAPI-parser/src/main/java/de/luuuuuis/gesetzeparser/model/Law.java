/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 02.05.21, 13:50.
 * Copyright (c) 2021.
 */
package de.luuuuuis.gesetzeparser.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Law {

    public final String paragraph, title, text, url;

}
