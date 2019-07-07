/**
 * QuestRating.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.backend.wsdl;

public class QuestRating  implements java.io.Serializable {
    private java.lang.String comment;

    private int difficultyRating;

    private long id;

    private int qualityRating;

    private long questId;

    private long userId;

    private java.lang.Integer version;

    public QuestRating() {
    }

    public QuestRating(
           java.lang.String comment,
           int difficultyRating,
           long id,
           int qualityRating,
           long questId,
           long userId,
           java.lang.Integer version) {
           this.comment = comment;
           this.difficultyRating = difficultyRating;
           this.id = id;
           this.qualityRating = qualityRating;
           this.questId = questId;
           this.userId = userId;
           this.version = version;
    }


    /**
     * Gets the comment value for this QuestRating.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this QuestRating.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the difficultyRating value for this QuestRating.
     * 
     * @return difficultyRating
     */
    public int getDifficultyRating() {
        return difficultyRating;
    }


    /**
     * Sets the difficultyRating value for this QuestRating.
     * 
     * @param difficultyRating
     */
    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }


    /**
     * Gets the id value for this QuestRating.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this QuestRating.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the qualityRating value for this QuestRating.
     * 
     * @return qualityRating
     */
    public int getQualityRating() {
        return qualityRating;
    }


    /**
     * Sets the qualityRating value for this QuestRating.
     * 
     * @param qualityRating
     */
    public void setQualityRating(int qualityRating) {
        this.qualityRating = qualityRating;
    }


    /**
     * Gets the questId value for this QuestRating.
     * 
     * @return questId
     */
    public long getQuestId() {
        return questId;
    }


    /**
     * Sets the questId value for this QuestRating.
     * 
     * @param questId
     */
    public void setQuestId(long questId) {
        this.questId = questId;
    }


    /**
     * Gets the userId value for this QuestRating.
     * 
     * @return userId
     */
    public long getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this QuestRating.
     * 
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }


    /**
     * Gets the version value for this QuestRating.
     * 
     * @return version
     */
    public java.lang.Integer getVersion() {
        return version;
    }


    /**
     * Sets the version value for this QuestRating.
     * 
     * @param version
     */
    public void setVersion(java.lang.Integer version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QuestRating)) return false;
        QuestRating other = (QuestRating) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            this.difficultyRating == other.getDifficultyRating() &&
            this.id == other.getId() &&
            this.qualityRating == other.getQualityRating() &&
            this.questId == other.getQuestId() &&
            this.userId == other.getUserId() &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        _hashCode += getDifficultyRating();
        _hashCode += new Long(getId()).hashCode();
        _hashCode += getQualityRating();
        _hashCode += new Long(getQuestId()).hashCode();
        _hashCode += new Long(getUserId()).hashCode();
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QuestRating.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://backend.com/wsdl", "questRating"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("difficultyRating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "difficultyRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qualityRating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qualityRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("questId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "questId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
