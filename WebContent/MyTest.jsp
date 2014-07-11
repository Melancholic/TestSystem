<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- Bootstrap -->
    <link href="bs/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="jquery-1.11.0.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bs/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function () {
            var q = 0;
            $.getJSON("GetQuestions", function (result) {
                q = result;

                for (var i = 0; i < result.questions.length; i++) {
                    $("#myTab").append("<li><a id=\"MyTabButton" + i + "\"></a></li>");
                    $("#MyTabButton" + i).text(i + 1);
                    $("#MyTabButton" + i).attr({
                        "href": "#MyTab" + i,
                        "data-toggle": "pill"
                    });

                    $("#myTab-content").append("<div id=\"MyTab" + i + "\"></div>");
                    $("#MyTab" + i).attr({
                        "class": "tab-pane"
                    });

                    if (result.questions[i].type == "text") {
                        $("#MyTab" + i).text(result.questions[i].question);
                        $("#MyTab" + i).append("<textarea class=\"form-control\" rows=\"3\"></textarea>");
                    } else if (result.questions[i].type == "chose") {
                        $("#MyTab" + i).text(result.questions[i].questions);
                    }
                }
                $('#myTab li:eq(0) a').tab('show');
            });

            $('#myTab').on('shown.bs.tab', function (e) {
                var ans = {
                    answers: new Array()
                };
                for (var i = 0; i < qwl; i++) {
                    ans.answers.push($("#MyEdit" + i).val());
                }
                $.post("SetAnswers",
                        {
                            answers: JSON.stringify(ans)
                        },
                        function (data, status) {
                            //alert("Data: " + data + "\nStatus: " + status);
                        });
            });
        });
    </script>
</head>
<body>
<h1>Hello, world!</h1>

<ul class="nav nav-pills" id="myTab">
</ul>

<div class="tab-content" id="myTab-content">
</div>
</body>
</html>