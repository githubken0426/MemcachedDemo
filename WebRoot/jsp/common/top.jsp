<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


   <div class="top">
      <div id="top_t">
        <div id="logo" class="fl"></div>
        <div id="photo_info" class="fr">
          <div id="base_info" class="fr">
           <div class="info_center">
              ${user.account}
              <span id="nt"><a href="javascript:void(0)" onclick="logOut()" id="notice">退出</a></span>
            </div>
          </div>
        </div>
      </div>
    </div>
<script>
	function logOut(){
		window.location.href='logOut.action';
	}
</script>
