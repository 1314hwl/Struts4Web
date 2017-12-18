package com.cn.wh;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.hwl.modle.DbDao;

/**
 * 模拟登陆:<br>
 * M:modle,即模型，对应的javaBean<br>
 * V:view视图，对应的jsp页面<br>
 * C:Controller控制器，对应Servlet;<br>
 * 
 * @Description
 * @author Hero
 * @date 2017年12月11日 下午9:45:52
 */
@WebServlet(name = "/LoginServlet", urlPatterns = { "/login" }, initParams = {
        @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
        @WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/java4web"),
        @WebInitParam(name = "user", value = "root"), @WebInitParam(name = "password", value = "123456") })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String mysqlDriver = config.getInitParameter("driver");
        String mysqlUrl = config.getInitParameter("url");
        String mysqlUser = config.getInitParameter("user");
        String mysqlPassword = config.getInitParameter("password");
        String errMsg = "";
        RequestDispatcher requestDispatcher;
        String userName = request.getParameter("userName");
        String password = request.getParameter("pass");
        DbDao dbDao = new DbDao(mysqlDriver, mysqlUrl, mysqlUser, mysqlPassword);
        try {
            ResultSet resultSet = dbDao.queryUser("select password from user " + " where name = ?", userName);
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("name", userName);
                    requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    errMsg += "您输入的用户名或密码不符合，请重新输入";
                }
            } else {
                errMsg += "您的用户名不存在，请先注册";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (errMsg != null && !errMsg.equals("")) {
            requestDispatcher = request.getRequestDispatcher("/login.jsp");
            request.setAttribute("err", errMsg);
            requestDispatcher.forward(request, response);
        }

    }

}
