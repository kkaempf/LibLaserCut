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

import de.thomas_oster.liblasercut.FloatPowerSpeedFocusProperty;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * The LaserProperty holds all the parameters for parts of the LaserJob.
 * The Frequency value is ignored for Engraving operations
 *
 * @author oster
 */
public class FloatPowerSpeedFocusFrequencyProperty extends FloatPowerSpeedFocusProperty
{
  private static final String FREQUENCY = "frequency";
  private float power = 0;
  private float speed = 100;
  private float focus = 0;
  private int frequency = 500;
  private boolean hideFocus;

  public FloatPowerSpeedFocusFrequencyProperty()
  {
    super();
  }

  public FloatPowerSpeedFocusFrequencyProperty(boolean hideFocus)
  {
      this.hideFocus = hideFocus;
  }

 /**
   * Sets the Laserpower. Valid values are from 0 to 100.
   * In 3d-Raster mode, the intensity is scaled to this power setting
   */
  @Override
  public void setPower(float power)
  {
    power = power < 0 ? 0 : power;
    power = power > 100 ? 100 : power;
    this.power = power;
  }

  @Override
  public float getPower()
  {
    return power;
  }

  /**
   * Sets the speed for the Laser. Valid values is from 0 to 100
   */
  public void setSpeed(float speed)
  {
    speed = speed < 0 ? 0 : speed;
    speed = speed > 100 ? 100 : speed;
    this.speed = speed;
  }

  @Override
  public float getSpeed()
  {
    return speed;
  }

  public void setFrequency(int frequency)
  {
    this.frequency = frequency;
  }

  public int getFrequency()
  {
    return this.frequency;
  }

  @Override
  public FloatPowerSpeedFocusFrequencyProperty clone()
  {
    FloatPowerSpeedFocusFrequencyProperty p = new FloatPowerSpeedFocusFrequencyProperty(this.isHideFocus());
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

  @Override
  public String[] getPropertyKeys()
  {
    LinkedList<String> result = new LinkedList<String>();
    result.addAll(Arrays.asList(super.getPropertyKeys()));
    result.add(FREQUENCY);
    return result.toArray(new String[0]);  
  }

  @Override
  public Object getProperty(String name)
  {
    if (FREQUENCY.equals(name)) {
      return this.getFrequency();
    }
    return super.getProperty(name);
  }

  @Override
  public void setProperty(String name, Object value)
  {
    if (FREQUENCY.equals(name)) {
      this.setFrequency((Integer) value);
    }
    else
    {
      super.setProperty(name, value);
    }
  }

  @Override
  public Object getMinimumValue(String name)
  {
    if (FREQUENCY.equals(name)) {
      return null;
    }
    return super.getMinimumValue(name);
  }

  @Override
  public Object getMaximumValue(String name)
  {
    if (FREQUENCY.equals(name)) {
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
        final FloatPowerSpeedFocusFrequencyProperty other = (FloatPowerSpeedFocusFrequencyProperty) obj;

        if (Float.floatToIntBits(this.power) != Float.floatToIntBits(other.power)) {
            return false;
        }
        if (Float.floatToIntBits(this.speed) != Float.floatToIntBits(other.speed)) {
            return false;
        }
        if (Float.floatToIntBits(this.focus) != Float.floatToIntBits(other.focus)) {
            return false;
        }
        return this.frequency == other.frequency;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.frequency;
        hash = 67 * hash + super.hashCode();
        return hash;
    }

}
