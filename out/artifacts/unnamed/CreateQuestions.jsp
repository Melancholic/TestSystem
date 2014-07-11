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
    <link href="styles.css" rel="stylesheet">

    <script>

        $(document).ready(function () {
            $('#fomAnswers').hide();

            $('#typeID').change(function () {
                if ($('#typeID').val() == "Text") {
                    alert("Text");
                    $('#fomAnswers').hide();
                } else if ($('#typeID').val() == "Chose") {
                    alert("Chose");
                    $('#fomAnswers').show();
                }
            })

        });


    </script>

</head>
<body>
<br><br><br>
<center>
    <form class="form-horizontal">
        <fieldset>
            <table>
                <tr>
                    <td>
                        <table class="table">
                            <div class="control-group">
                                <tr>
                                    <td><label class="control-label" for="quesInp">Question text</label></td>
                                    <td><label class="control-label" for="typeID">Type</label></td>
                                </tr>
                                <tr>
                                    <div class="controls">
                                        <td><input id="quesInp" name="quesInp" type="text" placeholder=""
                                                   class="input-xlarge"></td>
                                        <td>
                                            <div class="controls">
                                                <select id="typeID" name="typeID" class="input-xlarge">
                                                    <option id="txtID">Text</option>
                                                    <option id="chsID">Chose</option>
                                                </select>
                                            </div>

                                        </td>
                                    </div>
                                </tr>
                            </div>
                        </table>
                    </td>
                    <td>
                        <table class="table">
                            <div class="control-group">
                                <tr>
                                    <td><label class="control-label" for="quesInp">Answers</label></td>
                                    <td><label class="control-label" for="quesInp">Correct</label></td>
                                </tr>
                                <tr>
                                    <div class="controls">
                                        <td><input id="answInp" name="answInp" type="text" placeholder=""
                                                   class="input-xlarge"></td>
                                        <td>
                                            <input type="checkbox"/><br>
                                        </td>
                                    </div>
                                </tr>
                            </div>
                        </table>
                    </td>
                </tr>
            </table>


            <!-- Button -->
            <div class="control-group">
                <label class="control-label" for="sendBut"></label>

                <div class="controls">
                    <button id="sendBut" name="sendBut" class="btn btn-primary">Send</button>
                </div>
            </div>

        </fieldset>
    </form>
</center>

</body>
<!-- <body>
<center>
   <form name="Create a new questions"
          method="post"
          action="admin/CrtQuest">
        <table>
            <tr>
                <td><B>JSON:</B></td>
                <td><input type=textbox name="data" size="25" value=""></td>
            </tr>
        </table>
        <input type=submit value="Submit">
        <br><br>



</center>
</body>
</form>-->
</html>