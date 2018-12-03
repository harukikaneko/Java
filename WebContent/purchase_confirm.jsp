<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="item_bean" scope="request" class="shopping.ItemBean"/>
<%-- 購入確認画面 --%>
<!DOCTYPE html>
<html>
    <head>
        <title> 購入確認画面</title>
        <link href="css/shopping.css" rel="stylesheet" type="text/css" />
        <link href="css/boot.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/v.js"></script>
	<script src="js/va.js"></script>
	<script src="js/val.js"></script>
    <script type="text/javascript" src='<c:url value="message.js"/>'></script>
        <jsp:include page="header.jsp"/>

        <main>
            <h1>購入確認</h1>
            <p>次の商品を購入しますか？</p>

            <form action="./ResultServlet" method="post">
                <table class="table table-bordered shopping_table">
                	<thead class="thead-dark">
                		<tr>
                            <th>銘柄コード</th>
                            <th>銘柄名</th>
                            <th>購入金額</th>
                            <th>数量</th>
                            <th>注文価格</th>
                            <th>執行条件</th>
                            <th>取引日時</th>
                        </tr>
                	</thead>
                    <tbody>                        
                        <tr>
                            <%-- リクエストスコープから表示する値を取得 --%>
                            <td><%=request.getAttribute("code") %></td>
                            <td><%=request.getAttribute("name") %></td>
                            <td class="int"><%=request.getAttribute("purchased_total")%></td>
                            <td class="int"><%=request.getAttribute("quantity") %></td>
                            <td><%=request.getAttribute("select") %></td>
                            <td><%=request.getAttribute("choice") %></td>
                            <td><%= session.getAttribute("date") %></td>
                        </tr>
                    </tbody>
                </table>
                 <div class="button">
                 			 
          					 
                            <input class="common_button"　onclick="return notice()" type="submit" value="購入する">
                            <input type="hidden" name="code" value="<%=request.getAttribute("code") %>"/> 
                            <input type="hidden" name="name" value="<%=request.getAttribute("name") %>"/> 
                            <input type="hidden" name="quantity" value="<%=request.getAttribute("quantity") %>"/> 
                            <input type="hidden" name="purchased_total" value="<%=request.getAttribute("purchased_total")%>"/> 
                            <input type="hidden" name="select" value="<%=request.getAttribute("select") %>"/> 
                            <input type="hidden" name="choice" value="<%=request.getAttribute("choice") %>"/> 
                            <input type="hidden" name="dateinfo" value="<%= session.getAttribute("dateinfo") %>"/> 
                            <input type="hidden" name="date" value="<%= session.getAttribute("date") %>"/>

                            <input type="hidden" name="category" value="<%= request.getAttribute("category") %>"/> 
                            <input type="hidden" name="orderstatus" value="注文中">
                            <input type="hidden" name="sellbuy" value="買い">

                  </div>
                  </form>
             				   <input class="common_button" type="submit" onclick="history.back()" value="戻る">
          		  </form>
        </main>
    </body>
</html>