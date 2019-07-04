package com.gyh.manhattan.controller.api;

import com.gyh.manhattan.base.controller.AbstractBaseController;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.model.SysMessageModel;
import com.gyh.manhattan.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/message")
public class MessageApiController extends AbstractBaseController<SysMessageModel> {

    @RequestMapping(value = "/addEdit", method = RequestMethod.POST)
    public ExecuteResult<SysMessageModel> addEditRecord(HttpServletRequest request) {
        SysMessageModel sysMessageModel = new SysMessageModel();
        sysMessageModel.setUserId(1L);
        sysMessageModel.setTitle(request.getParameter("title"));
        sysMessageModel.setContent(request.getParameter("content"));
        return service.addEditRecord(sysMessageModel);
    }
}
