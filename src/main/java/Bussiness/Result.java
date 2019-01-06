/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

/**
 *
 * @author lap10467
 */
public class Result {

    static final int OK = 200;
    static final int ACCEPTED = 202;
    static final int BAD_REQUEST = 400;

    String messenger="";
    int status=0;
    Object object=null;  

    public Result(String messenger, int status, Object object) {
        this.messenger = messenger;
        this.status = status;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
    
    public Result(String messenger, int status) {
        this.messenger = messenger;
        this.status = status;
    }

    public String getMessenger() {
        return messenger;
    }

    public int getStatus() {
        return status;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
