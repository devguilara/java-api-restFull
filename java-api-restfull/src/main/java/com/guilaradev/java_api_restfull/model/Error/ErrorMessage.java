package com.guilaradev.java_api_restfull.model.Error;

public class ErrorMessage {
    private String errTitulo;

    private Integer errStatus;

    private String errMessage;

    public String getErrTitulo() {
        return errTitulo;
    }

    public void setErrTitulo(String errTitulo) {
        this.errTitulo = errTitulo;
    }

    public Integer getErrStatus() {
        return errStatus;
    }

    public void setErrStatus(Integer errStatus) {
        this.errStatus = errStatus;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

      //Constructor
      public ErrorMessage(String errTitulo, Integer errStatus, String errMessage) {
        this.errTitulo = errTitulo;
        this.errStatus = errStatus;
        this.errMessage = errMessage;
    }

}
