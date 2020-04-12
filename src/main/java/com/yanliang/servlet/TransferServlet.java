package com.yanliang.servlet;

import com.yanliang.factory.ProxyFactory;
import com.yanliang.pojo.Result;
import com.yanliang.service.TransferService;
import com.yanliang.utils.JsonUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="transferServlet",urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    private ProxyFactory proxyFactory;
    private TransferService transferService;

//    // 1. 实例化service层对象
//    private TransferService transferService = new TransferServiceImpl();
    // 改造为通过Bean工程获取service层对象
//    private TransferService transferService = (TransferService) BeanFactory.getBean("transferService");

    // 从工程获取委托对象（委托对象增强了事务控制的功能）
//    private ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("");
//    private TransferService transferService = (TransferService) proxyFactory.getJdkProxy(BeanFactory.getBean("")) ;


    @Override
    public void init() throws ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        proxyFactory = (ProxyFactory) webApplicationContext.getBean("proxyFactory");
        transferService = (TransferService) proxyFactory.getJdkProxy(webApplicationContext.getBean("transferService"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");

        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);

        Result result = new Result();

        try {

            // 2. 调用service层方法
            transferService.transfer(fromCardNo,toCardNo,money);
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
            result.setMessage(e.toString());
        }

        // 响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }
}
