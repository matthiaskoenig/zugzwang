package org.cytoscape.zugzwang.internal.customgraphicsmgr;

/*
 * #%L
 * Cytoscape Ding View/Presentation Impl (ding-presentation-impl)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.Comparator;
import org.cytoscape.view.presentation.customgraphics.CyCustomGraphics;

public class CGComparator implements Comparator<CyCustomGraphics> {
  public int compare(CyCustomGraphics o1, CyCustomGraphics o2) {
    String class1 = o1.getClass().getCanonicalName();
    String class2 = o2.getClass().getCanonicalName();
    if (!class1.equals(class2))
      return class1.compareTo(class2);

    return o1.getDisplayName().compareTo(o2.getDisplayName());
  }

  public boolean equals(Object obj) { return false; }
}
