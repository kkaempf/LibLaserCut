/*
  This file is part of LibLaserCut.
  Copyright (C) 2011 - 2014 Thomas Oster <mail@thomas-oster.de>

  LibLaserCut is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  LibLaserCut is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with LibLaserCut. If not, see <http://www.gnu.org/licenses/>.

 */
package de.thomas_oster.liblasercut;

import de.thomas_oster.liblasercut.FloatPowerSpeedProperty;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * The LaserProperty holds all the parameters for parts of the LaserJob.
 * The Frequency value is ignored for Engraving operations
 *
 * @author oster
 */
public class FloatPowerSpeedFocusProperty extends FloatPowerSpeedProperty
{
  private static final String FOCUS = "focus";
  private float focus = 0;
  private boolean hideFocus = false;

  public FloatPowerSpeedFocusProperty()
  {
      super();
  }

  public FloatPowerSpeedFocusProperty(boolean hideFocus)
  {
      super();
      this.hideFocus = hideFocus;
  }


  /**
   * Sets the Focus aka moves the Z axis. Values are given in mm.
   * Positive values move the Z axis down aka makes the distance between
   * laser and object bigger.
   * The possible range depends on the LaserCutter, so wrong setting
   * may result in IllegalJobExceptions
   * @param focus the relative Distance from object to Laser in mm
   */
  public void setFocus(float focus)
  {
    this.focus = focus;
  }

  /**
   * Returns the relative (to the distance at starting the job) distance
   * between laser and object in mm/10s
   */
  public float getFocus()
  {
    return this.focus;
  }

  public void setHideFocus(boolean hf) {
    hideFocus = hf;
  }

  public boolean isHideFocus() {
    return hideFocus;
  }

  @Override
  public FloatPowerSpeedFocusProperty clone()
  {
    FloatPowerSpeedFocusProperty p = new FloatPowerSpeedFocusProperty(this.isHideFocus());
    try {
      for (String s:this.getPropertyKeys())
      {
        p.setProperty(s, this.getProperty(s));
      }
    }
    catch (Exception e)
    {
    }
    
    return p;
  }

  private static final String[] propertyNames = new String[]{"power", "speed", "focus"};
  
  @Override
  public String[] getPropertyKeys()
  {
    if (this.isHideFocus()) {
      return super.getPropertyKeys();
    }
    else
    {
      LinkedList<String> result = new LinkedList<String>();
      result.addAll(Arrays.asList(super.getPropertyKeys()));
      result.add(FOCUS);
      return result.toArray(new String[0]);  
    }
  }

  @Override
  public Object getProperty(String name)
  {
    if (FOCUS.equals(name)) {
      return this.getFocus();
    }
    return super.getProperty(name);
  }

  @Override
  public void setProperty(String name, Object value)
  {
    if (FOCUS.equals(name)) {
      this.setFocus((Float) value);
    }
    else
    {
      super.setProperty(name, value);
    }
  }

  @Override
  public Object getMinimumValue(String name)
  {
    if (FOCUS.equals(name)) {
      return null;
    }
    return super.getMinimumValue(name);
  }

  @Override
  public Object getMaximumValue(String name)
  {
    if (FOCUS.equals(name)) {
      return null;
    }
    return super.getMaximumValue(name);
  }  

  @Override
  public Object[] getPossibleValues(String name)
  {
    return null;
  }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FloatPowerSpeedFocusProperty other = (FloatPowerSpeedFocusProperty) obj;
        if (Float.floatToIntBits(this.focus) != Float.floatToIntBits(other.focus)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Float.floatToIntBits(this.focus);
        hash = 67 * hash + super.hashCode();
        return hash;
    }

}
