package com.yxt.yyd.common.base.utils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;

/**
 * @author dimengzhe
 * @date 2021/9/3 23:59
 * @description 发送短信调用接口
 */

public class MsgWs {

    public static String SendWaitWorkMsg(String mobile, String msg) {
        try {
            String urlname = "http://sdk1.mb345.com/ws/LinkWS.asmx";
            String soapActionURI = "http://tempuri.org/BatchSend";
            Service s = new Service();
            Call call = (Call) s.createCall();
            call.setTimeout(new Integer(5000));
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapActionURI);
            // wsdl中接口名称
            call.setOperationName(new QName("http://tempuri.org/", "BatchSend"));

            call.setTargetEndpointAddress(urlname);
            call.addParameter(new QName("http://tempuri.org/", "CorpID"), XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "Pwd"), XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "Mobile"), XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "Content"), XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "Cell"), XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "SendTime"), XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            String[] fn01 = {"YXT004911", "yyundong@yuxintong", mobile, msg, "", ""};
            String val = (String) call.invoke(fn01);
            return val;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
