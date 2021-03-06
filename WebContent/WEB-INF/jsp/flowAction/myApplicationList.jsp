<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>我的申请查询</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 我的申请查询
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="flowAction_myApplicationList">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>按条件查询：</td>
					<td>
						<s:select name="applicationTemplateId" cssClass="SelectStyle"
							list="#applicationTemplateList" listKey="id" listValue="name"
							headerKey="" headerValue="查看全部模板"
						/>
					</td>
					<td>
						<s:select name="applicationStatus" cssClass="SelectStyle"
							list="{'审批中', '未通过', '已通过'}"
							headerKey="" headerValue="查看全部状态"
						/>
					</td>
					<td><a href=""><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
				</tr>
			</table>
		</s:form>
	</div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250px">标题</td>
				<td width="115px">申请人</td>
				<td width="115px">申请日期</td>
				<td width="115px">当前状态</td>
				<td>相关操作</td>
			</tr>
		</thead>	

		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">

		<s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td>${title}</td>
				<td>${applicant.name}&nbsp;</td>
				<td>${applyTime}&nbsp;</td>
				<td>${status}&nbsp;</td>
				<td>
					<s:a action="flowAction_approveHistory?applicationId=%{id}">查看流转记录</s:a>
				</td>
			</tr>
		</s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>


<div class="Description">
	说明：<br />
	1，排序是：按申请时间降序排列（最后的申请在最前面）。<br>
</div>

</body>
</html>
