package com.gyh.manhattan.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author
 */
public class ExecuteResult<T> {

    private String status = ConstParam.STATUS_FAILED;
    private String message;
    private String fullErrorMessage;
    private String redirectUrl;
    private T result;
    private List<T> resultList;
    private PageInfo<T> pageInfo;

    public ExecuteResult() {
    }

    public ExecuteResult(String status, String message, String fullErrorMessage, String redirectUrl) {
        this.status = status;
        this.message = message;
        this.fullErrorMessage = fullErrorMessage;
        this.redirectUrl = redirectUrl;
    }

    public ExecuteResult(String status, String message, String fullErrorMessage, String redirectUrl, T result, List<T> resultList) {
        this.status = status;
        this.message = message;
        this.fullErrorMessage = fullErrorMessage;
        this.redirectUrl = redirectUrl;
        this.result = result;
        this.resultList = resultList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullErrorMessage() {
        return fullErrorMessage;
    }

    public void setFullErrorMessage(String fullErrorMessage) {
        this.fullErrorMessage = fullErrorMessage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
