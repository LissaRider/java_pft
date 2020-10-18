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
 * &amp;lt;element name="GetCountryNameByISO2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getCountryNameByISO2Result"
})
@XmlRootElement(name = "GetCountryNameByISO2Response")
public class GetCountryNameByISO2Response {

  @XmlElement(name = "GetCountryNameByISO2Result")
  protected String getCountryNameByISO2Result;

  /**
   * Gets the value of the getCountryNameByISO2Result property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getGetCountryNameByISO2Result() {
    return getCountryNameByISO2Result;
  }

  /**
   * Sets the value of the getCountryNameByISO2Result property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setGetCountryNameByISO2Result(String value) {
    this.getCountryNameByISO2Result = value;
  }

}
