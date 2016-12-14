var AjaxUtil = {

    // 基础选项
    options : {
        method : "get", // 默认提交的方法,get post
        url : "", // 请求的路径 required
        params : {}, // 请求的参数
        type : 'text', // 返回的内容的类型,text,xml,json
        callback : function() {
        }// 回调函数 required
    },

    // 创建XMLHttpRequest对象
    createRequest : function() {
        var xmlhttp;
        try {
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");// IE6以上版本
        } catch (e) {
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");// IE6以下版本
            } catch (e) {
                try {
                    xmlhttp = new XMLHttpRequest();
                    if (xmlhttp.overrideMimeType) {
                        xmlhttp.overrideMimeType("text/xml");
                    }
                } catch (e) {
                    alert("您的浏览器不支持Ajax");
                }
            }
        }
        return xmlhttp;
    },
    // 设置基础选项
    setOptions : function(newOptions) {
        for ( var pro in newOptions) {
            this.options[pro] = newOptions[pro];
        }
    },
    // 格式化请求参数
    formateParameters : function() {
        var paramsArray = [];
        var params = this.options.params;
        for ( var pro in params) {
            var paramValue = params[pro];
            /*if(this.options.method.toUpperCase() === "GET")
             {
             paramValue = encodeURIComponent(params[pro]);
             }*/
            paramsArray.push(pro + "=" + paramValue);
        }
        return paramsArray.join("&");
    },

    // 状态改变的处理
    readystatechange : function(xmlhttp) {
        // 获取返回值
        var returnValue;
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            switch (this.options.type) {
                case "xml":
                    returnValue = xmlhttp.responseXML;
                    break;
                case "json":
                    var jsonText = xmlhttp.responseText;
                    if(jsonText){
                        returnValue = eval("(" + jsonText + ")");
                    }
                    break;
                default:
                    returnValue = xmlhttp.responseText;
                    break;
            }
            if (returnValue) {
                this.options.callback.call(this, returnValue);
            } else {
                this.options.callback.call(this);
            }
        }
    },

    // 发送Ajax请求
    request : function(options) {
        var ajaxObj = this;

        // 设置参数
        ajaxObj.setOptions.call(ajaxObj, options);

        // 创建XMLHttpRequest对象
        var xmlhttp = ajaxObj.createRequest.call(ajaxObj);

        // 设置回调函数
        xmlhttp.onreadystatechange = function() {
            ajaxObj.readystatechange.call(ajaxObj, xmlhttp);
        };

        // 格式化参数
        var formateParams = ajaxObj.formateParameters.call(ajaxObj);

        // 请求的方式
        var method = ajaxObj.options.method;
        var url = ajaxObj.options.url;

        if ("GET" === method.toUpperCase()) {
            url += "?" + formateParams;
        }

        // 建立连接
        xmlhttp.open(method, url, true);

        if ("GET" === method.toUpperCase()) {
            xmlhttp.send(null);
        } else if ("POST" === method.toUpperCase()) {
            // 如果是POST提交，设置请求头信息
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send(formateParams);
        }
    }
};