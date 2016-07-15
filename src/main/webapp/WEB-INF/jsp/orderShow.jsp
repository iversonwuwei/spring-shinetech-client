<%--
  Created by IntelliJ IDEA.
  User: walden
  Date: 16/7/15
  Time: 下午12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        var keys = ${orders.orders};
        function f1(){
            var orderid="";
            var owner="";
            var customerName = "";
            var customerEmail = "";
            var content = "";
            for(var key in keys){
                orderid += "<td>"+key+": "+keys[key].order_id+"</td>";
                owner += "<td>"+key+": "+keys[key].owner+"</td>";
                customerName += "<td>"+key+": "+keys[key].customer_name+"</td>";
                customerEmail += "<td>"+key+": "+keys[key].customer_email+"</td>";
            }
            content += "<tr>"+orderid+"</tr>"+"<tr>"+owner+"</tr>"+"<tr>"+customerName+"</tr>"+"<tr>"+customerEmail+"</tr>";
            document.getElementById("table").innerHTML = content;
        }
    </script>
</head>
<body>
<input type="button" id="c1" name="c1" onclick="f1();" value="click one"/>
<div id="content">
    <table id="table">
    </table>
</div>
</body>
</html>
