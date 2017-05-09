<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>数据库信息列表</title>
	<link rel="stylesheet" href="<%=path%>/resources/css/common.css"/>
   	<link rel="stylesheet" href="<%=path%>/resources/css/main.css"/>
   	<link rel="stylesheet" href="<%=path%>/resources/kkpager/kkpager_blue.css"/>
   	
   	<script type="text/javascript" src="<%=path%>/resources/js/jquery.min.js"></script>
   	<script type="text/javascript" src="<%=path%>/resources/js/colResizable-1.3.min.js"></script>
   	<script type="text/javascript" src="<%=path%>/resources/js/common.js"></script>
   	<script type="text/javascript" src="<%=path%>/resources/kkpager/kkpager.js"></script>
  <script type="text/javascript"><%--
  
  function getParameter(name) { 
  		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
  		var r = window.location.search.substr(1).match(reg); 
  		if (r!=null)
  		 return unescape(r[2]); 
  		return null;
  	}
  	
  $(function(){
  		//分页开始
  		var totalPage = ${totalPages};
  		var totalRecords = ${totalCount};
  		var pageNo = getParameter('pno');
  		if(!pageNo){
  			pageNo = 1;
  		}
  		//初始化分页控件
  		kkpager.init({
  			pno : pageNo,
  			total : totalPage,			 //总页码
  			totalRecords : totalRecords, //总数据条数
  			hrefFormer : 'shop_shop_list',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n;
  			},
  			lang : {
  				prePageText : '上一页',
  				nextPageText : '下一页',
  				totalPageBeforeText : '共',
  				totalPageAfterText : '页',
  				totalRecordsAfterText : '条数据',
  				gopageBeforeText : '转到',
  				gopageButtonOkText : '确定',
  				gopageAfterText : '页',
  				buttonTipBeforeText : '第',
  				buttonTipAfterText : '页'
  			}
  		});
  		//生成
  		kkpager.generPageHtml();
  	});
}
  --%>
  $(function(){ 
	  	//设置leftbar
			$("#leftBarHistory li").eq(0).addClass("on");
			$("#leftBarHistory li").eq(0).children("a").addClass("sselect");
	  });
  </script>
  <style type="">
  	.footer{margin-top:0px;}
  	.titleRight{ font-size: 12px;font-family: "microsoft yahei";
    			border-bottom: 1px solid #ddd;margin-bottom: 
    			20px;line-height: 30px;
    			}
    .titleRight strong{line-height: 30px; display: inline-block;
    			 	  padding: 0 10px; margin-bottom: -1px;
    			 }
    .titleBoder{border-bottom: 1px solid #F60;}
  </style>
  </head>
  
  <body style="overflow: scroll">
 		<jsp:include page="common/top.jsp" flush="true" />
   	<div id="middle">
  		<jsp:include page="common/leftbar.jsp" flush="true"/>
	<div style="margin:100px 0px 0px 230px;"  id="mainFrame">    	
	<form action="" method="post" id="totalForm" >
		    <div class="right_cont">
		          <ul class="breadcrumb">
		          	<li>当前位置：<span style="color:red;font-size: 18px; font-weight: bold;">数据库信息列表</span></li>
		          </ul>
           <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		     <tbody>
		       <tr align="center">
		         <td width="100" ><strong>店铺名称</strong></td>
		         <td width="150" ><strong>店铺描述</strong></td>
		         <td width="50" ><strong>联系电话</strong></td>
		         <td width="120"><strong>店铺地址</strong></td>
		         <td width="100"><strong>创建时间</strong></td>
		         <td width="60" nowrap="nowrap"><strong> 操 作 </strong></td>
		       </tr>
		        	<c:forEach var="shop" items="${shopList}" varStatus="s">
					 <tr align="center">
					 	<td>${shop.shopName}</td>
					 	<td><c:choose>
						 		<c:when test="${fn:length(shop.shopDescription)>30}">
									${fn:substring(shop.shopDescription,0,30)}... 
								</c:when>
						 		<c:otherwise>
						 			${shop.shopDescription}
						 		</c:otherwise>
					 		</c:choose>
					 	 </td>
					 	<td>${shop.telNumberList}</td>
					 	<td>
					 		${shop.province}${shop.city}${shop.district}${shop.detailAddress}
					 	</td>
					 	<td><fmt:formatDate value="${shop.insertTime}" pattern="yyyy-MM-dd" type="BOTH" dateStyle="long" /> </td>
					 	<td>${s.index+1 }</td>
					 </tr>
					</c:forEach>
			     </tbody>
			   </table>
			</div>
		</form>
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
		<jsp:include page="common/footer.jsp" flush="true"></jsp:include>
	 </div>
	 </div>
  </body>
</html>
