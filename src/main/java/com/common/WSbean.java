package com.common;

import javax.xml.namespace.QName;

/**
 * Created by gaoyang on 17/8/15.
 */
public class WSbean {
    String parametername;
    String parametervalue;
    QName XMLType;

    public String getParametername() {
        return parametername;
    }

    public void setParametername(String parametername) {
        this.parametername = parametername;
    }

    public String getParametervalue() {
        return parametervalue;
    }

    public void setParametervalue(String parametervalue) {
        this.parametervalue = parametervalue;
    }

    public QName getXMLType() {
        return XMLType;
    }

    public void setXMLType(QName XMLType) {
        this.XMLType = XMLType;
    }
}
