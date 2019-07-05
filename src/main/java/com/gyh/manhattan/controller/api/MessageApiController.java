package com.gyh.manhattan.controller.api;

import com.gyh.manhattan.base.annotation.RequestCallBack;
import com.gyh.manhattan.base.controller.AbstractBaseController;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.model.SysMessageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/message")
@RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
public class MessageApiController extends AbstractBaseController<SysMessageModel> {

    @RequestMapping(value = "/addEdit", method = RequestMethod.POST)
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
    public ExecuteResult<SysMessageModel> addEditRecord(HttpServletRequest request) {
        SysMessageModel sysMessageModel = new SysMessageModel();
        sysMessageModel.setUserId(1L);
        sysMessageModel.setTitle(request.getParameter("title"));
        sysMessageModel.setContent(request.getParameter("content"));
        return service.addEditRecord(sysMessageModel);
    }
}
