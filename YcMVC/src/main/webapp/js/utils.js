/**
 * Created by yong on 2016/12/13 0013.
 */
//立即执行的js
(function() {
    //获取contextPath
    var contextPath = getContextPath();
    //获取basePath
    var basePath = getBasePath();
    //将获取到contextPath和basePath分别赋值给window对象的g_contextPath属性和g_basePath属性
    window.g_contextPath = contextPath;
    window.g_basePath = basePath;
})();

/**
 * @author 孤傲苍狼
 * 获得项目根路径，等价于jsp页面中
 *  <%
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 * 使用方法：getBasePath();
 * @returns 项目的根路径
 *
 */
function getBasePath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPath + projectName);
}

/**
 * @author 孤傲苍狼
 * 获取Web应用的contextPath，等价于jsp页面中
 *  <%
 String path = request.getContextPath();
 %>
 * 使用方法:getContextPath();
 * @returns /项目名称(/EasyUIStudy_20141104)
 */
function getContextPath() {
    return window.document.location.pathname.substring(0, window.document.location.pathname.indexOf('\/', 1));
};