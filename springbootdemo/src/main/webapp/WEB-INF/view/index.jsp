<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/6/24
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.css"></link>
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-theme.css"></link>
    <link rel="stylesheet" type="text/css" href="/JQuery/css/jquery-ui.structure.css"></link>
    <link rel="stylesheet" type="text/css" href="/JQuery/css/jquery-ui.css"></link>
    <link rel="stylesheet" type="text/css" href="/JQuery/css/jquery-ui.theme.css"></link>


     <script type = 'text/javascript' src='/JQuery/js/jquery-3.4.1.js'></script>
    <script type = 'text/javascript' src='/JQuery/css/jquery-ui.js'></script>
    <script type = 'text/javascript' src='/bootstrap/js/bootstrap.js'></script>
    <script type = 'text/javascript' src='/bootstrap/js/bootstrap.min.js'></script>
    <style>
        .fakeimg {
            height: 200px;
            background: #aaa;
        }
    </style>

</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>我的第一个 Bootstrap 页面1111111</h1>
    <p>重置浏览器窗口大小查看效果！</p>
    </div>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">网站名</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">主页</a></li>
                    <li><a href="#">页面 2</a></li>
                    <li><a href="#">页面 3</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <h2>关于我</h2>
                <h5>我的照片:</h5>
                <div class="fakeimg">这边插入图像</div>
                <p>关于我的介绍..</p>
                <h3>链接</h3>
                <p>描述文本。</p>
                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href="#">链接 1</a></li>
                    <li><a href="#">链接 2</a></li>
                    <li><a href="#">链接 3</a></li>
                </ul>
                <hr class="hidden-sm hidden-md hidden-lg">
            </div>
            <div class="col-sm-8">
                <h2>标题</h2>
                <h5>副标题</h5>
                <div class="fakeimg">图像</div>
                <p>一些文本..</p>
                <p>菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！</p>
                <br>
                <h2>标题</h2>
                <h5>副标题</h5>
                <div class="fakeimg">图像</div>
                <p>一些文本..</p>
                <p>菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！</p>
            </div>
        </div>
    </div>

    <div class="jumbotron text-center" style="margin-bottom:0">
        <p>底部内容</p>
    </div>
</body>
</html>
