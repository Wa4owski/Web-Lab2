<%@ page import="java.sql.SQLOutput" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="custom.Result" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Web: Lab2</title>
   <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image" href="https://faviconka.ru/ico/1/faviconka.ru_1_150074.ico">
</head>
<body>


<header>
    <h1 class="main-header" id="right-text">Лабораторная работа №2. Вариант: 13205</h1>
    <h1 class="main-header" id="left-text">Ваховский Павел Андреевич, P3213</h1>
</header>
<section class="content">

    <br>
    <br>
    <br>
    <div style="position: relative; text-align: center" >
        <!--        <header>-->
        <!--            <h2 class="card-header">Graphics</h2>-->
        <!--        </header>-->
<%--        <object type="image/svg+xml" data="graph.svg"></object>--%>
        <canvas id="graph-canvas" width="500px" height="500px">Интерактивная область графика</canvas>

    </div>
    <br>
    <br>
    <br>

    <div  id="values">
        <header>
            <h2 class="card-header">Values</h2>
        </header>

        <tr>

            <div id="input-form">
                <table id="input-grid">
                    <!-- X Value -->

                    <tr>
                        <td class="input-grid-label">
                            <label> <!--for="x-buttons"-->X: </label>
                        </td>

                        <td id="input-grid-value">
                            <% for(int i = -5; i <= 3; i++){ %>
                                <div class="center-labeled">
                                    <label class="label" ><%=i%></label>
                                    <input type="checkbox" class="x-checkbox" name="x" value="<%=i%>">
                                </div>
                            <% } %>
                        </td>
                    </tr>

                    <!-- Y Value -->
                    <tr>
                        <td class="input-grid-label">
                            <label for="y-textinput">Y:</label>
                        </td>

                        <td class="input-grid-value">
                            <input id="y-textinput" type="text" name="yval" maxlength="10" autocomplete="off" placeholder="Number from -5 to 5...">
                        </td>
                    </tr>

                    <!-- R Value -->
                    <tr>
                        <td class="input-grid-label">
                            <label>R:</label>
                        </td>

                        <td class="input-grid-value r-radio-group">

                                <% for(double i = 1; i <= 3; i += 0.5){ %>
                                    <div class="center-labeled">
                                        <label class="label" ><%=i%></label>
                                        <input type="checkbox" class="r-checkbox" name="r" value="<%=i%>">
                                    </div>
                                 <% } %>

                        </td>
                    </tr>



                </table>

                <input class="button" type="submit" value="Submit" onclick="submit()">
                <input class="button" type="reset" value="Reset" onclick="reset()">
            </div>


            </td>
        </tr>
        </table>

    </div>

    <br> <br> <br>

    <ul id="errors" style="list-style-type: none"> </ul>


</section>

<table id="result-table">
    <tbody>
    <tr class="table-header">
        <th class="coords-col">X</th>
        <th class="coords-col">Y</th>
        <th class="coords-col">R</th>
        <th class="time-col">Время запроса</th>
        <th class="time-col">Время исполнения</th>
        <th class="verdict-col">Попадание</th>

    </tr>
    <%
        try {
            ArrayList<Result> results = (ArrayList<Result>) session.getAttribute("results");
            Collections.reverse(results);
            for (Result result : results) {
                out.print("<tr>" + result.generateRow() + "<tr>");
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    %>

    </tbody>
</table>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/main.js"></script>
<%--<script src="js/graph.js"></script>--%>

</body>
</html>