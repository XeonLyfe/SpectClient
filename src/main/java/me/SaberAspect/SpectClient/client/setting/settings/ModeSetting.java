package me.SaberAspect.SpectClient.client.setting.settings;

import java.util.Arrays;
import java.util.List;

import com.lukflug.panelstudio.settings.EnumSetting;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.Setting;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class ModeSetting extends Setting implements EnumSetting {
  public int index;
  
  public List<String> modes;
  
  public ModeSetting(String name, Module parent, String defaultMode, String... modes) {
    this.name = name;
    this.parent = parent;
    this.modes = Arrays.asList(modes);
    this.index = this.modes.indexOf(defaultMode);
  }
  
  public String getMode() {
    return this.modes.get(this.index);
  }
  
  public void setMode(String mode) {
	  this.index = this.modes.indexOf(mode);
	  
	   if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
  }
  
  public boolean is(String mode) {
    return (this.index == this.modes.indexOf(mode));
  }
  
  public void cycle() {
    if (this.index < this.modes.size() - 1) {
      this.index++;
    } else {
      this.index = 0;
    } 
  }

@Override
public String getValueName() {
	return this.modes.get(this.index);
}

@Override
public void increment() {
	 if (this.index < this.modes.size() - 1) {
	      this.index++;
	    } else {
	      this.index = 0;
	    } 
	  }
}
