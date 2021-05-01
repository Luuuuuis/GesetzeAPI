/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 19:00.
 * Copyright (c) 2021.
 */

/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 18:14.
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
