package com.lavasoft;

import javax.xml.bind.annotation.*;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="GetIpLocation_2_0Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getIpLocation20Result"
})
@XmlRootElement(name = "GetIpLocation_2_0Response")
public class GetIpLocation20Response {

  @XmlElement(name = "GetIpLocation_2_0Result")
  protected String getIpLocation20Result;

  /**
   * Gets the value of the getIpLocation20Result property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getGetIpLocation20Result() {
    return getIpLocation20Result;
  }

  /**
   * Sets the value of the getIpLocation20Result property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setGetIpLocation20Result(String value) {
    this.getIpLocation20Result = value;
  }

}
