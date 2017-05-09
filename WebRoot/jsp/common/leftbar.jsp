<%@page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="<%=path%>/resources/css/style.css">
<script type="text/javascript" src="<%=path%>/resources/js/jquery.SuperSlide.js"></script>

<script type="text/javascript">
  $(function(){
      $(".sideMenu").slide({
         titCell:"h3", 
         targetCell:"ul",
         defaultIndex:0, 
         effect:'slideDown', 
         delayTime:'500' , 
         trigger:'click', 
         triggerTime:'150', 
         defaultPlay:true, 
         returnDefault:false,
         easing:'easeInQuint',
         endFun:function(){
              scrollWW();
         }
       });
      $(window).resize(function() {
          scrollWW();
      });
  });
  function scrollWW(){
    if($(".side").height()<$(".sideMenu").height()){
       $(".scroll").show();
       var pos = $(".sideMenu ul:visible").position().top-38;
       $('.sideMenu').animate({top:-pos});
    }else{
       $(".scroll").hide();
       $('.sideMenu').animate({top:0});
       n=1;
    }
  } 

var n=1;
function menuScroll(num){
  var Scroll = $('.sideMenu');
  var ScrollP = $('.sideMenu').position();
  /*alert(n);
  alert(ScrollP.top);*/
  if(num==1){
     Scroll.animate({top:ScrollP.top-38});
     n = n+1;
  }else{
    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
    if (ScrollP.top<0) {
      Scroll.animate({top:38+ScrollP.top});
    }else{
      n=1;
    }
    if(n>1){
      n = n-1;
    }
  }
}
  </script>

	<div class="side">
        <div class="sideMenu" style="margin:0 auto">
          <h3>Memcached缓存管理</h3>
          <ul id="leftBarHistory">
            <li ><a href="shop_shop_list.action" >查询数据库的数据</a></li>
            <li><a href="shop_cache_list.action">查询缓存的数据</a></li>
          </ul>
          <h3>Redis缓存管理</h3>
          <ul id="redisCache">
          	<li><a href="redisShop_redisTemplateAddList.action">查询数据库的数据</a></li>
            <li><a href="redisShop_redisTemplateGetList.action">查询缓存的数据</a></li>
            <li><a href="#">导航菜单</a></li>
            <li><a href="#">导航菜单</a></li>
            <li ><a href="#" >导航菜单</a></li>
            <li><a href="#">导航菜单</a></li>
          </ul>
       </div>
    </div>
	<div class="scroll">
          <a href="javascript:;" class="per" title=" " onClick="menuScroll(1);"></a>
          <a href="javascript:;" class="next" title=" " onClick="menuScroll(2);"></a>
    </div>



