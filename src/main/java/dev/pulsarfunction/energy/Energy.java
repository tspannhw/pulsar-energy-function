package dev.pulsarfunction.energy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.StringJoiner;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Energy  {

  private String uuid;
  private String systemtime;
  private   double  current;
  private   double  voltage;
  private   double  power;
  private   double  total;
  private String fanstatus;
  private String diskusage;
  private   double  memory;
  private String swver;
  private String hwver;
  private String type;
  private String model;
  private String mac;
  private String deviceId;
  private String hwId;
  private String fwId;
  private String oemId;
  private String alias;
  private String devname;
  private String iconhash;
  private String feature;
  private String activemode;
  private  int relaystate;
  private  int updating;
  private  int rssi;
  private  int ledoff;
  private   double  latitude;
  private   double  longitude;
  private  int ontime;
  private  int day;
  private  int index;
  private String zonestr;
  private String tzstr;
  private  int dstoffset;
  private String host;
  private   double  currentconsumption;
  private String devicetime;
  private String ledon;
  private String end;
  private String te;
  private   double  cpu;
  private String sw_ver;
  private String hw_ver;

  @Override
  public String toString() {
    return new StringJoiner(", ", Energy.class.getSimpleName() + "[", "]")
            .add("uuid='" + uuid + "'")
            .add("systemtime='" + systemtime + "'")
            .add("current=" + current)
            .add("voltage=" + voltage)
            .add("power=" + power)
            .add("total=" + total)
            .add("fanstatus='" + fanstatus + "'")
            .add("diskusage='" + diskusage + "'")
            .add("memory=" + memory)
            .add("swver='" + swver + "'")
            .add("hwver='" + hwver + "'")
            .add("type='" + type + "'")
            .add("model='" + model + "'")
            .add("mac='" + mac + "'")
            .add("deviceId='" + deviceId + "'")
            .add("hwId='" + hwId + "'")
            .add("fwId='" + fwId + "'")
            .add("oemId='" + oemId + "'")
            .add("alias='" + alias + "'")
            .add("devname='" + devname + "'")
            .add("iconhash='" + iconhash + "'")
            .add("feature='" + feature + "'")
            .add("activemode='" + activemode + "'")
            .add("relaystate=" + relaystate)
            .add("updating=" + updating)
            .add("rssi=" + rssi)
            .add("ledoff=" + ledoff)
            .add("latitude=" + latitude)
            .add("longitude=" + longitude)
            .add("ontime=" + ontime)
            .add("day=" + day)
            .add("index=" + index)
            .add("zonestr='" + zonestr + "'")
            .add("tzstr='" + tzstr + "'")
            .add("dstoffset=" + dstoffset)
            .add("host='" + host + "'")
            .add("currentconsumption=" + currentconsumption)
            .add("devicetime='" + devicetime + "'")
            .add("ledon='" + ledon + "'")
            .add("end='" + end + "'")
            .add("te='" + te + "'")
            .add("cpu=" + cpu)
            .add("sw_ver='" + sw_ver + "'")
            .add("hw_ver='" + hw_ver + "'")
            .toString();
  }

  /* */
  public Energy(String uuid, String systemtime, double current, double voltage, double power, double total, String fanstatus, String diskusage, double memory, String swver, String hwver, String type, String model, String mac, String deviceId, String hwId, String fwId, String oemId, String alias, String devname, String iconhash, String feature, String activemode, int relaystate, int updating, int rssi, int ledoff, double latitude, double longitude, int ontime, int day, int index, String zonestr, String tzstr, int dstoffset, String host, double currentconsumption, String devicetime, String ledon, String end, String te, double cpu, String sw_ver, String hw_ver) {
    super();
    this.uuid = uuid;
    this.systemtime = systemtime;
    this.current = current;
    this.voltage = voltage;
    this.power = power;
    this.total = total;
    this.fanstatus = fanstatus;
    this.diskusage = diskusage;
    this.memory = memory;
    this.swver = swver;
    this.hwver = hwver;
    this.type = type;
    this.model = model;
    this.mac = mac;
    this.deviceId = deviceId;
    this.hwId = hwId;
    this.fwId = fwId;
    this.oemId = oemId;
    this.alias = alias;
    this.devname = devname;
    this.iconhash = iconhash;
    this.feature = feature;
    this.activemode = activemode;
    this.relaystate = relaystate;
    this.updating = updating;
    this.rssi = rssi;
    this.ledoff = ledoff;
    this.latitude = latitude;
    this.longitude = longitude;
    this.ontime = ontime;
    this.day = day;
    this.index = index;
    this.zonestr = zonestr;
    this.tzstr = tzstr;
    this.dstoffset = dstoffset;
    this.host = host;
    this.currentconsumption = currentconsumption;
    this.devicetime = devicetime;
    this.ledon = ledon;
    this.end = end;
    this.te = te;
    this.cpu = cpu;
    this.sw_ver = sw_ver;
    this.hw_ver = hw_ver;
  }

  public String getHw_ver() {
    return hw_ver;
  }

  public void setHw_ver(String hw_ver) {
    this.hw_ver = hw_ver;
  }

  public String getSw_ver() {
    return sw_ver;
  }

  public void setSw_ver(String sw_ver) {
    this.sw_ver = sw_ver;
  }

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Energy() {
    super();
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getSystemtime() {
    return systemtime;
  }

  public void setSystemtime(String systemtime) {
    this.systemtime = systemtime;
  }

  public  double  getCurrent() {
    return current;
  }

  public void setCurrent( double  current) {
    this.current = current;
  }

  public  double  getVoltage() {
    return voltage;
  }

  public void setVoltage( double  voltage) {
    this.voltage = voltage;
  }

  public  double  getPower() {
    return power;
  }

  public void setPower( double  power) {
    this.power = power;
  }

  public  double  getTotal() {
    return total;
  }

  public void setTotal( double  total) {
    this.total = total;
  }

  public String getFanstatus() {
    return fanstatus;
  }

  public void setFanstatus(String fanstatus) {
    this.fanstatus = fanstatus;
  }

  public String getDiskusage() {
    return diskusage;
  }

  public void setDiskusage(String diskusage) {
    this.diskusage = diskusage;
  }

  public  double  getMemory() {
    return memory;
  }

  public void setMemory( double  memory) {
    this.memory = memory;
  }

  public String getSwver() {
    return swver;
  }

  public void setSwver(String swver) {
    this.swver = swver;
  }

  public String getHwver() {
    return hwver;
  }

  public void setHwver(String hwver) {
    this.hwver = hwver;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getHwId() {
    return hwId;
  }

  public void setHwId(String hwId) {
    this.hwId = hwId;
  }

  public String getFwId() {
    return fwId;
  }

  public void setFwId(String fwId) {
    this.fwId = fwId;
  }

  public String getOemId() {
    return oemId;
  }

  public void setOemId(String oemId) {
    this.oemId = oemId;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getDevname() {
    return devname;
  }

  public void setDevname(String devname) {
    this.devname = devname;
  }

  public String getIconhash() {
    return iconhash;
  }

  public void setIconhash(String iconhash) {
    this.iconhash = iconhash;
  }

  public String getFeature() {
    return feature;
  }

  public void setFeature(String feature) {
    this.feature = feature;
  }

  public String getActivemode() {
    return activemode;
  }

  public void setActivemode(String activemode) {
    this.activemode = activemode;
  }

  public int getRelaystate() {
    return relaystate;
  }

  public void setRelaystate(int relaystate) {
    this.relaystate = relaystate;
  }

  public int getUpdating() {
    return updating;
  }

  public void setUpdating(int updating) {
    this.updating = updating;
  }

  public int getRssi() {
    return rssi;
  }

  public void setRssi(int rssi) {
    this.rssi = rssi;
  }

  public int getLedoff() {
    return ledoff;
  }

  public void setLedoff(int ledoff) {
    this.ledoff = ledoff;
  }

  public  double  getLatitude() {
    return latitude;
  }

  public void setLatitude( double  latitude) {
    this.latitude = latitude;
  }

  public  double  getLongitude() {
    return  longitude;
  }

  public void setLongitude( double longitude) {
    this.longitude =  longitude;
  }

  public int getOntime() {
    return ontime;
  }

  public void setOntime(int ontime) {
    this.ontime = ontime;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getZonestr() {
    return zonestr;
  }

  public void setZonestr(String zonestr) {
    this.zonestr = zonestr;
  }

  public String getTzstr() {
    return tzstr;
  }

  public void setTzstr(String tzstr) {
    this.tzstr = tzstr;
  }

  public int getDstoffset() {
    return dstoffset;
  }

  public void setDstoffset(int dstoffset) {
    this.dstoffset = dstoffset;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public  double  getCurrentconsumption() {
    return currentconsumption;
  }

  public void setCurrentconsumption( double  currentconsumption) {
    this.currentconsumption = currentconsumption;
  }

  public String getDevicetime() {
    return devicetime;
  }

  public void setDevicetime(String devicetime) {
    this.devicetime = devicetime;
  }

  public String getLedon() {
    return ledon;
  }

  public void setLedon(String ledon) {
    this.ledon = ledon;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getTe() {
    return te;
  }

  public void setTe(String te) {
    this.te = te;
  }

  public  double  getCpu() {
    return cpu;
  }

  public void setCpu( double  cpu) {
    this.cpu = cpu;
  }
}










